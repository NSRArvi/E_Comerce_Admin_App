package com.example.e_comerce_admin.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_comerce_admin.models.EcomUser;
import com.example.e_comerce_admin.utils.Constants;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewModel extends ViewModel {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MutableLiveData<List<EcomUser>> userListLiveData =
            new MutableLiveData<>();
    private MutableLiveData<EcomUser> userLiveData =
            new MutableLiveData<>();

    public CustomerViewModel() {
        getAllCustomers();
    }

    private void getAllCustomers() {
        db.collection(Constants.DbCollection.COLLECTION_USERS)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    final List<EcomUser> users = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        users.add(doc.toObject(EcomUser.class));
                    }
                    userListLiveData.postValue(users);
                });
    }

    public void getCustomerById(String uid) {
        db.collection(Constants.DbCollection.COLLECTION_USERS)
                .document(uid)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    userLiveData.postValue(value.toObject(EcomUser.class));
                });
    }

    public MutableLiveData<List<EcomUser>> getUserListLiveData() {
        return userListLiveData;
    }

    public MutableLiveData<EcomUser> getUserLiveData() {
        return userLiveData;
    }
}
