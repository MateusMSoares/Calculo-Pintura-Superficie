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

import backend.dto.TipoDTO;
import backend.entitys.Tipo;

@Service
public class TipoService {

    @Autowired
    private Firestore db;

    public List<TipoDTO> carregarTipos() throws IOException {
        List<TipoDTO> tipos = new ArrayList<>();
        
        try {
            // Acesse a coleção de tipos
            ApiFuture<QuerySnapshot> future = db.collection("tipos").get();  // "tipos" é o nome da coleção
            
            // Esperar pela consulta e obter os documentos
            QuerySnapshot querySnapshot = future.get();
            
            // Itera sobre os documentos e converte para objetos Tipo
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                TipoDTO tipo = document.toObject(TipoDTO.class);
                tipo.setId(document.getId());  // Adiciona o ID do documento
                tipos.add(tipo);
            }
        } catch (Exception e) {
                System.err.println("Erro ao carregar tipos: " + e.getMessage());
                throw new IOException("Erro ao carregar tipos", e);
            }
            
        return tipos;
    }


    public TipoDTO procurarPorId(String id) throws IOException {
        TipoDTO tipo = null;
        
        try {
            // Acesse o documento do tipo com o ID
            DocumentReference docRef = db.collection("tipos").document(String.valueOf(id));
            
            // Obtém o documento
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            
            // Verifique se o documento existe
            if (document.exists()) {
                // Converte o documento para um objeto Tipo
                tipo = document.toObject(TipoDTO.class);
                tipo.setId(document.getId());  // Adiciona o ID do documento
            } else {
                throw new IOException("Tipo com ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
                System.err.println("Erro ao procurar tipo: " + e.getMessage());
                throw new IOException("Erro ao procurar tipo", e);
            }
        
        return tipo;
    }

        public Tipo criarTipo(Tipo newTipo) throws IOException {
        Tipo tipo = newTipo;
              
        DocumentReference docRef = db.collection("tipos").document();
        ApiFuture<WriteResult> future = docRef.set(tipo, SetOptions.merge()); 

        future.addListener(() -> {
            try {
                WriteResult result = future.get();  // Espera a resposta do Firestore
                System.out.println("Tipo criado com sucesso! Timestamp: " + result.getUpdateTime());
            } catch (Exception e) {
                System.err.println("Erro ao criar o tipo: " + e.getMessage());
            }
        }, Executors.newSingleThreadExecutor());  // Você precisa de um Executor para rodar o código assíncrono

        return tipo;
    }


}
