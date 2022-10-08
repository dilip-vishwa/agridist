package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.EquipmentIssue;
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
public class EquipmentIssueService {

    public static final String COL_NAME="equipmentissues";

    public EquipmentIssue saveEquipmentIssueDetails(EquipmentIssue equipmentIssue) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        equipmentIssue.setEquipmentIssue_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipmentIssue);
        return equipmentIssue;
    }

    public List<EquipmentIssue> getEquipmentIssues() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<EquipmentIssue> equipmentIssueList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            equipmentIssueList.add(document.toObject(EquipmentIssue.class));
        }
        return equipmentIssueList;
    }
    public Optional<EquipmentIssue> getEquipmentIssueDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return Optional.ofNullable(future.get().toObject(EquipmentIssue.class));
    }

    public String updateEquipmentIssueDetails(EquipmentIssue equipmentIssue, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipmentIssue);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteEquipmentIssue(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with EquipmentIssue ID "+id+" has been deleted";
    }

}