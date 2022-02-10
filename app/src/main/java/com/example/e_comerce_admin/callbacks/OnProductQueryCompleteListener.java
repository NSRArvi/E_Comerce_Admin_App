package com.example.e_comerce_admin.callbacks;

import com.example.e_comerce_admin.models.ProductModel;

import java.util.List;

public interface OnProductQueryCompleteListener {
    void onProductQueryComplete(List<ProductModel> items);
}
