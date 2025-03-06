package backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        
        ApiFuture<QuerySnapshot> query = db.collection("equipamentos").get();
        try {
            // Esperar pela consulta e obter os documentos
            QuerySnapshot querySnapshot = query.get();
            
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                Equipamento equipamento = document.toObject(Equipamento.class);
                if (equipamento != null) {
                    equipamento.setId(document.getId());
                    equipamentos.add(equipamento);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar equipamentos: " + e.getMessage());
        }
        
        return equipamentos;
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
