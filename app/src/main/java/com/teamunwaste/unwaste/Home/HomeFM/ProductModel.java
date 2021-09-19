package com.teamunwaste.unwaste.Home.HomeFM;

public class ProductModel {

    String categoryId;
    String productId;
    String productTitle;
    String productImage;
    String productDescription;
    String productPrice;
    String productStatus;
    String userName;
    String userPhone;
    String userEmail;

    public ProductModel() {
    }

    public ProductModel(String categoryId, String productId, String productTitle, String productImage, String productDescription, String productPrice, String productStatus, String userName, String userPhone, String userEmail) {
        this.categoryId = categoryId;
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImage = productImage;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
