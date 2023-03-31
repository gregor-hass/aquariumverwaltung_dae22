package model;

/**
 * Animal model class
 * 
 * @author Gregor Koerner (S2010306010)
 */
public class Animal {    

    private Integer animalId;
    private String genus;
    private String species;
    private String morph;

    public Animal(Integer animalId, String genus, String species, String morph) {
        this.animalId = animalId;
        this.genus = genus;
        this.species = species;
        this.morph = morph;
    }
    
    public Integer getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
    public String getGenus() {
        return genus;
    }
    public void setGenus(String genus) {
        this.genus = genus;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getMorph() {
        return morph;
    }
    public void setMorph(String morph) {
        this.morph = morph;
    }

}
