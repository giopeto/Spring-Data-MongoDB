package com.lishman.springdata.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="medals")
@TypeAlias("medal")
public class OlympicMedals extends AbstractDocument {
    
    public enum MedalType {BRONZE, SILVER, GOLD}
    
    @Field("name")
    private String countryName;

    private List<Integer> medals = new ArrayList<Integer>();


    public OlympicMedals(String countryName, int gold, int silver, int bronze) {
        this.setCountryName(countryName);
        medals.add(bronze);
        medals.add(silver);
        medals.add(gold);
    }
    
    @PersistenceConstructor
    public OlympicMedals(String countryName, List<Integer> medals) {
        this.setCountryName(countryName);
        this.medals = medals;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }
    
    public int getMedalCount(MedalType type) {
        return medals.get(type.ordinal());
    }
    
}