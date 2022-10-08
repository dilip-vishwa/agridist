package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class DocumentService {

    public static final String COL_NAME="documents";

    public Document saveDocumentDetails(Document document) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        document.setDocument_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(document);
        return document;
    }

    public List<Document> getDocuments() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Document> documentList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            documentList.add(document.toObject(Document.class));
        }
        return documentList;
    }
    public Optional<Document> getDocumentDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return Optional.ofNullable(future.get().toObject(Document.class));
    }

    public String updateDocumentDetails(Document document, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(document);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteDocument(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Document ID "+id+" has been deleted";
    }

}