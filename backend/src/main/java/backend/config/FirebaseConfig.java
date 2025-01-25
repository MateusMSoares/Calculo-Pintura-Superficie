package backend.config;

import java.io.FileInputStream;
import java.io.IOException;

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
            // Caminho para o arquivo de credenciais
            FileInputStream serviceAccount = new FileInputStream("frontend/src/config/calculosuperfice-firebase-adminsdk-pegyb-132ca3ac0c.json");

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
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}

