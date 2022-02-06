package com.example.e_comerce_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_comerce_admin.callbacks.OnProductItemClickListener;
import com.example.e_comerce_admin.models.ProductModel;
import com.example.e_comerce_admin.databinding.ProductRowBinding;


public class ProductAdapter extends ListAdapter<ProductModel, ProductAdapter.ProductViewHolder> {
    private OnProductItemClickListener listener;
    public ProductAdapter(Fragment fragment) {
        super(new ProductDiff());
        listener = (OnProductItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ProductRowBinding binding = ProductRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ProductRowBinding binding;
        public ProductViewHolder(ProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.productRowCardView.setOnClickListener(v -> {
                listener.onProductItemClicked(
                        getItem(getAdapterPosition()).getProductId());
            });
        }
        public void bind(ProductModel productModel) {
            binding.setProduct(productModel);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<ProductModel>{
        @Override
        public boolean areItemsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductModel oldItem, @NonNull ProductModel newItem) {
            return oldItem.getProductId().equals(newItem.getProductId());
        }
    }
}
