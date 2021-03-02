package com.example.country_state_city_info.Model;

import com.google.gson.annotations.SerializedName;

public class StatesListModel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("country_id")
    private String country_id;

    public StatesListModel(String id, String name, String country_id) {
        this.id = id;
        this.name = name;
        this.country_id = country_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "StatesListModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country_id='" + country_id + '\'' +
                '}';
    }

    /* "id": "1",
            "name": "Andaman and Nicobar Islands",
            "country_id": "101"*/
}
