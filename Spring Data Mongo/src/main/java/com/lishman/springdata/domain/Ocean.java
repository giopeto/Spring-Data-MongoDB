package com.lishman.springdata.domain;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="oceans")
@TypeAlias("ocean")
public class Ocean extends AbstractDocument {

    @Indexed(name="oceanName", unique=true)
    private String name;
    private int area;
    
    @PersistenceConstructor
    public Ocean(String name, int area) {
        setName(name);
        setArea(area);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setArea(int area) {
        this.area = area;
    }
    
    public int getarea() {
        return area;
    }
    
    public String toString() {
        return getName();
    }
}