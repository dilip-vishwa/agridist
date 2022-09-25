package com.callforcode.agridist.lib;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Objects;

@Service
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {
            String project_id = System.getenv("project_id");
            String private_key_id = System.getenv("private_key_id");
            String private_key = System.getenv("private_key");
            String client_email = System.getenv("client_email");
            String client_id = System.getenv("client_id");
            String auth_uri = System.getenv("auth_uri");
            String token_uri = System.getenv("token_uri");
            String auth_provider_x509_cert_url = System.getenv("auth_provider_x509_cert_url");
            String client_x509_cert_url = System.getenv("client_x509_cert_url");

            String cred = "{" +
                "\"type\": \"service_account\"," +
                "\"project_id\":\"" + project_id + "\"," +
                "\"private_key_id\":\"" + private_key_id + "\"," +
                "\"private_key\":\"" + private_key + "\"," +
                "\"client_email\":\"" + client_email + "\"," +
                "\"client_id\":\"" + client_id + "\"," +
                "\"auth_uri\":\"" + auth_uri + "\"," +
                "\"token_uri\":\"" + token_uri + "\"," +
                "\"auth_provider_x509_cert_url\":\"" + auth_provider_x509_cert_url + "\"," +
                "\"client_x509_cert_url\":\"" + client_x509_cert_url + "\"" +
            "}";
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(cred);

//            ClassLoader classLoader = FBInitialize.class.getClassLoader();
//            File file = new File(Objects.requireNonNull(classLoader.getResource("agridist.json")).getFile());
//            FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
            InputStream serviceAccount = new ByteArrayInputStream(cred.getBytes());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://agridist-396f1-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            } else {
                FirebaseApp.getInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
