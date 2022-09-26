package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Hire;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class HireService {

    public static final String COL_NAME="hires";

    public String saveHireDetails(Hire hire) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        hire.setHire_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(hire);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Hire> getHires() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Hire> hireList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            hireList.add(document.toObject(Hire.class));
        }
        return hireList;
    }
    public Hire getHireDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Hire hire = null;

        if(document.exists()) {
            hire = document.toObject(Hire.class);
            return hire;
        }else {
            return null;
        }
    }

    public String updateHireDetails(Hire hire, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(hire);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteHire(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Hire ID "+id+" has been deleted";
    }

}