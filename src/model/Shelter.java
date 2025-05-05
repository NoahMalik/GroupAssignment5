package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Shelter class to hold pets.
 */
public class Shelter<T extends Pet> {
    private List<T> pets = new ArrayList<>();

    public void addPet(T pet) {
        pets.add(pet);
    }

    public void removePet(T pet) {
        pets.remove(pet);
    }

    public List<T> getAllPets() {
        return pets;
    }

    public T getPetById(String petId) {
        for (T pet : pets) {
            if (pet.getId().equals(petId)) {
                return pet;
            }
        }
        return null;
    }
}