package com.lishman.springdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lishman.springdata.domain.Continent;

public interface ContinentRepository extends CrudRepository<Continent, Long> {
    
    public Continent findByName(String name);

    public List<Continent> findByNameStartingWithIgnoreCase(String start);

	public List<Continent> findByNameStartingWith(String string);
    
}