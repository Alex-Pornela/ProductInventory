package com.activity.inventory.Repository;

import androidx.annotation.NonNull;

import com.activity.inventory.model.Product;
import com.activity.inventory.model.ProductUnits;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {
    private final OkHttpClient mClient = new OkHttpClient();
   // private Gson gson;
    List<Product> productList= new ArrayList<>();
    ViewData viewData;
    ArrayList<ProductUnits> unitList = new ArrayList<>();


    public ApiRequest(ViewData viewData ){
        this.viewData = viewData;
    }

    public void viewProduct(){

       // gson = new Gson();
        Request request = new Request.Builder().url( URLManager.PRODUCT_LIST_API ).build();
        mClient.newCall( request ).enqueue( new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String data = response.body().string();

                    //manual parsing
                    try {
                        JSONArray products = new JSONArray(data);
                        for(int i=0; i< products.length(); i++){
                            JSONObject product = products.getJSONObject( i );
                            String id = product.getString( "id" );
                            String name = product.getString( "product_name" );
                            String code = product.getString( "product_code" );

                            //unit of measure
                            JSONArray productUnit = product.getJSONArray( "product_unit" );
                            for (int k=0; k< productUnit.length(); k++){
                                JSONObject unit = productUnit.getJSONObject( k );
                                String unitId = unit.getString( "id" );
                                String productID = unit.getString( "product_id" );
                                String productName = unit.getString( "product_name" );
                                String unitMeasure = unit.getString( "unit_of_measure" );
                                String price = unit.getString( "price" );

                                unitList.add( new ProductUnits( unitId, productID, price, productName, unitMeasure ) );
                            }
                            productList.add( new Product( id, name, code, unitList ) );

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //using gson library
                    //productList = gson.fromJson(data,  new TypeToken<List<Product>>(){}.getType());
                    viewData.onViewProduct( productList );
                }
            }
        } );
    }

    public void addProduct(String productCode, String productName){

        RequestBody requestBody = new FormBody.Builder()
                .add("product_code", productCode)
                .add("product_name", productName)
                .build();

        Request request = new Request.Builder()
                .url(URLManager.ADD_PRODUCT)
                .post(requestBody)
                .build();

        mClient.newCall( request ).enqueue( new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            }
        } );
    }

    public interface ViewData{
        void onViewProduct(List<Product>productList);
    }
}
