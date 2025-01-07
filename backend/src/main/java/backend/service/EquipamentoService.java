package backend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.EquipamentDto;
import backend.entitys.Equipamento;
import backend.entitys.Geometria;
import backend.entitys.Tipo;
import lombok.Getter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


@Service
@Getter
public class EquipamentoService {
    private JsonReader jsonReader;
    @Autowired
    private TipoService tipoService;
    @Autowired
    private GeometriaService geometriaService;

    public EquipamentoService() {
        this.jsonReader = new JsonReader();
    }

    public Equipamento procurarPorId(int idEquipamento) throws IOException {
        try {
            return jsonReader.carregarEquipamentoPorId(idEquipamento);
    
        } catch (Exception e) {
            System.err.println("Erro ao montar o equipamento: " + e.getMessage());
            throw new RuntimeException("Erro ao montar o equipamento", e);
        }
    }

    public List<Equipamento> listarEquipamentos() throws IOException{
        List<Equipamento> equipamentos = jsonReader.carregarEquipamentos();
        return equipamentos;
    }
    
    public Equipamento criarEquipamento(EquipamentDto newEquipamento) throws IOException {
        Equipamento equipamento = new Equipamento();
        equipamento.setNome(newEquipamento.getNome());
        equipamento.setTipo(newEquipamento.getTipoId());
        equipamento.setGeometria(newEquipamento.getGeometriaId());
        // Passando o Map de propriedades_fundamentais para o método setPropriedades
        equipamento.setPropriedades(this.setPropriedades(equipamento, newEquipamento.getPropriedades_fundamentais()));
    
        return equipamento;
    }
    
    public Map<String, Object> setPropriedades(Equipamento equipamento, Map<String, Object> propriedades_fundamentais) throws IOException {
        Map<String, Object> propriedades = new HashMap<>();
    
        // Carregar Tipo e Geometria
        Tipo tipo = tipoService.procurarPorId(equipamento.getTipo());
        Geometria geometria = geometriaService.procurarPorId(equipamento.getGeometria());
    
        if (tipo != null && tipo.getPropriedades() != null) {
            propriedades.putAll(tipo.getPropriedades());
        }
    
        if (geometria != null && geometria.getPropriedades() != null) {
            propriedades.putAll(geometria.getPropriedades());
        }

        for (Map.Entry<String, Object> entry : propriedades_fundamentais.entrySet()) {
            String chave = entry.getKey();
            Object valor = entry.getValue();
    
            if (propriedades.containsKey(chave)) {
                propriedades.put(chave, valor); 
            } else {
                propriedades.put(chave, valor); 
            }
        }
    
        return propriedades;
    }

    public Map<String, Object> calcularMedidas(Equipamento equipamento) throws Exception {
        Map<String, Object> resultados = new HashMap<>();
        Map<String, Object> formulasOriginais = new HashMap<>();
        Map<String, Object> formulasComValores = new HashMap<>();
        
        Geometria geometria = geometriaService.procurarPorId(equipamento.getGeometria());
        Map<String, Object> propriedades = geometria.getPropriedades();
        Map<String, String> formulas = geometria.getFormulas();
        
        for (Map.Entry<String, String> formulaEntry : formulas.entrySet()) {
            String chave = formulaEntry.getKey();
            String formula = formulaEntry.getValue();

            formulasOriginais.put(chave, formula);
            
            String formulaComValores = formula;
            for (Map.Entry<String, Object> propEntry : propriedades.entrySet()) {
                String propKey = propEntry.getKey();
                Double propValue = (Double) propEntry.getValue();
                formulaComValores = formulaComValores.replace(propKey, String.format("%.2f", propValue));
            }
            
            formulasComValores.put(chave, formulaComValores);
            
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
                double result = expression.evaluate();
                resultados.put(chave, result);
            } catch (Exception e) {
                e.printStackTrace();
                resultados.put(chave, "Error evaluating formula");
            }
        }

        for (Map.Entry<String, Object> entry : propriedades.entrySet()) {
            String chave = entry.getKey();
            if (!resultados.containsKey(chave)) {
                resultados.put(chave, entry.getValue());
                formulasOriginais.put(chave, "Propriedade: " + chave);
                formulasComValores.put(chave, String.format("%.2f", entry.getValue()));
            }
        }
        
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("resultado", resultados);
        resultado.put("formulas_originais", formulasOriginais);
        resultado.put("formulas_com_valores", formulasComValores);
        
        return resultado;
    }
        
    
}
