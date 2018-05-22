package com.ptteng.bo;

import java.io.Serializable;

/**
 * 产品单选框
 */
public class ProductSelectBO implements Serializable {
    private Long productId;
    private String productName;

    @Override
    public String toString() {
        return "ProductSelectBO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                '}';
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
