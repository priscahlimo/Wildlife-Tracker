import Models.Allsightings;
import Models.Animals;
import Models.Endangeredanimals;
import Models.Sightings;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/",(request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/animalform", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "animalform.hbs");
        },new HandlebarsTemplateEngine());


        post("/sight", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String animalName = request.queryParams("animal");
            String rangerName = request.queryParams("ranger");
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String type = request.queryParams("type");

            if(type.equals("animal")){
                Animals animal = new Animals(animalName);
                animal.save();
                Sightings newSighting = new Sightings(animal.getId(),location,rangerName);
                newSighting.save();
            } else if(type.equals("endangered")){
                Endangeredanimals endangeredAnimal = new Endangeredanimals(animalName,health,age);
                endangeredAnimal.save();
                Sightings anotherSighting = new Sightings(endangeredAnimal.getId(), location, rangerName);
                anotherSighting.save();
            }

            List<Object> allSightings = Allsightings.getAll();
//            List<Endangeredanimals> animals= Endangeredanimals.all();
            model.put("sightings", allSightings);
//            model.put("animals", animals);

            return new ModelAndView(model, "sight.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sight", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings", Allsightings.getAll());
            System.out.println(Allsightings.getAll().size());
            model.put("animal", Endangeredanimals.all());
            return new ModelAndView(model, "sight.hbs");
        }, new HandlebarsTemplateEngine());
  }

}
