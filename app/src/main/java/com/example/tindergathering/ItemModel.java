package com.example.tindergathering;

public class ItemModel {
    private int image;
    private String name, age, city, formats;

    public ItemModel() {
    }

    public ItemModel(int image, String name, String age, String city, String formats) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.city = city;
        this.formats = formats;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getFormats() {
        return formats;
    }
}
