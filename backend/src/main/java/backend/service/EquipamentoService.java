package backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;

import backend.dto.EquipamentoDto;
import backend.entitys.Equipamento;
import backend.entitys.Tanque;
import backend.entitys.Torre;
import lombok.Getter;


@Service
@Getter
public class EquipamentoService {
    @Autowired
    private TipoService tipoService;
    @Autowired
    private Firestore db;
  
    public EquipamentoDto procurarPorId(String idEquipamento) throws IOException {
        return null;
    }

    public List<Equipamento> listarEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
            
            // Obtém a referência para a coleção "equipamentos"
            CollectionReference equipamentosRef = db.collection("equipamentos");
            
            try {
                // Obtém todos os documentos da coleção
                QuerySnapshot snapshot = equipamentosRef.get().get();
                
                // Itera sobre cada documento na coleção
                for (QueryDocumentSnapshot document : snapshot) {
                    String tipo = document.getString("tipo");

                    Equipamento equipamento = document.toObject(criarPorTipo(tipo).getClass());
                    
                    // Adiciona o objeto Equipamento à lista
                    equipamentos.add(equipamento);
                }
            } catch (Exception e) {
                System.err.println("Erro ao listar equipamentos: " + e.getMessage());
            }
            
            return equipamentos;
    }

    public Equipamento criarPorTipo(String tipo){
        if (tipo.equals("TORRE")) {
            return new Torre();
        } else if (tipo.equals("TANQUE")) {
            return new Tanque();
        } else {
            return null;  
        }
    }


    public Equipamento criarEquipamento(Equipamento newEquipamento) throws IOException {
        
        DocumentReference docRef = db.collection("equipamentos").document();
        String documentId = docRef.getId();
        newEquipamento.setId(documentId);
        ApiFuture<WriteResult> future = docRef.set(newEquipamento, SetOptions.merge()); 

        // Usando ApiFuture para lidar com a resposta assíncrona
        future.addListener(() -> {
            try {
                WriteResult result = future.get();                
                System.out.println("Equipamento criado com sucesso! Timestamp: " + result.getUpdateTime());
            } catch (Exception e) {
                System.err.println("Erro ao criar o equipamento: " + e.getMessage());
            }
        }, Executors.newSingleThreadExecutor());  // Você precisa de um Executor para rodar o código assíncrono

        return newEquipamento;
    }
    
}
