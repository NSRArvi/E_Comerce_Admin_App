package com.example.e_comerce_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_comerce_admin.databinding.CategoryRowBinding;

public class CategoryAdapter extends ListAdapter<String, CategoryAdapter.CategoryViewHolder> {
    public CategoryAdapter() {
        super(new ProductDiff());
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final CategoryRowBinding binding = CategoryRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private CategoryRowBinding binding;
        public CategoryViewHolder(CategoryRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(String category) {
            binding.rowCategoryNameTV.setText(category);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<String>{
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }
}
