package com.example.country_state_city_info.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Country_ implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sortname")
    @Expose
    private String sortname;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneCode")
    @Expose
    private Integer phoneCode;
    private final static long serialVersionUID = 6155832161048993939L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("sortname", sortname).append("name", name).append("phoneCode", phoneCode).toString();
    }

}