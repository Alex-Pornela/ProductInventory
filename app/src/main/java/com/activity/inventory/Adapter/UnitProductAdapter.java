package com.activity.inventory.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.inventory.model.ProductUnits;
import com.activity.inventory.R;

import java.util.List;

public class UnitProductAdapter extends RecyclerView.Adapter<UnitProductAdapter.myViewHolder> {

    List<ProductUnits> unitList;

    public UnitProductAdapter( List<ProductUnits> unitList){
        this.unitList = unitList;
    }

    @NonNull
    @Override
    public UnitProductAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.unit_list_layout, parent, false );
        return new myViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull UnitProductAdapter.myViewHolder holder, int position) {
        holder.name.setText( unitList.get( position ).getProduct_name());
        holder.unit.setText( unitList.get( position ).getUnit_of_measure() );
        holder.price.setText( unitList.get( position ).getPrice() );
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,unit;
        public myViewHolder(@NonNull View itemView) {
            super( itemView );

            name = itemView.findViewById( R.id.productName);
            unit = itemView.findViewById( R.id.productUnit );
            price = itemView.findViewById( R.id.productPrice );
        }
    }
}
