package Models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Allsightings {
    private int animalId;
    private String location;
    private String rangerName;
    private Timestamp lastSeen;
    public String name;
    public int id;
    public String health;
    public String age;
    public String type;

    public Allsightings(String name, String health, String age, String location, String rangerName, String type, Timestamp lastSeen){
        this.name = name;
        this.health = health;
        this.age = age;
        this.location = location;
        this.rangerName = rangerName;
        this.type = type;
        this.lastSeen = lastSeen;

    }

    public int getAnimalId() {
        return animalId;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    public static List<Allsightings> getAll(){
        String sql = "SELECT animals.id,name,health,age,location,rangerName,type,lastSeen FROM animals INNER JOIN sightings ON sightings.animalId = animals.id ORDER BY lastSeen";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Allsightings.class);
        }
    }

}
