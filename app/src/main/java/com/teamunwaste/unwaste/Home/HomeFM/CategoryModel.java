package com.teamunwaste.unwaste.Home.HomeFM;

import java.util.List;

public class CategoryModel {

    String categoryId;
    String categoryTitle;
    String categoryImage;
    List<ProductModel> list;

    public CategoryModel() {
    }

    public CategoryModel(String categoryId, String categoryTitle, String categoryImage, List<ProductModel> list) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryImage = categoryImage;
        this.list = list;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public List<ProductModel> getList() {
        return list;
    }
}
