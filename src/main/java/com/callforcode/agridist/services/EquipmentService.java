package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Equipment;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class EquipmentService {

    public static final String COL_NAME="equipments";

    public String saveEquipmentDetails(Equipment equipment) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        equipment.setEquipment_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipment);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Equipment> getEquipments() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Equipment> equipmentList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            equipmentList.add(document.toObject(Equipment.class));
        }
        return equipmentList;
    }
    public Equipment getEquipmentDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Equipment equipment = null;

        if(document.exists()) {
            equipment = document.toObject(Equipment.class);
            return equipment;
        }else {
            return null;
        }
    }

    public String updateEquipmentDetails(Equipment equipment, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipment);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteEquipment(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Equipment ID "+id+" has been deleted";
    }

}