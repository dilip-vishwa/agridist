package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.UserInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class UserInfoService {

    public static final String COL_NAME="userinfos";

    public String saveUserInfoDetails(UserInfo userInfo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        userInfo.setUserInfo_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(userInfo);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<UserInfo> getUserInfos() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<UserInfo> userInfoList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            userInfoList.add(document.toObject(UserInfo.class));
        }
        return userInfoList;
    }
    public UserInfo getUserInfoDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        UserInfo userInfo = null;

        if(document.exists()) {
            userInfo = document.toObject(UserInfo.class);
            return userInfo;
        }else {
            return null;
        }
    }

    public String updateUserInfoDetails(UserInfo userInfo, String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(userInfo);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteUserInfo(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with UserInfo ID "+id+" has been deleted";
    }

}