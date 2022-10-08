package com.callforcode.agridist.services;
import com.callforcode.agridist.entities.Document;
import com.callforcode.agridist.entities.UserInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class UserInfoService {

    public static final String COL_NAME="userinfos";

    public UserInfo saveUserInfoDetails(UserInfo userInfo) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        String id = dbFirestore.collection(COL_NAME).document().getId();
        userInfo.setUserInfo_id(id);
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(id).set(userInfo);
        return userInfo;
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
    public Optional<UserInfo> getUserInfoDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        return Optional.ofNullable(future.get().toObject(UserInfo.class));
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