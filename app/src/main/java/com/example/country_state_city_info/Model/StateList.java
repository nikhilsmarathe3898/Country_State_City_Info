package com.example.country_state_city_info.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;

public class StateList extends ArrayList<StateList> implements Serializable
{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    private final static long serialVersionUID = -1263671776157960410L;

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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("countryId", countryId).toString();
    }
}