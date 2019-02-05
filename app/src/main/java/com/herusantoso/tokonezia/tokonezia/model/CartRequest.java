package com.herusantoso.tokonezia.tokonezia.model;

import com.google.gson.annotations.SerializedName;

public class CartRequest {

    @SerializedName("product_id")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
