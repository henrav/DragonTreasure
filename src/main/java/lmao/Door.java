package lmao;

public class Door {
    private String name;
    private String description;
    private boolean locked;
    private String keyName;
    private String direction;
    private Room LeadsTo; //vilket rum dörren leder till

    public Door(String name, String description, boolean locked, String direction) {
        this.name = name;
        this.description = description;
        this.locked = locked;
        this.keyName = keyName;
        this.direction = direction;
        this.LeadsTo = LeadsTo;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getLocked() {
        return locked;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getDirection() {
        return direction;
    }

    public Room getLeadsTo() {
        return LeadsTo;
    }

    public void setLeadsTo(Room LeadsTo) {
        this.LeadsTo = LeadsTo;
    }
    public boolean isLocked() {
        if (locked == true) {
            System.out.println("The door is locked");
        } else {
            System.out.println("The door is unlocked");
        }
        return false;
    }



    public void setLocked(boolean locked) {
        this.locked = locked;
    }


    public void unlock() {
        this.locked = false;
    }
}
