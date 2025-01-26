package backend.config;

import java.io.ByteArrayInputStream;
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
            // Obtendo as credenciais do Firebase da variável de ambiente
            String firebaseCredentials = System.getenv("FIREBASE_CREDENTIALS");

            if (firebaseCredentials == null || firebaseCredentials.isEmpty()) {
                throw new RuntimeException("As credenciais do Firebase não foram encontradas.");
            }

            // Convertendo a string JSON para InputStream
            InputStream serviceAccount = new ByteArrayInputStream(firebaseCredentials.getBytes());

            // Inicializando o Firebase com as credenciais diretamente
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            // Inicializa o FirebaseApp com as opções de configuração
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