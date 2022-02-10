package com.example.e_comerce_admin.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_comerce_admin.callbacks.OnActionCompleteListener;
import com.example.e_comerce_admin.models.CartModel;
import com.example.e_comerce_admin.models.OrderModel;
import com.example.e_comerce_admin.models.OrderSettingsModel;
import com.example.e_comerce_admin.utils.Constants;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends ViewModel {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MutableLiveData<OrderSettingsModel> settingsModelMutableLiveData =
            new MutableLiveData<>();
    private MutableLiveData<List<OrderModel>> orderListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CartModel>> cartListLiveData = new MutableLiveData<>();
    private MutableLiveData<OrderModel> orderLiveData = new MutableLiveData<>();
    private OrderSettingsModel orderSettingsModel;
    public MutableLiveData<OrderSettingsModel> getSettingsModelMutableLiveData() {
        return settingsModelMutableLiveData;
    }

    public OrderViewModel() {
        getAllOrders();
    }

    public void getOrderSettings() {
        db.collection(Constants.DbCollection.COLLECTION_ORDER_SETTINGS)
                .document(Constants.DbCollection.DOCUMENT_ORDER_SETTING)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    settingsModelMutableLiveData.postValue(
                            value.toObject(OrderSettingsModel.class));
                });

    }

    public void getAllOrders() {
        db.collection(Constants.DbCollection.COLLECTION_ORDERS)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;

                    final List<OrderModel> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.toObject(OrderModel.class));
                    }

                    orderListLiveData.postValue(items);
                });
    }

    public void getOrderByOrderId(String orderId) {
        db.collection(Constants.DbCollection.COLLECTION_ORDERS)
                .document(orderId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    orderLiveData.postValue(value.toObject(OrderModel.class));
                });
    }

    public void getSpecificOrderDetails(String orderId) {
        db.collection(Constants.DbCollection.COLLECTION_ORDERS)
                .document(orderId)
                .collection(Constants.DbCollection.COLLECTION_ORDER_DETAILS)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    final List<CartModel> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.toObject(CartModel.class));
                    }

                    cartListLiveData.postValue(items);
                });
    }

    public void updateOderStatus(String orderId, String status, OnActionCompleteListener listener) {
        db.collection(Constants.DbCollection.COLLECTION_ORDERS)
                .document(orderId)
                .update("orderStatus", status)
                .addOnSuccessListener(unused -> {
                    listener.onSuccess();
                })
                .addOnFailureListener(unused -> {
                    listener.onFailure();
                });
    }

    public OrderSettingsModel getOrderSettingsModel() {
        return orderSettingsModel;
    }

    public void setOrderSettingsModel(OrderSettingsModel orderSettingsModel) {
        this.orderSettingsModel = orderSettingsModel;
    }

    public MutableLiveData<List<OrderModel>> getOrderListLiveData() {
        return orderListLiveData;
    }

    public MutableLiveData<List<CartModel>> getCartListLiveData() {
        return cartListLiveData;
    }

    public MutableLiveData<OrderModel> getOrderLiveData() {
        return orderLiveData;
    }
}
