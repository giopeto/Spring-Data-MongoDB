

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.lishman.springdata.config.MongoConfig;
import com.lishman.springdata.domain.Continent;
import com.lishman.springdata.repository.ContinentRepository;

@Component
public class GettingStarted {
    
    @Autowired private MongoOperations mongoOps;
    
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        
        ContinentRepository repo = ctx.getBean(ContinentRepository.class);
        
        //------------------------------------------------- CRUD
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println( System.getProperty("java.version"));
        Continent[] continents = new Continent[] {
            new Continent(1, "Africa"),
            new Continent(2, "Asia"),
            new Continent(3, "Europe"),
            new Continent(4, "North America"),
            new Continent(5, "South America"),
            new Continent(6, "Australia")
        };
        
        // save multiple entities
        repo.save(Arrays.asList(continents));
        
        // check if an entity exists
        if (! repo.exists(7L)) {
            // save a single entity
            Continent antartica = new Continent(7, "Antartica");
            repo.save(antartica);
        }

        // count the number of entities
        long count = repo.count();
        
        // get all the entities
        Iterable<Continent> allContinents = repo.findAll();
        
        // get a single entity
        Continent asia = repo.findOne(2L);
        allContinents.forEach(System.out::println);
        // delete an entity
        repo.delete(asia);
        allContinents = repo.findAll();
        allContinents.forEach(System.out::println);
        
        //------------------------------------------------- Query Methods

        // get continent where name is 'Europe'
        Continent europe = repo.findByName("Europe");

        // get continents where name begins with 'a' or 'A'
        List<Continent> aContinents = repo.findByNameStartingWithIgnoreCase("a");
        aContinents.forEach(System.out::println);
        
        List<Continent> aContinentsStartingWith = repo.findByNameStartingWith("A");
        System.out.println("***************************************");
        aContinentsStartingWith.forEach(System.out::println);
        System.out.println("***************************************");
        
        
        ctx.close();
    }

}