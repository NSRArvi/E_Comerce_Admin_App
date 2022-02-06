package com.example.e_comerce_admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.e_comerce_admin.adapters.ProductAdapter;
import com.example.e_comerce_admin.callbacks.OnProductItemClickListener;
import com.example.e_comerce_admin.viewmodels.ProductViewModel;
import com.example.e_comerce_admin.databinding.FragmentProductListBinding;

import java.util.ArrayList;
import java.util.List;


public class productListFragment extends Fragment
    implements OnProductItemClickListener {

    private FragmentProductListBinding binding;
    private ProductViewModel productViewModel;
    private String category;


    public productListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(inflater);
        productViewModel = new ViewModelProvider(requireActivity())
                .get(ProductViewModel.class);
        final ProductAdapter adapter = new ProductAdapter(this);
        binding.productRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.productRV.setAdapter(adapter);
        productViewModel.productListLiveData.observe(getViewLifecycleOwner(),
                productList -> {
                    adapter.submitList(productList);
                });
        productViewModel.categoryListLiveData.observe(
                getViewLifecycleOwner(), catList -> {
                    final List<String> categories = new ArrayList<>();
                    categories.add("All");
                    categories.addAll(catList);
                    final ArrayAdapter<String> spinnerAdapter =
                            new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    categories);
                    binding.searchCategorySP.setAdapter(spinnerAdapter);
                });

        binding.searchCategorySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    category = parent.getItemAtPosition(position).toString();
                    productViewModel.getAllProductsByCategory(category);
                }else {
                    productViewModel.getAllProducts();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return binding.getRoot();
    }
    @Override
    public void onProductItemClicked(String productId) {
        final Bundle bundle = new Bundle();
        bundle.putString("pid", productId);
        Navigation.findNavController(getActivity(), R.id.fragmentContainerView)
                .navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle);
    }
}