package com.activity.inventory.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.inventory.model.Product;
import com.activity.inventory.databinding.ProductListLayoutBinding;

public class ProductListAdapter extends ListAdapter<Product, ProductListAdapter.ProductViewHolder> {

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<Product> diffCallback) {
        super( diffCallback );
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        ProductListLayoutBinding productListLayoutBinding = ProductListLayoutBinding.inflate( layoutInflater, parent, false );
        return new ProductViewHolder( productListLayoutBinding );
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem( position );
        holder.productListLayoutBinding.setProduct( product );
    }

    public static final DiffUtil.ItemCallback<Product> itemCallBack = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals( newItem.getId() );
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals( newItem );
        }
    };

    static class ProductViewHolder extends RecyclerView.ViewHolder{

        ProductListLayoutBinding productListLayoutBinding;

        public ProductViewHolder(@NonNull ProductListLayoutBinding productListLayoutBinding) {
            super( productListLayoutBinding.getRoot() );
            this.productListLayoutBinding = productListLayoutBinding;
        }
    }
}
