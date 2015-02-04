package com.lishman.springdata;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

public class TestData {

    public static void continents() {

        MongoClient client = mongoClient();
        DB db = client.getDB( "world");

        DBCollection continents = db.getCollection("continent");
        continents.drop();
        
        continents.insert(dbObjectFromJson("{_id : 1, name : 'Africa'}"));
        continents.insert(dbObjectFromJson("{_id : 2, name : 'Asia'}"));
        continents.insert(dbObjectFromJson("{_id : 3, name : 'Europe'}"));
        continents.insert(dbObjectFromJson("{_id : 4, name : 'North America'}"));
        continents.insert(dbObjectFromJson("{_id : 5, name : 'South America'}"));
        continents.insert(dbObjectFromJson("{_id : 6, name : 'Australia'}"));
        continents.insert(dbObjectFromJson("{_id : 7, name : 'Antarctica'}"));
    }

    public static void oceans() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world" );
        
        DBCollection oceans = db.getCollection("oceans");
        
        /* remove(..) is used instead of drop() to preserve the index.
         * 
         * The index is created when the application context is initialized.
         * If drop() were used here, it would delete the collection AND the
         * index when running the integration tests.
         */
        
        oceans.remove(new BasicDBObject());
        
        oceans.insert(dbObjectFromJson("{_id : 1, name : 'Arctic', area: 5426000}"));
        oceans.insert(dbObjectFromJson("{_id : 2, name : 'Atlantic', area: 29630000}"));
        oceans.insert(dbObjectFromJson("{_id : 3, name : 'Indian', area: 26463000}"));
        oceans.insert(dbObjectFromJson("{_id : 4, name : 'Pacific', area: 60045000}"));
        oceans.insert(dbObjectFromJson("{_id : 5, name : 'Southern', area: 7846000}"));
    }
    
    public static void countries() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world");

        DBCollection countries = db.getCollection("countries");
        countries.drop();
        
        countries.insert(dbObjectFromJson("{ '_id' : '1', '_class' : 'ctry', 'name' : 'Australia', 'area' : 2966200, 'pop' : 21884000, 'continent' : { '_id' : 6, 'name' : 'Australia' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '2', '_class' : 'ctry', 'name' : 'Gabon', 'area' : 103347, 'pop' : 1475000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '3', '_class' : 'ctry', 'name' : 'Gambia', 'area' : 4361, 'pop' : 1705000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '4', '_class' : 'ctry', 'name' : 'Georgia', 'area' : 26900, 'pop' : 4382100, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '5', '_class' : 'ctry', 'name' : 'Germany', 'area' : 137847, 'pop' : 82046000, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '6', '_class' : 'ctry', 'name' : 'Ghana', 'area' : 92098, 'pop' : 23837000, 'continent' : { '_id' : 1, 'name' : 'Africa' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '7', '_class' : 'ctry', 'name' : 'Greece', 'area' : 50949, 'pop' : 11257285, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '8', '_class' : 'ctry', 'name' : 'Japan', 'area' : 145925, 'pop' : 126659683, 'continent' : { '_id' : 2, 'name' : 'Asia' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '9', '_class' : 'ctry', 'name' : 'New Zealand', 'area' : 104454, 'pop' : 4320300, 'continent' : { '_id' : 6, 'name' : 'Australia' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '10', '_class' : 'ctry', 'name' : 'Serbia', 'area' : 34116, 'pop' : 7120666, 'continent' : { '_id' : 3, 'name' : 'Europe' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '11', '_class' : 'ctry', 'name' : 'USA', 'area' : 3794101, 'pop' : 316637000, 'continent' : { '_id' : 4, 'name' : 'North America' } }"));
        countries.insert(dbObjectFromJson("{ '_id' : '12', '_class' : 'ctry', 'name' : 'Vietnam', 'area' : 128565, 'pop' : 90388000, 'continent' : { '_id' : 2, 'name' : 'Asia' } }"));
    }
    
    public static void cities() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world");
        
        DBCollection cities = db.getCollection("cities");
        cities.drop();

        cities.insert(cityFromJson(db, 8, "{ '_id' : '1', '_class' : 'city', 'name' : 'Tokyo', 'attractions' : [ 'Mount Fuji', 'Tokyo Skytree', 'Disneyland' ] }"));
        cities.insert(cityFromJson(db, 5, "{ '_id' : '2', '_class' : 'city', 'name' : 'Munich', 'attractions' : [ 'English Garden', 'BMW Museum' ] }"));
        cities.insert(cityFromJson(db, 5, "{ '_id' : '3', '_class' : 'city', 'name' : 'Berlin', 'attractions' : [ 'Brandenburg Gate', 'Berlin TV Tower', 'Reichstag building' ] }"));
        cities.insert(cityFromJson(db, 11, "{ '_id' : '4', '_class' : 'city', 'name' : 'New York', 'attractions' : [ 'Empire State Building', 'Statue of Liberty', 'Central Park', 'Times Square', 'Bronx Zoo' ] }"));
        cities.insert(cityFromJson(db, 11, "{ '_id' : '5', '_class' : 'city', 'name' : 'Boston', 'attractions' : [ 'Freedom Trail', 'Fenway Park', 'Beacon Hill' ] }"));
    }
    
    public static void medals() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world");
        
        DBCollection medals = db.getCollection("medals");
        medals.drop();
        
        medals.insert(dbObjectFromJson("{ '_id' : '1', '_class' : 'medal', 'name' : 'United States', 'medals' : [ 666, 758, 976 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '2', '_class' : 'medal', 'name' : 'Soviet Union', 'medals' : [ 296, 319, 395] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '3', '_class' : 'medal', 'name' : 'Great Britain', 'medals' : [ 272, 272, 236 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '4', '_class' : 'medal', 'name' : 'France', 'medals' : [ 246, 223, 202 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '5', '_class' : 'medal', 'name' : 'Germany', 'medals' : [ 217, 182, 174 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '6', '_class' : 'medal', 'name' : 'Italy', 'medals' : [ 185, 166, 198 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '7', '_class' : 'medal', 'name' : 'Sweden', 'medals' : [ 176, 164, 143 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '8', '_class' : 'medal', 'name' : 'East Germany', 'medals' : [ 127, 129, 153] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '9', '_class' : 'medal', 'name' : 'China', 'medals' : [ 128, 144, 201 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '10', '_class' : 'medal', 'name' : 'Russia', 'medals' : [ 142, 122, 133 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '11', '_class' : 'medal', 'name' : 'Hungary', 'medals' : [ 165, 144, 167 ] }"));
        medals.insert(dbObjectFromJson("{ '_id' : '12', '_class' : 'medal', 'name' : 'Australia', 'medals' : [ 177, 153, 138 ] }"));
    }
    
    public static void states() {
        
        MongoClient client = mongoClient();
        DB db = client.getDB( "world");
        
        DBCollection states = db.getCollection("states");
        states.drop();
        
        states.insert(dbObjectFromJson("{ '_id' : '1', '_class' : 'state', 'name' : 'Alabama', 'abbreviation' : 'AL', 'dateOfStatehood' : 1819, 'capital' : 'Montgomery', 'capitalSince' : 1846 }"));
        states.insert(dbObjectFromJson("{ '_id' : '2', '_class' : 'state', 'name' : 'Alaska', 'abbreviation' : 'AK', 'dateOfStatehood' : 1959, 'capital' : 'Juneau', 'capitalSince' : 1906 }"));
        states.insert(dbObjectFromJson("{ '_id' : '3', '_class' : 'state', 'name' : 'Arizona', 'abbreviation' : 'AZ', 'dateOfStatehood' : 1912, 'capital' : 'Phoenix', 'capitalSince' : 1889 }"));
        states.insert(dbObjectFromJson("{ '_id' : '4', '_class' : 'state', 'name' : 'Arkansas', 'abbreviation' : 'AR', 'dateOfStatehood' : 1836, 'capital' : 'Little Rock', 'capitalSince' : 1821 }"));
        states.insert(dbObjectFromJson("{ '_id' : '5', '_class' : 'state', 'name' : 'California', 'abbreviation' : 'CA', 'dateOfStatehood' : 1850, 'capital' : 'Sacramento', 'capitalSince' : 1854 }"));
        states.insert(dbObjectFromJson("{ '_id' : '6', '_class' : 'state', 'name' : 'Colorado', 'abbreviation' : 'CO', 'dateOfStatehood' : 1876, 'capital' : 'Denver', 'capitalSince' : 1867 }"));
        states.insert(dbObjectFromJson("{ '_id' : '7', '_class' : 'state', 'name' : 'Connecticut', 'abbreviation' : 'CT', 'dateOfStatehood' : 1788, 'capital' : 'Hartford', 'capitalSince' : 1875 }"));
        states.insert(dbObjectFromJson("{ '_id' : '8', '_class' : 'state', 'name' : 'Delaware', 'abbreviation' : 'DE', 'dateOfStatehood' : 1787, 'capital' : 'Dover', 'capitalSince' : 1777 }"));
        states.insert(dbObjectFromJson("{ '_id' : '9', '_class' : 'state', 'name' : 'Florida', 'abbreviation' : 'FL', 'dateOfStatehood' : 1845, 'capital' : 'Tallahassee', 'capitalSince' : 1824 }"));
        states.insert(dbObjectFromJson("{ '_id' : '10', '_class' : 'state', 'name' : 'Georgia', 'abbreviation' : 'GA', 'dateOfStatehood' : 1788, 'capital' : 'Atlanta', 'capitalSince' : 1868 }"));
        states.insert(dbObjectFromJson("{ '_id' : '11', '_class' : 'state', 'name' : 'Hawaii', 'abbreviation' : 'HI', 'dateOfStatehood' : 1959, 'capital' : 'Honolulu', 'capitalSince' : 1845 }"));
        states.insert(dbObjectFromJson("{ '_id' : '12', '_class' : 'state', 'name' : 'Idaho', 'abbreviation' : 'ID', 'dateOfStatehood' : 1890, 'capital' : 'Boise', 'capitalSince' : 1865 }"));
        states.insert(dbObjectFromJson("{ '_id' : '13', '_class' : 'state', 'name' : 'Illinois', 'abbreviation' : 'IL', 'dateOfStatehood' : 1818, 'capital' : 'Springfield', 'capitalSince' : 1837 }"));
        states.insert(dbObjectFromJson("{ '_id' : '14', '_class' : 'state', 'name' : 'Indiana', 'abbreviation' : 'IN', 'dateOfStatehood' : 1816, 'capital' : 'Indianapolis', 'capitalSince' : 1825 }"));
        states.insert(dbObjectFromJson("{ '_id' : '15', '_class' : 'state', 'name' : 'Iowa', 'abbreviation' : 'IA', 'dateOfStatehood' : 1846, 'capital' : 'Des Moines', 'capitalSince' : 1857 }"));
        states.insert(dbObjectFromJson("{ '_id' : '16', '_class' : 'state', 'name' : 'Kansas', 'abbreviation' : 'KS', 'dateOfStatehood' : 1861, 'capital' : 'Topeka', 'capitalSince' : 1856 }"));
        states.insert(dbObjectFromJson("{ '_id' : '17', '_class' : 'state', 'name' : 'Kentucky', 'abbreviation' : 'KY', 'dateOfStatehood' : 1792, 'capital' : 'Frankfort', 'capitalSince' : 1792 }"));
        states.insert(dbObjectFromJson("{ '_id' : '18', '_class' : 'state', 'name' : 'Louisiana', 'abbreviation' : 'LA', 'dateOfStatehood' : 1812, 'capital' : 'Baton Rouge', 'capitalSince' : 1880 }"));
        states.insert(dbObjectFromJson("{ '_id' : '19', '_class' : 'state', 'name' : 'Maine', 'abbreviation' : 'ME', 'dateOfStatehood' : 1820, 'capital' : 'Augusta', 'capitalSince' : 1832 }"));
        states.insert(dbObjectFromJson("{ '_id' : '20', '_class' : 'state', 'name' : 'Maryland', 'abbreviation' : 'MD', 'dateOfStatehood' : 1788, 'capital' : 'Annapolis', 'capitalSince' : 1694 }"));
        states.insert(dbObjectFromJson("{ '_id' : '21', '_class' : 'state', 'name' : 'Massachusetts', 'abbreviation' : 'MA', 'dateOfStatehood' : 1788, 'capital' : 'Boston', 'capitalSince' : 1630 }"));
        states.insert(dbObjectFromJson("{ '_id' : '22', '_class' : 'state', 'name' : 'Michigan', 'abbreviation' : 'MI', 'dateOfStatehood' : 1837, 'capital' : 'Lansing', 'capitalSince' : 1847 }"));
        states.insert(dbObjectFromJson("{ '_id' : '23', '_class' : 'state', 'name' : 'Minnesota', 'abbreviation' : 'MN', 'dateOfStatehood' : 1858, 'capital' : 'Saint Paul', 'capitalSince' : 1849 }"));
        states.insert(dbObjectFromJson("{ '_id' : '24', '_class' : 'state', 'name' : 'Mississippi', 'abbreviation' : 'MS', 'dateOfStatehood' : 1817, 'capital' : 'Jackson', 'capitalSince' : 1821 }"));
        states.insert(dbObjectFromJson("{ '_id' : '25', '_class' : 'state', 'name' : 'Missouri', 'abbreviation' : 'MO', 'dateOfStatehood' : 1821, 'capital' : 'Jefferson City', 'capitalSince' : 1826 }"));
        states.insert(dbObjectFromJson("{ '_id' : '26', '_class' : 'state', 'name' : 'Montana', 'abbreviation' : 'MT', 'dateOfStatehood' : 1889, 'capital' : 'Helena', 'capitalSince' : 1875 }"));
        states.insert(dbObjectFromJson("{ '_id' : '27', '_class' : 'state', 'name' : 'Nebraska', 'abbreviation' : 'NE', 'dateOfStatehood' : 1867, 'capital' : 'Lincoln', 'capitalSince' : 1867 }"));
        states.insert(dbObjectFromJson("{ '_id' : '28', '_class' : 'state', 'name' : 'Nevada', 'abbreviation' : 'NV', 'dateOfStatehood' : 1864, 'capital' : 'Carson City', 'capitalSince' : 1861 }"));
        states.insert(dbObjectFromJson("{ '_id' : '29', '_class' : 'state', 'name' : 'New Hampshire', 'abbreviation' : 'NH', 'dateOfStatehood' : 1788, 'capital' : 'Concord', 'capitalSince' : 1808 }"));
        states.insert(dbObjectFromJson("{ '_id' : '30', '_class' : 'state', 'name' : 'New Jersey', 'abbreviation' : 'NJ', 'dateOfStatehood' : 1787, 'capital' : 'Trenton', 'capitalSince' : 1784 }"));
        states.insert(dbObjectFromJson("{ '_id' : '31', '_class' : 'state', 'name' : 'New Mexico', 'abbreviation' : 'NM', 'dateOfStatehood' : 1912, 'capital' : 'Santa Fe', 'capitalSince' : 1610 }"));
        states.insert(dbObjectFromJson("{ '_id' : '32', '_class' : 'state', 'name' : 'New York', 'abbreviation' : 'NY', 'dateOfStatehood' : 1788, 'capital' : 'Albany', 'capitalSince' : 1797 }"));
        states.insert(dbObjectFromJson("{ '_id' : '33', '_class' : 'state', 'name' : 'North Carolina', 'abbreviation' : 'NC', 'dateOfStatehood' : 1789, 'capital' : 'Raleigh', 'capitalSince' : 1792 }"));
        states.insert(dbObjectFromJson("{ '_id' : '34', '_class' : 'state', 'name' : 'North Dakota', 'abbreviation' : 'ND', 'dateOfStatehood' : 1889, 'capital' : 'Bismarck', 'capitalSince' : 1883 }"));
        states.insert(dbObjectFromJson("{ '_id' : '35', '_class' : 'state', 'name' : 'Ohio', 'abbreviation' : 'OH', 'dateOfStatehood' : 1803, 'capital' : 'Columbus', 'capitalSince' : 1816 }"));
        states.insert(dbObjectFromJson("{ '_id' : '36', '_class' : 'state', 'name' : 'Oklahoma', 'abbreviation' : 'OK', 'dateOfStatehood' : 1907, 'capital' : 'Oklahoma City', 'capitalSince' : 1910 }"));
        states.insert(dbObjectFromJson("{ '_id' : '37', '_class' : 'state', 'name' : 'Oregon', 'abbreviation' : 'OR', 'dateOfStatehood' : 1859, 'capital' : 'Salem', 'capitalSince' : 1855 }"));
        states.insert(dbObjectFromJson("{ '_id' : '38', '_class' : 'state', 'name' : 'Pennsylvania', 'abbreviation' : 'PA', 'dateOfStatehood' : 1787, 'capital' : 'Harrisburg', 'capitalSince' : 1812 }"));
        states.insert(dbObjectFromJson("{ '_id' : '39', '_class' : 'state', 'name' : 'Rhode Island', 'abbreviation' : 'RI', 'dateOfStatehood' : 1790, 'capital' : 'Providence', 'capitalSince' : 1900 }"));
        states.insert(dbObjectFromJson("{ '_id' : '40', '_class' : 'state', 'name' : 'South Carolina', 'abbreviation' : 'SC', 'dateOfStatehood' : 1788, 'capital' : 'Columbia', 'capitalSince' : 1786 }"));
        states.insert(dbObjectFromJson("{ '_id' : '41', '_class' : 'state', 'name' : 'South Dakota', 'abbreviation' : 'SD', 'dateOfStatehood' : 1889, 'capital' : 'Pierre', 'capitalSince' : 1889 }"));
        states.insert(dbObjectFromJson("{ '_id' : '42', '_class' : 'state', 'name' : 'Tennessee', 'abbreviation' : 'TN', 'dateOfStatehood' : 1796, 'capital' : 'Nashville', 'capitalSince' : 1826 }"));
        states.insert(dbObjectFromJson("{ '_id' : '43', '_class' : 'state', 'name' : 'Texas', 'abbreviation' : 'TX', 'dateOfStatehood' : 1845, 'capital' : 'Austin', 'capitalSince' : 1839 }"));
        states.insert(dbObjectFromJson("{ '_id' : '44', '_class' : 'state', 'name' : 'Utah', 'abbreviation' : 'UT', 'dateOfStatehood' : 1896, 'capital' : 'Salt Lake City','capitalSince' : 1858 }"));
        states.insert(dbObjectFromJson("{ '_id' : '45', '_class' : 'state', 'name' : 'Vermont', 'abbreviation' : 'VT', 'dateOfStatehood' : 1791, 'capital' : 'Montpelier', 'capitalSince' : 1805 }"));
        states.insert(dbObjectFromJson("{ '_id' : '46', '_class' : 'state', 'name' : 'Virginia', 'abbreviation' : 'VA', 'dateOfStatehood' : 1788, 'capital' : 'Richmond', 'capitalSince' : 1780 }"));
        states.insert(dbObjectFromJson("{ '_id' : '47', '_class' : 'state', 'name' : 'Washington', 'abbreviation' : 'WA', 'dateOfStatehood' : 1889, 'capital' : 'Olympia', 'capitalSince' : 1853 }"));
        states.insert(dbObjectFromJson("{ '_id' : '48', '_class' : 'state', 'name' : 'West Virginia', 'abbreviation' : 'WV', 'dateOfStatehood' : 1863, 'capital' : 'Charleston', 'capitalSince' : 1885 }"));
        states.insert(dbObjectFromJson("{ '_id' : '49', '_class' : 'state', 'name' : 'Wisconsin', 'abbreviation' : 'WI', 'dateOfStatehood' : 1848, 'capital' : 'Madison', 'capitalSince' : 1838 }"));
        states.insert(dbObjectFromJson("{ '_id' : '50', '_class' : 'state', 'name' : 'Wyoming', 'abbreviation' : 'WY', 'dateOfStatehood' : 1890, 'capital' : 'Cheyenne', 'capitalSince' : 1869 }"));
    
    }
    

    private static MongoClient mongoClient() {
        try {
            return new MongoClient( "mongo-host" , 27017 );
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static DBObject cityFromJson(DB db, long countryId, String json) {
        DBObject city = dbObjectFromJson(json);
        DBRef ref = new DBRef(db, "countries", Long.toString(countryId));
        city.put("country", ref);
        return city;        
    }

    private static DBObject dbObjectFromJson(String json) {
        return (DBObject) JSON.parse(json);
        
    }
}