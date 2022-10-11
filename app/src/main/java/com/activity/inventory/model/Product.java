package com.activity.inventory.model;
//import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;


public class Product {

    private String id;
    private String product_name;
    private String product_code;
    private ArrayList<ProductUnits> product_unit;

    public Product(String id, String product_name, String product_code, ArrayList<ProductUnits> product_unit) {
        this.id = id;
        this.product_name = product_name;
        this.product_code = product_code;
        this.product_unit = product_unit;
    }

    public String getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public ArrayList<ProductUnits> getProduct_unit() {
        return product_unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals( product.id ) &&
                product_name.equals( product.product_name ) &&
                product_code.equals( product.product_code ) &&
                product_unit.equals( product.product_unit );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, product_name, product_code, product_unit );
    }
}