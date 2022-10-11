package com.activity.inventory.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.activity.inventory.Adapter.ProductAdapter;
import com.activity.inventory.Adapter.ProductListAdapter;
import com.activity.inventory.model.Product;
import com.activity.inventory.model.ProductUnits;
import com.activity.inventory.R;
import com.activity.inventory.ViewModel.ProductViewModel;
import com.activity.inventory.databinding.ActivityMainBinding;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    List<Product> productList = new ArrayList<>();
    List<ProductUnits> unitList = new ArrayList<>();
    ProductAdapter adapter;
    ProductViewModel viewModel;
    Dialog dialog;
    private ProductListAdapter productListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );


        loadData();
        binding.addProduct.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        } );

    }

    private void openDialog() {

        dialog = new Dialog( this );
        dialog.setContentView( R.layout.add_product_layout );
        dialog.getWindow().setBackgroundDrawable(  new ColorDrawable( Color.TRANSPARENT ) );

        EditText productCode = dialog.findViewById( R.id.product_code );
        EditText productName = dialog.findViewById( R.id.product_name );
        Button cancel = dialog.findViewById( R.id.closeDialog );
        Button addBtn = dialog.findViewById( R.id.addBtn );


        addBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product_code = productCode.getText().toString();
                String product_name = productName.getText().toString();
                viewModel.addProduct( product_code,product_name );
                //loadData();
                dialog.dismiss();
            }
        } );

        cancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        } );

        dialog.show();
    }

    private void loadData() {
        binding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        binding.recyclerView.setHasFixedSize( true );
        //adapter = new ProductAdapter( this, productList);
        productListAdapter = new ProductListAdapter(ProductListAdapter.itemCallBack);
        //binding.recyclerView.setAdapter( adapter );
        binding.recyclerView.setAdapter( productListAdapter );
        viewModel = new ViewModelProvider(this).get( ProductViewModel.class );
        viewModel.getProductLiveData().observe( this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                //productList = products;
                //adapter.updateAdapter( products);
                productListAdapter.submitList( products );
            }
        } );
    }



}