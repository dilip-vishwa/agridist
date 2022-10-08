package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.Equipment;
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
public class EquipmentService {

    public static final String COL_NAME="equipments";

    public Equipment saveEquipmentDetails(Equipment equipment) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        equipment.setEquipment_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipment);
        return equipment;
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
    public Optional<Equipment> getEquipmentDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return Optional.ofNullable(future.get().toObject(Equipment.class));
    }

    public List<Equipment> getEquipmentDetailsByName(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
//        Query queryReference = dbFirestore.collection(COL_NAME).whereEqualTo("name", name);
        Query queryReference = dbFirestore.collection(COL_NAME).orderBy("name", Query.Direction.valueOf("ASCENDING")).startAt(name).endAt(name+ "\uf8ff");

        ApiFuture<QuerySnapshot> future = queryReference.get();
        List<Equipment> equipmentList = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            equipmentList.add(document.toObject(Equipment.class));
        }
        return equipmentList;
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