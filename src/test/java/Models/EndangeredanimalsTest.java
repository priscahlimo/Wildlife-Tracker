package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EndangeredanimalsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    public Endangeredanimals setUpEndangeredAnimal(){
        return new Endangeredanimals("elephant","weak","young");
    }

    @Test
    public void endangeredAnimal_instantiatesCorrectly_true(){
        Endangeredanimals animal = setUpEndangeredAnimal();
        assertEquals(true,animal instanceof Endangeredanimals);
    }

    @Test
    public void endangeredAnimal_instantiatesWithName_String(){
        Endangeredanimals animal = setUpEndangeredAnimal();
        assertEquals("elephant",animal.getName());
    }

    @Test
    public void endangeredAnimal_instantiatesWithHealth_String(){
        Endangeredanimals animal = setUpEndangeredAnimal();
        assertEquals("weak",animal.getHealth());
    }

    @Test
    public void endangeredAnimal_instantiatesWithAge_String(){
        Endangeredanimals animal = setUpEndangeredAnimal();
        assertEquals("young",animal.getAge());
    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Endangeredanimals testAnimal = new Endangeredanimals("lion","okay","newborn");
        Endangeredanimals anotherAnimal = new Endangeredanimals("lion","okay","newborn");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_successfullyAddsEndangeredAnimalToDatabase_List() {
        Endangeredanimals animal = setUpEndangeredAnimal();
        animal.save();
        assertTrue(Endangeredanimals.all().get(0).equals(animal));
    }

    @Test
    public void save_assignsIdToEndangeredAnimal() {
        Endangeredanimals animal = setUpEndangeredAnimal();
        animal.save();
        Endangeredanimals savedAnimal = Endangeredanimals.all().get(0);
        assertEquals(savedAnimal.getId(), animal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Endangeredanimals firstAnimal = setUpEndangeredAnimal();
        firstAnimal.save();
        Endangeredanimals secondAnimal = Endangeredanimals.all().get(0);
        secondAnimal.save();
        assertEquals(true, Endangeredanimals.all().get(0).equals(firstAnimal));
        assertEquals(true, Endangeredanimals.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Endangeredanimals firstAnimal = setUpEndangeredAnimal();
        firstAnimal.save();
        Endangeredanimals secondAnimal = Endangeredanimals.all().get(0);
        secondAnimal.save();
        assertEquals(Endangeredanimals.find(secondAnimal.getId()), secondAnimal);
    }

}