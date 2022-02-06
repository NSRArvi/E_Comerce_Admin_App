package com.example.e_comerce_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_comerce_admin.callbacks.OnDashboardItemSelectedListener;
import com.example.e_comerce_admin.models.DashboardItem;
import com.example.e_comerce_admin.databinding.DashboardItemRowBinding;

import java.util.List;


public class DashboardAdapter extends
        RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private final List<DashboardItem> items;
    private final OnDashboardItemSelectedListener listener;

    public DashboardAdapter(Fragment fragment, List<DashboardItem> items){
        this.items = items;
        this.listener = (OnDashboardItemSelectedListener) fragment;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final DashboardItemRowBinding binding =
                DashboardItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new DashboardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {
        private final DashboardItemRowBinding binding;
        public DashboardViewHolder(DashboardItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.dashItem.setOnClickListener(v -> {
                final int position = getAdapterPosition();
                listener.onDashboardItemSelect(items.get(position).getItemName());
            });
        }

        public void bind(DashboardItem item) {
            binding.setItem(item);
        }
    }
}
