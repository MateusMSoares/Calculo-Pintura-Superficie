package backend.config;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() {
        initializeFirebase(); 
        return getFirestore();
    }

    public static void initializeFirebase() {
        try {
            // Tenta obter as credenciais do Firebase da variável de ambiente (para produção, por exemplo)
            String firebaseCredentials = System.getenv("FIREBASE_CREDENTIALS");
    
            InputStream serviceAccount;
    
            if (firebaseCredentials != null && !firebaseCredentials.isEmpty()) {
                // Caso as credenciais estejam na variável de ambiente
                serviceAccount = new ByteArrayInputStream(firebaseCredentials.getBytes());
            } else {
                // Caso contrário, tenta carregar as credenciais de um arquivo local (para ambiente local)
                String filePath = "frontend/src/config/calculosuperfice-firebase-adminsdk-pegyb-132ca3ac0c.json";
                serviceAccount = new FileInputStream(filePath);
            }
    
            // Inicializando o Firebase com as credenciais
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
    
            // Inicializa o FirebaseApp com as opções de configuração, caso ainda não tenha sido inicializado
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase Initialized");
            }
    
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao inicializar o Firebase.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
    

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}