package com.activity.inventory.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.activity.inventory.model.Product;
import com.activity.inventory.Repository.ApiRequest;

import java.util.List;

public class ProductViewModel extends ViewModel implements ApiRequest.ViewData {

    ApiRequest repo;
    MutableLiveData<List<Product>> productLiveData = new MutableLiveData<>();

    public ProductViewModel(){
        repo = new ApiRequest(this);
        repo.viewProduct();
    }

    public LiveData<List<Product>> getProductLiveData(){
        return productLiveData;
    }

    public void addProduct(String productCode, String productName){
        repo.addProduct( productCode,productName );

    }

    @Override
    public void onViewProduct(List<Product> productList) {
        productLiveData.postValue(productList)  ;
    }
}
