package com.example.e_comerce_admin.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_comerce_admin.callbacks.OnOrderItemClickListener;
import com.example.e_comerce_admin.databinding.OrderRowBinding;
import com.example.e_comerce_admin.models.OrderModel;


public class OrderAdapter extends ListAdapter<OrderModel, OrderAdapter.OrderViewHolder> {
    private final OnOrderItemClickListener orderItemClickListener;
    public OrderAdapter(OnOrderItemClickListener listener) {
        super(new ProductDiff());
        this.orderItemClickListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final OrderRowBinding binding = OrderRowBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(v -> {
            orderItemClickListener.onOrderItemClicked(getItem(position).getOrderId());
        });
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        private OrderRowBinding binding;
        public OrderViewHolder(OrderRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(OrderModel orderModel) {
            binding.setOrder(orderModel);
        }
    }

    static class ProductDiff extends DiffUtil.ItemCallback<OrderModel>{
        @Override
        public boolean areItemsTheSame(@NonNull OrderModel oldItem, @NonNull OrderModel newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderModel oldItem, @NonNull OrderModel newItem) {
            return oldItem.getOrderId().equals(newItem.getOrderId());
        }
    }
}
