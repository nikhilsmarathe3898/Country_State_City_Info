package com.example.country_state_city_info.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable
{

@SerializedName("countries")
@Expose
private List<Country_> countries = null;
private final static long serialVersionUID = 8107188439368299490L;

public List<Country_> getCountries() {
return countries;
}

public void setCountries(List<Country_> countries) {
this.countries = countries;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("countries", countries).toString();
}

}