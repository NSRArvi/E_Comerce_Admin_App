package com.example.e_comerce_admin.repository;

import android.util.Log;

import com.example.e_comerce_admin.callbacks.OnCheckAdminListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminRepository {
    private static final String TAG = AdminRepository.class.getSimpleName();
    private static final String COLLECTION_ADMIN = "Admins";
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void checkAdmin(String uid, OnCheckAdminListener listener) {
        db.collection(COLLECTION_ADMIN)
                .document(uid)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        if (task.getResult().exists()) {
                            listener.doesAdminExist(true);
                            Log.e(TAG, "exists");
                        }else {
                            listener.doesAdminExist(false);
                            Log.e(TAG, "does not exist");
                        }

                    }
                });
    }

}
