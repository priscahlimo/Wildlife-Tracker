package Models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Allsightings {
    private int animalId;
    private String location;
    private String rangerName;
    private Timestamp lastSeen;
    public int id;


    public Allsightings( int animalsId, String location, String rangerName,  Timestamp lastSeen){
        this.animalId=animalsId;
        this.location = location;
        this.rangerName = rangerName;

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


    public int getId() {
        return id;
    }



    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalid, location, rangername, lastseen,) VALUES (:animalId :location, :rangername, :lastseen)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("lastSeen",this.lastSeen)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Object> getAll(){
        String sql1 = "SELECT * FROM animals WHERE type='animal'";
        String sql = "SELECT * FROM animals WHERE type='endagered-animal'";
        List<Object> Allanimals= new ArrayList<>();
        try(Connection con = DB.sql2o.open()) {

            List<Animals> Myanimals= con.createQuery(sql1).executeAndFetch(Animals.class);
            List<Endangeredanimals>Myendangeredanimals=con.createQuery(sql).executeAndFetch(Endangeredanimals.class);
            Allanimals.addAll(Myanimals);
            Allanimals.addAll(Myendangeredanimals);
        }

        return Allanimals;
    }
//    public static getAllSightings(){
//
//    }

}
