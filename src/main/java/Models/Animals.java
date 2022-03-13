package Models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Animals  extends Allanimals implements DatabaseManagement{
    public static final String ANIMAL_TYPE = "animal";

    public Animals(String name){
        this.name = name;
        this.type = ANIMAL_TYPE;
        if (name.isEmpty()){
            throw new IllegalArgumentException("Please enter the animal's name.");
        }
    }
    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animals)) {
            return false;
        } else {
            Animals newAnimal = (Animals) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals WHERE type='animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }

    public static Animals find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

    public static List<Object> getAnimals() {
        List<Object> allAnimals = new ArrayList<Object>();

        try(Connection con = DB.sql2o.open()) {
            String sqlFire = "SELECT * FROM animals WHERE id=:id AND type='animal';";
            List<Animals> animals = con.createQuery(sqlFire)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
            allAnimals.addAll(animals);

            String sqlWater = "SELECT * FROM animals WHERE id=:id AND type='endangered-animal';";
            List<Endangeredanimals> endangeredAnimals = con.createQuery(sqlWater)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangeredanimals.class);
            allAnimals.addAll(endangeredAnimals);
        }

        return allAnimals;
    }

}