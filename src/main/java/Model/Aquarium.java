package Model;

/**
 * Aquarium model class
 * 
 * @author Gregor Koerner (S2010306010)
 */
public class Aquarium {
    

    private Integer aquariumId;
    private double volume;
    private String room;

    /**
     * @param aquariumId
     * @param volume
     * @param room
     * 
     * 
     */
    public Aquarium(Integer aquariumId, double volume, String room){
        this.setAquariumId(aquariumId);
        this.setVolume(volume);
        this.setRoom(room);
    }


    public Integer getAquariumId() {
        return aquariumId;
    }

    public void setAquariumId(Integer aquariumId) {
        this.aquariumId = aquariumId;
    }

    
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    
    /**
     * @see java.lang.Object#toString()
     * override for printing
     * 
     * @return formatted string
     */
    @Override
    public String toString() {
        return String.format("Aquarium in "+  getRoom() + ": " + getVolume());
    }
}