package backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

import backend.dto.EquipamentoDto;
import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import lombok.Getter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


@Service
@Getter
public class EquipamentoService {
    @Autowired
    private TipoService tipoService;
    @Autowired
    private GeometriaService geometriaService;
    @Autowired
    private Firestore db;

    public EquipamentoDto procurarPorId(String idEquipamento) throws IOException {
        try {
            // Acesse a coleção de equipamentos e o documento com o idEquipamento
            DocumentReference docRef = db.collection("equipamentos").document(idEquipamento);
    
            // Obtém o documento
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
    
            // Verifique se o documento existe
            if (document.exists()) {
                // Se o documento existe, converta para um objeto Equipamento
                EquipamentoDto equipamento = document.toObject(EquipamentoDto.class);
                equipamento.setId(document.getId());
                return equipamento;
            } else {
                // Caso o documento não exista
                throw new IOException("Equipamento com ID " + idEquipamento + " não encontrado.");
            }
        } catch (Exception e) {
            // Em caso de erro, imprima a mensagem de erro e lance uma exceção
            System.err.println("Erro ao procurar o equipamento: " + e.getMessage());
            throw new IOException("Erro ao procurar o equipamento", e);
        }
    }

    public List<EquipamentoDto> listarEquipamentos() {
        List<EquipamentoDto> equipamentos = new ArrayList<>();
        
        ApiFuture<QuerySnapshot> query = db.collection("equipamentos").get();
        try {
            // Esperar pela consulta e obter os documentos
            QuerySnapshot querySnapshot = query.get();
            
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                EquipamentoDto equipamento = document.toObject(EquipamentoDto.class);
                equipamento.setId(document.getId());
                equipamentos.add(equipamento);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar equipamentos: " + e.getMessage());
        }
        
        return equipamentos;
    }
    
    public Equipamento criarEquipamento(Equipamento newEquipamento) throws IOException {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(newEquipamento.getNome());
        equipamento.setTipo(newEquipamento.getTipo());
        equipamento.setGeometria(newEquipamento.getGeometria());
        
              
        // Cria um documento na coleção 'equipamentos' com um ID automático
        DocumentReference docRef = db.collection("equipamentos").document();  // Ou pode usar .document(id) para definir um ID específico
        
        // Configura os dados que serão salvos no Firestore
        ApiFuture<WriteResult> future = docRef.set(equipamento, SetOptions.merge());  // merge() é usado para garantir que apenas os campos fornecidos sejam atualizados.

        // Usando ApiFuture para lidar com a resposta assíncrona
        future.addListener(() -> {
            try {
                WriteResult result = future.get();  // Espera a resposta do Firestore
                System.out.println("Equipamento criado com sucesso! Timestamp: " + result.getUpdateTime());
            } catch (Exception e) {
                System.err.println("Erro ao criar o equipamento: " + e.getMessage());
            }
        }, Executors.newSingleThreadExecutor());  // Você precisa de um Executor para rodar o código assíncrono

        return equipamento;
    }
    
    

    public Map<String, Object> calcularMedidas(Equipamento equipamento) throws Exception {
        Map<String, Object> resultados = new HashMap<>();
        Map<String, Object> formulasOriginais = new HashMap<>();
        Map<String, Object> formulasComValores = new HashMap<>();
        
        Geometria geometria = equipamento.getGeometria();
        Map<String, Object> propriedades = geometria.getPropriedades();
        Map<String, String> formulas = geometria.getFormulas();
        
        // Fase 1: Calcular as fórmulas e atribuir resultados diretamente às propriedades
        for (Map.Entry<String, String> formulaEntry : formulas.entrySet()) {
            String chave = formulaEntry.getKey();
            String formula = formulaEntry.getValue();
    
            formulasOriginais.put(chave, formula);
            
            // Substituir as variáveis nas fórmulas pelas propriedades calculadas
            String formulaComValores = formula;
            for (Map.Entry<String, Object> propEntry : propriedades.entrySet()) {
                String propKey = propEntry.getKey();
                Double propValue = (Double) propEntry.getValue();
                formulaComValores = formulaComValores.replace(propKey, String.format("%.2f", propValue));
            }
            
            formulasComValores.put(chave, formulaComValores);
    
            // Usar ExpressionBuilder para avaliar a fórmula
            ExpressionBuilder builder = new ExpressionBuilder(formula);
        
            for (Map.Entry<String, Object> propEntry : propriedades.entrySet()) {
                String propKey = propEntry.getKey();
                builder.variable(propKey); 
            }
            Expression expression = builder.build();
        
            for (Map.Entry<String, Object> propEntry : propriedades.entrySet()) {
                String propKey = propEntry.getKey();
                Double propValue = (Double) propEntry.getValue();
                expression.setVariable(propKey, propValue);
            }
        
            try {
                // Avaliar a expressão
                double result = expression.evaluate();
                resultados.put(chave, result);
                // Atribuir o valor calculado diretamente à propriedade
                propriedades.put(chave, result);
            } catch (Exception e) {
                e.printStackTrace();
                resultados.put(chave, "Error evaluating formula");
            }
        }
    
        // Fase 2: Garantir que as propriedades que não foram calculadas nas fórmulas sejam adicionadas aos resultados
        for (Map.Entry<String, Object> entry : propriedades.entrySet()) {
            String chave = entry.getKey();
            if (!resultados.containsKey(chave)) {
                resultados.put(chave, entry.getValue());
                formulasComValores.put(chave, String.format("%.2f", entry.getValue()));
            }
        }
        
        // Construir o resultado final
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("resultado", resultados);
        resultado.put("formulas_originais", formulasOriginais);
        resultado.put("formulas_com_valores", formulasComValores);
        
        return resultado;
    }  


}
