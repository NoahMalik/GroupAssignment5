package model;

/**
 * Adapter class to convert ExoticAnimal to a Pet.
 */
public class ExoticAnimalAdapter extends Pet {
    private ExoticAnimal exoticAnimal;

    public ExoticAnimalAdapter(ExoticAnimal exoticAnimal) {
        super(exoticAnimal.getUniqueId(), exoticAnimal.getAnimalName(), exoticAnimal.getYearsOld(), exoticAnimal.getSubSpecies());
        this.exoticAnimal = exoticAnimal;
    }

    @Override
    public String getDetails() {
        return exoticAnimal.getSubSpecies() + " - " + exoticAnimal.getYearsOld() + " years old";
    }
}