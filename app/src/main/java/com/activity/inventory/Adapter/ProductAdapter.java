package com.activity.inventory.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.activity.inventory.model.Product;
import com.activity.inventory.model.ProductUnits;
import com.activity.inventory.R;
import com.activity.inventory.View.ProductUnit;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolder> {

    List<Product> productList;
    Context context;
    private ArrayList<ProductUnits> unitList = new ArrayList<>();



    @SuppressLint("NotifyDataSetChanged")
    public ProductAdapter(Context context, List<Product> productList){
        this.productList = productList;
        this.context = context;
        notifyDataSetChanged();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateAdapter(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.product_list_layout, parent, false );
        View view = null;
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.productName.setText( productList.get( position ).getProduct_name() );
        holder.productCode.setText( productList.get( position ).getProduct_code() );
        holder.productId.setText( productList.get( position ).getId() );

        holder.view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = productList.get( position );
                List<ProductUnits> units = product.getProduct_unit();
                for(ProductUnits i: units){
                    if(i.getProduct_name().equals( productList.get( position ).getProduct_name() )){
                       unitList.add( new ProductUnits( i.getId(), i.getProduct_id(), i.getPrice(), i.getProduct_name(), i.getUnit_of_measure()) );
                    }
                }
                Intent intent = new Intent( context, ProductUnit.class );
                intent.putExtra(  "productUnit", unitList );
                context.startActivity( intent );


            }
        } );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productCode, productId;
        MaterialButton update, view, delete;

        public myViewHolder(@NonNull View itemView) {
            super( itemView );

            productName = itemView.findViewById( R.id.productName );
            productCode = itemView.findViewById( R.id.productCode );
            productId = itemView.findViewById( R.id.productId );
            update = itemView.findViewById( R.id.updateBtn );
            view = itemView.findViewById( R.id.viewBtn );
            delete = itemView.findViewById( R.id.deletebtn );
        }
    }

}
