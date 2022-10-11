package com.activity.inventory.model;

import java.io.Serializable;

public class ProductUnits implements Serializable {
    private String id;
    private String product_id;
    private String price;
    private String product_name;
    private String unit_of_measure;



    public ProductUnits(String id, String product_id, String price, String product_name, String unit_of_measure) {
        this.id = id;
        this.product_id = product_id;
        this.price = price;
        this.product_name = product_name;
        this.unit_of_measure = unit_of_measure;
    }

    public String getId() {
        return id;
    }

    public ProductUnits(String price, String product_name, String unit_of_measure) {
        this.price = price;
        this.product_name = product_name;
        this.unit_of_measure = unit_of_measure;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getPrice() {
        return price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getUnit_of_measure() {
        return unit_of_measure;
    }
}
