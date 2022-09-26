package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Land;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class LandService {

    public static final String COL_NAME="lands";

    public String saveLandDetails(Land land) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        land.setLand_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(land);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Land> getLands() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Land> landList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            landList.add(document.toObject(Land.class));
        }
        return landList;
    }
    public Land getLandDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Land land = null;

        if(document.exists()) {
            land = document.toObject(Land.class);
            return land;
        }else {
            return null;
        }
    }

    public String updateLandDetails(Land land, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(land);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteLand(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Land ID "+id+" has been deleted";
    }

}