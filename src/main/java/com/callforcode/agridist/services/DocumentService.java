package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class DocumentService {

    public static final String COL_NAME="documents";

    public String saveDocumentDetails(Document document) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        document.setDocument_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(document);
        return collectionsApiFuture.get().getUpdateTime().toString();
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
    public Document getDocumentDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot documentsnapshot = future.get();

        Document document = null;

        if(documentsnapshot.exists()) {
            document = documentsnapshot.toObject(Document.class);
            return document;
        }else {
            return null;
        }
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