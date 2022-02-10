package com.example.e_comerce_admin.callbacks;

import com.example.e_comerce_admin.models.PurchaseModel;

import java.util.List;

public interface OnPurchaseQueryCompleteListener {
    void onPurchaseQueryComplete(List<PurchaseModel> purchaseModels);

}
