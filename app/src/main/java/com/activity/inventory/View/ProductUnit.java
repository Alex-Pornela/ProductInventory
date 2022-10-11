package com.activity.inventory.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.activity.inventory.Adapter.UnitProductAdapter;
import com.activity.inventory.model.ProductUnits;
import com.activity.inventory.databinding.ActivityProductUnitBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductUnit extends AppCompatActivity {

    private ActivityProductUnitBinding binding;
    UnitProductAdapter adapter;
    List<ProductUnits> unitList = new ArrayList<>();
    ProductUnits unitModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = ActivityProductUnitBinding.inflate( getLayoutInflater() );
        setContentView(binding.getRoot() );

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        unitList = (List<ProductUnits>) bundle.getSerializable( "productUnit" );

         for(ProductUnits model: unitList){
             if(model.getProduct_name().equals( unitList.get( 0 ).getProduct_name() )){
                 unitModel = model;
             }
         }

        displayData();

        binding.backBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductUnit.this, MainActivity.class);
                startActivity( intent );
                finish();
            }
        } );
    }

    private void displayData() {
        binding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        binding.recyclerView.setHasFixedSize( true );
        adapter = new UnitProductAdapter(  unitList );
        binding.recyclerView.setAdapter( adapter );
    }
}