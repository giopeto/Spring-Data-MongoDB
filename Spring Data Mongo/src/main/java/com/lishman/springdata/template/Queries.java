package com.lishman.springdata.template;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.domain.Country;

@Component
public class Queries {
    
    @Autowired private MongoOperations mongoOps;
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        Queries queries = ctx.getBean(Queries.class);
        queries.useMongoTemplateForQueries();
        ctx.close();
    }
    
    private void useMongoTemplateForQueries() {
        
        setupTestData();
        
        //------------------------------------------------- equality
        
        Continent asia = mongoOps.findOne(query(where("name").is("Asia")), Continent.class);
        
        Query europeanQuery = query(where("continent.name").is("Europe"));
        List<Country> europeanCountries = mongoOps.find(europeanQuery, Country.class);
        
        //------------------------------------------------- not equal to
        
        List<Continent> notAsia = mongoOps.find(query(where("name").ne("Asia")), Continent.class);
        
        //------------------------------------------------- less than
        
        List<Country> smallCountries = mongoOps.find(query(where("area").lt(30000)), Country.class);
        
        //------------------------------------------------- between
        
        Criteria between = where("population").gt(5000000).lt(30000000);
        List<Country> popBetween = mongoOps.find(query(between), Country.class);
        
        //------------------------------------------------- in list
        
        List<Country> ghanaAndGambia = mongoOps.find(query(where("name").in("Ghana", "Gambia")), Country.class);
        List<Country> notGhanaAndGambia = mongoOps.find(query(where("name").nin("Ghana", "Gambia")), Country.class);
        
        //------------------------------------------------- regular expression

        List<Country> regex = mongoOps.find(query(where("name").regex("G[ae].*")), Country.class);
        
        //------------------------------------------------- not
        
        List<Country> notRegex = mongoOps.find(query(where("name").not().regex("G[ae].*")), Country.class);
        
        //------------------------------------------------- subdocument
        
        List<Country> asianCountries = mongoOps.find(query(where("continent.name").is("Asia")), Country.class);
        
        //------------------------------------------------- and
        
        Criteria smallAreaAndBigPop = where("area").lt(500000).and("population").gt(30000000);
        List<Country> densePop = mongoOps.find(query(smallAreaAndBigPop), Country.class);
        
        //------------------------------------------------- or
        
        Criteria smallArea = where("area").lt(50000);
        Criteria smallPop = where("population").lt(2000000);
        Criteria smallAreaOrPop = new Criteria().orOperator(smallArea, smallPop);
        List<Country> smallAreaOrSmallPop = mongoOps.find(query(smallAreaOrPop), Country.class);
        
        //------------------------------------------------- and / or
        
        Criteria countries = where("name").regex("G[ae].*");
        Criteria andOr = new Criteria().andOperator(countries, smallAreaOrPop);
        List<Country> countryList = mongoOps.find(query(andOr), Country.class);

        /* produces this query document..
         
               { "$and" : [ 
                            { "name" : { "$regex" : "G[ae].*"}} , 
                            { "$or" : [ 
                                        { "area" : { "$lt" : 50000}} , 
                                        { "population" : { "$lt" : 2000000}}
                                      ]
                            }
                          ]
               }
          
         */

        //------------------------------------------------- query document
    
        BasicQuery queryDoc = new BasicQuery("{ continent.name : 'Europe', area : { $gt : 50000 } }");
        List<Country> largeEuropean = mongoOps.find(queryDoc, Country.class);
        
    }
    
    //------------------------------------------------- test data

    private void setupTestData() {
        if (mongoOps.collectionExists(Country.class)) {
            mongoOps.dropCollection(Country.class);
        }
        
        Country[] countries = new Country[] {
            new Country("Australia", 2966200, 21884000L, new Continent(6, "Australia")),
            new Country("Gabon", 103347, 1475000L, new Continent(1, "Africa")),
            new Country("Gambia", 4361, 1705000L, new Continent(1, "Africa")),
            new Country("Georgia", 26900, 4382100L, new Continent(3, "Europe")),
            new Country("Germany", 137847, 82046000L, new Continent(3, "Europe")),
            new Country("Ghana", 92098, 23837000L, new Continent(1, "Africa")),
            new Country("Greece", 50949, 11257285L, new Continent(3, "Europe")),
            new Country("Japan", 145925, 126659683L, new Continent(2, "Asia")),
            new Country("New Zealand", 104454, 4320300L, new Continent(6, "Australia")),
            new Country("Serbia", 34116, 7120666L, new Continent(3, "Europe")),
            new Country("USA", 3794101, 316637000L, new Continent(4, "North America")),
            new Country("Vietnam", 128565, 90388000L, new Continent(2, "Asia")),
            new Country("Iceland", 39770, 321857L, new Continent(3, "Europe"))
        };
    
        mongoOps.insertAll(Arrays.asList(countries));
    }
}