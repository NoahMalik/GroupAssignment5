package model;

/**
 * Abstract class representing a pet in the system.
 */
public abstract class Pet implements Comparable<Pet> {
    private String id;
    private String name;
    private int age;
    private String species;
    private boolean adopted;

    public Pet(String id, String name, int age, String species) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adopted = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void adopt() {
        this.adopted = true;
    }

    @Override
    public int compareTo(Pet other) {
        return this.name.compareTo(other.name); // Default sorting by name
    }

    public String getDetails() {
        return species + " - " + age + " years old";
    }
}