package controller;

import com.google.gson.Gson;
import model.*;
import view.PetAdoptionView;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Date;

/**
 * Controller class to handle business logic and communication between model and view.
 */
public class PetAdoptionController {
    private Shelter<Pet> shelter;
    private PetAdoptionView view;

    public PetAdoptionController() {
        this.shelter = new Shelter<>();
        this.view = new PetAdoptionView(this);
    }

    public void addPet(Pet pet) {
        shelter.addPet(pet);
        view.showPets(shelter.getAllPets());
    }

    public void adoptPet(String petId) {
        Pet pet = shelter.getPetById(petId);
        if (pet == null) {
            view.showMessage("No pet selected.");
            return;
        }
        if (pet.isAdopted()) {
            view.showMessage("This pet has already been adopted!");
        } else {
            pet.adopt();
            view.showMessage(pet.getName() + " has been adopted!");
            view.showPets(shelter.getAllPets());
        }
    }

    public void removePet(String petId) {
        Pet pet = shelter.getPetById(petId);
        if (pet != null) {
            shelter.removePet(pet);
            view.showMessage(pet.getName() + " has been removed.");
            view.showPets(shelter.getAllPets());
        } else {
            view.showMessage("No pet selected.");
        }
    }

    public void sortPets(String criteria) {
        List<Pet> pets = shelter.getAllPets();
        switch (criteria.toLowerCase()) {
            case "age" -> pets.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
            case "species" -> pets.sort((p1, p2) -> p1.getSpecies().compareTo(p2.getSpecies()));
            default -> Collections.sort(pets); // default by name
        }
        view.showPets(pets);
    }

    public void saveToJson() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = timestamp + " pets.json";
        try (FileWriter writer = new FileWriter(filename)) {
            new Gson().toJson(shelter.getAllPets(), writer);
            view.showMessage("Pets saved to: " + filename);
        } catch (IOException e) {
            view.showMessage("Error saving pets: " + e.getMessage());
        }
    }
}