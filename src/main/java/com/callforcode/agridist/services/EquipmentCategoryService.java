package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.EquipmentCategory;
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
public class EquipmentCategoryService {

    public static final String COL_NAME="equipmentcategorys";

    public EquipmentCategory saveEquipmentCategoryDetails(EquipmentCategory equipmentCategory) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        equipmentCategory.setEquipmentCategory_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipmentCategory);
        return equipmentCategory;
    }

    public List<EquipmentCategory> getEquipmentCategorys() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<EquipmentCategory> equipmentCategoryList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            equipmentCategoryList.add(document.toObject(EquipmentCategory.class));
        }
        return equipmentCategoryList;
    }
    public Optional<EquipmentCategory> getEquipmentCategoryDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return Optional.ofNullable(future.get().toObject(EquipmentCategory.class));
    }

    public String updateEquipmentCategoryDetails(EquipmentCategory equipmentCategory, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(equipmentCategory);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteEquipmentCategory(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with EquipmentCategory ID "+id+" has been deleted";
    }

}