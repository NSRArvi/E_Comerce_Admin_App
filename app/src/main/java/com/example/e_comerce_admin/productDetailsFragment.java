package com.example.e_comerce_admin;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.e_comerce_admin.Models.PurchaseModel;
import com.example.e_comerce_admin.ViewModels.ProductViewModel;
import com.example.e_comerce_admin.databinding.FragmentProductDetailsBinding;


public class productDetailsFragment extends Fragment {

    private FragmentProductDetailsBinding binding;
    private ProductViewModel productViewModel;
    private String productId;
    public productDetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailsBinding.inflate(inflater);
        productViewModel = new ViewModelProvider(requireActivity())
                .get(ProductViewModel.class);
        productId = getArguments().getString("pid");
        productViewModel.getProductByProductId(productId)
                .observe(getViewLifecycleOwner(), productModel -> {
                    binding.setProduct(productModel);
                });
        productViewModel.getPurchasesByProductId(productId)
                .observe(getViewLifecycleOwner(), purchaseModels -> {
                    String purchaseHistory = "";
                    for (PurchaseModel p : purchaseModels) {
                        purchaseHistory += "Purchased on: " +
                                p.getPurchaseDate()+"\n"+
                                "for BDT "+p.getPurchasePrice()+"\n"+
                                "with quantity: "+p.getPurchaseQuantity()+"\n\n";
                        binding.purchaseHistory.setText(purchaseHistory);
                    }
                });
        binding.updatePriceBtn.setOnClickListener(v -> {
            showUpdatePriceAlertDialog();
        });
        return binding.getRoot();

    }

    private void showUpdatePriceAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Update Price");
        final EditText editText = new EditText(getActivity());
        editText.setHint("Enter new price");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Update", (dialog, which) -> {
            final String price = editText.getText().toString();
            //validate
            productViewModel.updateProductPrice(productId, Double.parseDouble(price));
        });
        builder.setNegativeButton("Cancel", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}