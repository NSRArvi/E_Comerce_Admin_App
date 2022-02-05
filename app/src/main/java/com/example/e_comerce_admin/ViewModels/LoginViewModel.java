package com.example.e_comerce_admin.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_comerce_admin.CallBacks.OnCheckAdminListener;
import com.example.e_comerce_admin.Repository.AdminRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginViewModel extends ViewModel {
    public enum AuthState {
        AUTHENTICATED, UNAUTHENTICATED
    }
    public MutableLiveData<AuthState> authLiveData;
    private MutableLiveData<String> errMsgLiveData;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public LoginViewModel() {
        authLiveData = new MutableLiveData<>();
        errMsgLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            authLiveData.postValue(AuthState.UNAUTHENTICATED);
        }else {
            authLiveData.postValue(AuthState.AUTHENTICATED);
        }
    }

    public FirebaseUser getUser() {
        return user;
    }

    public LiveData<String> getErrMsgLiveData() {
        return errMsgLiveData;
    }

    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener(authResult -> {
            checkAdmin(authResult.getUser().getUid());
        }).addOnFailureListener(e -> {
            errMsgLiveData.postValue(e.getLocalizedMessage());
        });
    }

    private void checkAdmin(String uid) {
        AdminRepository.checkAdmin(uid, status -> {
            if (status) {
                user = auth.getCurrentUser();
                authLiveData.postValue(AuthState.AUTHENTICATED);
            }else {
                errMsgLiveData.postValue("You are not an Admin");
                auth.signOut();
            }
        });
    }

    public void logout() {
        auth.signOut();
        authLiveData.postValue(AuthState.UNAUTHENTICATED);
    }
}
