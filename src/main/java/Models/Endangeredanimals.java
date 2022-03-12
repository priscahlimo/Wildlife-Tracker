package Models;


import org.sql2o.Connection;

import java.util.List;

public class Endangeredanimals extends Allanimals implements DatabaseManagement{
    public static final String ANIMAL_TYPE = "endangered-animal";

    public  Endangeredanimals(String name, String health, String age){
        this.name = name;
        this.health = health;
        this.age = age;
        this.type = ANIMAL_TYPE;

        if (name.isEmpty() || health.isEmpty() || age.isEmpty()){
            throw new IllegalArgumentException("Please enter all input fields.");
        }
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Endangeredanimals)) {
            return false;
        } else {
            Endangeredanimals newAnimal = (Endangeredanimals) otherAnimal;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getHealth().equals(newAnimal.getHealth()) &&
                    this.getAge().equals(newAnimal.getAge());
        }
    }

    public static List<Endangeredanimals> all() {
        String sql = "SELECT * FROM animals WHERE type='endangered-animal';";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangeredanimals.class);
        }
    }

    public static Endangeredanimals find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangeredanimals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Endangeredanimals.class);
            return animal;
        }
    }
}
