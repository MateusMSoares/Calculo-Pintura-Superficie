package backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;

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

    public void salvarEmLote(List<Tipo> tipos) throws IOException {
        // Inicia o batch de escrita
        WriteBatch batch = db.batch();
        
        try {
            for (Tipo tipo : tipos) {
                // Crie uma referência para um novo documento com um ID gerado automaticamente
                DocumentReference docRef = db.collection("tipos").document();  // Firestore gera automaticamente o ID
                
                // Adiciona a operação de "set" no batch
                batch.set(docRef, tipo);
                
                // Imprime o ID do documento que será gerado
                System.out.println("Tipo será salvo com ID: " + docRef.getId());
            }

            // Commit para executar todas as operações de uma vez
            batch.commit().get();  // .get() espera a operação ser concluída
            System.out.println("Tipos salvos com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar tipos em lote: " + e.getMessage());
            throw new IOException("Erro ao salvar tipos em lote", e);
        }
    }


}
