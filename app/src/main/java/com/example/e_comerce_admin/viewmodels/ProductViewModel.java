package com.example.e_comerce_admin.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_comerce_admin.models.ProductModel;
import com.example.e_comerce_admin.models.PurchaseModel;
import com.example.e_comerce_admin.utils.Constants;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;


import java.util.ArrayList;
import java.util.List;

public class ProductViewModel extends ViewModel {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public MutableLiveData<List<String>> categoryListLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ProductModel>> productListLiveData = new MutableLiveData<>();

    private final String TAG = ProductViewModel.class.getSimpleName();
    public ProductViewModel() {
        getAllCategories();
        getAllProducts();
    }

    public void addNewProduct(ProductModel productModel, PurchaseModel purchaseModel) {
        final WriteBatch writeBatch = db.batch();
        final DocumentReference pDoc = db.collection(
                Constants.DbCollection.COLLECTION_PRODUCT).document();
        productModel.setProductId(pDoc.getId());

        final DocumentReference purDoc = db.collection(
                Constants.DbCollection.COLLECTION_PURCHASE).document();
        purchaseModel.setPurchaseId(purDoc.getId());
        purchaseModel.setProductId(pDoc.getId());

        writeBatch.set(pDoc, productModel);
        writeBatch.set(purDoc, purchaseModel);

        writeBatch.commit().addOnSuccessListener(unused -> {
            Log.e(TAG, "product saved");
        }).addOnFailureListener(e -> {

        });
    }

    private void getAllCategories() {
        db.collection(Constants.DbCollection.COLLECTION_CATEGORY)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    final List<String> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.get("name", String.class));
                    }
                    categoryListLiveData.postValue(items);
                });
    }

    public void getAllProducts() {
        db.collection(Constants.DbCollection.COLLECTION_PRODUCT)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;

                    final List<ProductModel> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.toObject(ProductModel.class));
                    }

                    productListLiveData.postValue(items);
                });
    }
    public void getAllProductsByCategory(String category) {
        db.collection(Constants.DbCollection.COLLECTION_PRODUCT)
                .whereEqualTo("category", category)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;

                    final List<ProductModel> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.toObject(ProductModel.class));
                    }

                    productListLiveData.postValue(items);
                });
    }

    public LiveData<ProductModel> getProductByProductId(String productId) {
        final MutableLiveData<ProductModel> productLiveData =
                new MutableLiveData<>();
        db.collection(Constants.DbCollection.COLLECTION_PRODUCT)
                .document(productId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    productLiveData.postValue(
                            value.toObject(ProductModel.class));
                });
        return productLiveData;
    }

    public LiveData<List<PurchaseModel>> getPurchasesByProductId(String productId) {
        final MutableLiveData<List<PurchaseModel>> purchaseListLD =
                new MutableLiveData<>();
        db.collection(Constants.DbCollection.COLLECTION_PURCHASE)
                .whereEqualTo("productId", productId)
                .addSnapshotListener((value, error) -> {
                    if (error != null) return;
                    final List<PurchaseModel> items = new ArrayList<>();
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        items.add(doc.toObject(PurchaseModel.class));
                    }
                    purchaseListLD.postValue(items);
                });
        return purchaseListLD;
    }

    public void updateProductPrice(String productId, double price) {
        final DocumentReference doc = db.collection(Constants.DbCollection.COLLECTION_PRODUCT)
                .document(productId);
        doc.update("price", price)
                .addOnSuccessListener(unused -> {

                }).addOnFailureListener(e -> {

                });
    }
}
