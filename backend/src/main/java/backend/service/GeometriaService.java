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

import backend.dto.GeometriaDTO;
import backend.entitys.Geometria;

@Service
public class GeometriaService {

    @Autowired
    private Firestore db;

    public List<GeometriaDTO> carregarGeometrias() throws IOException {
    List<GeometriaDTO> geometrias = new ArrayList<>();
    
    try {
        // Acesse a coleção de geometrias
        ApiFuture<QuerySnapshot> future = db.collection("geometrias").get();
        
        // Esperar pela consulta e obter os documentos
        QuerySnapshot querySnapshot = future.get();
        
        // Itera sobre os documentos e converte para objetos Geometria
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            GeometriaDTO geometria = document.toObject(GeometriaDTO.class);
            geometria.setId(document.getId());  // Adiciona o ID do documento
            geometrias.add(geometria);
        }
    } catch (Exception e) {
            System.err.println("Erro ao carregar geometrias: " + e.getMessage());
            throw new IOException("Erro ao carregar geometrias", e);
        }
        
        return geometrias;
    }


    public GeometriaDTO procurarPorId(String id) throws IOException {
        GeometriaDTO geometria = null;
        
        try {
            // Acesse o documento da geometria com o ID
            DocumentReference docRef = db.collection("geometrias").document(String.valueOf(id));
            
            // Obtém o documento
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            
            // Verifique se o documento existe
            if (document.exists()) {
                // Converte o documento para um objeto Geometria
                geometria = document.toObject(GeometriaDTO.class);
                geometria.setId(document.getId());  // Adiciona o ID do documento
            } else {
                throw new IOException("Geometria com ID " + id + " não encontrada.");
            }
        } catch (Exception e) {
                System.err.println("Erro ao procurar geometria: " + e.getMessage());
                throw new IOException("Erro ao procurar geometria", e);
            }
        
        return geometria;
    }

    public void salvarEmLote(List<Geometria> geometrias) throws IOException {
        WriteBatch batch = db.batch();
    
        try {
            for (Geometria geometria : geometrias) {
                DocumentReference docRef = db.collection("geometrias").document(); 
                batch.set(docRef, geometria); 
            }
            batch.commit().get();
            System.out.println("Geometrias salvas com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao salvar geometrias em lote: " + e.getMessage());
            throw new IOException("Erro ao salvar geometrias em lote", e);
        }
    }
       

}
