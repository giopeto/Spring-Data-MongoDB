package com.lishman.springdata.domain;


public final class Continent {
    
    private final long id;
    private final String name;
    
    public Continent(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
        return this.getName();
    }
}