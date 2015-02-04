package com.lishman.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lishman.springdata.TestData;
import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.City;
import com.lishman.springdata.domain.Country;
import com.lishman.springdata.repository.CityRepository;

@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CityRepositoryTest {

    @Autowired private CityRepository cityRepo;
    @Autowired private MongoOperations mongoOps;
    
    @Before
    public void reset() {
        TestData.countries();
        TestData.cities();
    }
    
    //------------------------------------------------- findByName

    @Test
    public void testFindByName() {
        City tokyo = cityRepo.findByName("Tokyo");
        assertThat(tokyo.getName(), equalTo("Tokyo"));
    }

    //------------------------------------------------- count
    
    @Test
    public void testCount() {
        assertThat(cityRepo.count(), equalTo(5L));
    }
    
    //------------------------------------------------- exists
    
    @Test
    public void testExists() {
        assertThat(cityRepo.exists(BigInteger.valueOf(2)), equalTo(true));
        assertThat(cityRepo.exists(BigInteger.valueOf(22)), equalTo(false));
    }
    
    //------------------------------------------------- find one
    
    @Test
    public void testFindOne() {
        City boston = cityRepo.findOne(BigInteger.valueOf(5));
        assertThat(boston.getName(), equalTo("Boston"));
    }
    
    //------------------------------------------------- find all
    
    @Test
    public void testFindAll() {
        Iterable<City> cities = cityRepo.findAll();
        assertThat(cities.toString(), equalTo("[Tokyo, Munich, Berlin, New York, Boston]"));
    }
    
    @Test
    public void testFindAllWithIds() {
        List<BigInteger> ids = new ArrayList<BigInteger>();
        ids.add(BigInteger.valueOf(2));
        ids.add(BigInteger.valueOf(4));
        
        Iterable<City> cities = cityRepo.findAll(ids);
        assertThat(cities.toString(), equalTo("[Munich, New York]"));
    }
    
    //------------------------------------------------- insert
    
    @Test
    public void testInsert() {
        Country usa = mongoOps.findOne(query(where("name").is("USA")), Country.class);
        City chicago = new City("Chicago", usa, Arrays.asList(new String[]{"Navy Pier", "Skyline Lake Tour", "SkyShedd Aquarium"}));
        chicago.setId(new BigInteger("6"));
        
        City chicagoInserted = cityRepo.save(chicago);
        
        assertThat(chicagoInserted.getName(), equalTo("Chicago"));
        assertThat(mongoOps.findById(BigInteger.valueOf(6), City.class).getName(), equalTo("Chicago"));
    }
    
    //------------------------------------------------- update
    
    @Test
    public void testUpdate() {
        City munich = mongoOps.findOne(query(where("name").is("Munich")), City.class);
        munich.getAttractions().add("Allianz Arena");
        munich.getAttractions().add("Marienplatz");
        
        City munichUpdated = cityRepo.save(munich);
        
        assertThat(munichUpdated.getName(), equalTo("Munich"));
        
        City munichRetrieved = mongoOps.findById(BigInteger.valueOf(2), City.class);
        assertThat(munichRetrieved.getName(), equalTo("Munich"));
        List<String> attractions = munichRetrieved.getAttractions();
        assertThat(attractions.toString(), equalTo("[English Garden, BMW Museum, Allianz Arena, Marienplatz]"));
    }
    
    //------------------------------------------------- delete
    
    @Test
    public void testDelete() {
        City berlin = mongoOps.findOne(query(where("name").is("Berlin")), City.class);
        
        cityRepo.delete(berlin);
        
        assertThat(mongoOps.findById(BigInteger.valueOf(3), City.class), equalTo(null));
    }

    
}