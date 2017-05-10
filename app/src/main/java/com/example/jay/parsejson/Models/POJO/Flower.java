package com.example.jay.parsejson.Models.POJO;

/**
 * Created by jay on 5/5/17.
 */

public class Flower {

    public String category, instructions, photo, name;
    public double price;
    public int productId; // product_id

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String mCategory, mInstructions, mPhoto, mName;
    public double mPrice;
    public int mProductId;

    private Flower(Builder builder){
        mCategory = builder.mCategory;
        mInstructions = builder.mInstructions;
        mPhoto = builder.mPhoto;
        mName = builder.mName;
        mPrice = builder.mPrice;
        mProductId = builder.mProductId;
    }

    public static class Builder {

        private String mCategory, mInstructions, mPhoto, mName;
        private double mPrice;
        private int mProductId;

        public Builder setCategory(String category) {
            mCategory = category;
            return Builder.this;
        }

        public Builder setInstructions(String instructions) {
            mInstructions = instructions;
            return Builder.this;
        }

        public Builder setPhoto(String photo) {
            mPhoto = photo;
            return Builder.this;
        }

        public Builder setName(String name) {
            mName = name;
            return Builder.this;
        }

        public Builder setPrice(double price) {
            mPrice = price;
            return Builder.this;
        }

        public Builder setProductId(int productId) {
            mProductId = productId;
            return Builder.this;
        }

        public Flower build() {
            return new Flower(Builder.this);
        }
    }
}