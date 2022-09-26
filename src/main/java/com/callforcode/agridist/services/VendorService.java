package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Vendor;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class VendorService {

    public static final String COL_NAME="vendors";

    public String saveVendorDetails(Vendor vendor) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        vendor.setVendor_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(vendor);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<Vendor> getVendors() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Vendor> vendorList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            vendorList.add(document.toObject(Vendor.class));
        }
        return vendorList;
    }
    public Vendor getVendorDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Vendor vendor = null;

        if(document.exists()) {
            vendor = document.toObject(Vendor.class);
            return vendor;
        }else {
            return null;
        }
    }

    public String updateVendorDetails(Vendor vendor, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(vendor);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteVendor(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Vendor ID "+id+" has been deleted";
    }

}