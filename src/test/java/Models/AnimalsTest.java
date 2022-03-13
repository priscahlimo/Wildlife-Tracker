package Models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnimalsTest{
    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    public Animals setUpAnimal(){
        return new Animals("elephant");
    }

    @Test
    public void animal_instantiatesCorrectly_true(){
        Animals animal = setUpAnimal();
        assertEquals(true,animal instanceof Animals);
    }

    @Test
    public void animal_instantiatesWithName_String(){
        Animals animal = setUpAnimal();
        assertEquals("elephant",animal.getName());
    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Animals testAnimal = setUpAnimal();
        Animals anotherAnimal = new Animals("elephant");
        assertTrue(testAnimal.equals(anotherAnimal));
    }

    @Test
    public void save_successfullyAddsAnimalToDatabase_List() {
        Animals animal = setUpAnimal();
        animal.save();
        assertTrue(Animals.all().get(0).equals(animal));
    }

    @Test
    public void save_assignsIdToAnimal() {
        Animals animal = setUpAnimal();
        animal.save();
        Animals savedAnimal = Animals.all().get(0);
        assertEquals(savedAnimal.getId(), animal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animals firstAnimal = setUpAnimal();
        firstAnimal.save();
        Animals secondAnimal = new Animals("lion");
        secondAnimal.save();
        assertEquals(true, Animals.all().get(0).equals(firstAnimal));
        assertEquals(true, Animals.all().get(1).equals(secondAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animals firstAnimal = setUpAnimal();
        firstAnimal.save();
        Animals secondAnimal = new Animals("lion");
        secondAnimal.save();
        assertEquals(Animals.find(secondAnimal.getId()), secondAnimal);
    }
}