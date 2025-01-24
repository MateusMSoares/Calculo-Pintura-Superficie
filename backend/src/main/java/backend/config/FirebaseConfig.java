package backend.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {
    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("path/to/your/credentials-file.json");

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
