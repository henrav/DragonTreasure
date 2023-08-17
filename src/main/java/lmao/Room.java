package lmao;

import lmao.Entities.Entities;
import lmao.Items.Item;
import lmao.Items.Key;

import java.util.ArrayList;

public class Room {
    private String roomName;
    private String roomDescription;
    private ArrayList<Item> items;
    private ArrayList<Door> doors;
    private Entities monster;


    //två olika konstruktorer, en för rum med monster och en för rum utan monster
    public Room(String roomName, String roomDescription, Entities monster){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.items = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.monster = monster;
    }
    public Room(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.items = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.monster = null;
    }
    public String getRoomName(){
        return roomName;
    }

    public Entities getMonster(){
        return monster;
    }

    public void addDoor(Door door){
        doors.add(door);
    }


    public ArrayList<Door> getDoors(){
        return doors;
    }

    //printar ut rummets items
    public void printItems(){
        if(items.size() == 1){
            System.out.println("There's 1 item in the room:");
            System.out.println("["+ items.get(0).getName() +"]");
            System.out.println("Press v to pick it up");
        }
        else if(items.size() > 1){
            System.out.println("There's " + items.size() + " items in the room:");
            for (Item item : items){
                System.out.println("[" + item.getName() +"]");
            }
            System.out.println("Press v to pick them up");
        }
    }


    //printar ut rummets dörrar
    public void printDoors(){
        if (doors.size() > 0 && doors.size() != 1){
            System.out.println("There's " + doors.size() + " doors in the room leading to:");
            for (Door door : doors){
                if (door.getDirection().equals("E")){
                    System.out.println("East");
                }
                else if (door.getDirection().equals("W")){
                    System.out.println("West");
                }
                else if (door.getDirection().equals("N")){
                    System.out.println("North");
                }
                else if (door.getDirection().equals("S")){
                    System.out.println("South");
                }
            }
        }
        else{
            System.out.println("There's 1 door in room leading to:" + "\n" + doors.get(0).getDirection());
        }
    }


    //printar ut rummets beskrivning
    public void printRoomDescription(){
        System.out.println(roomDescription);
    }

    //printar ut rummets monster om det finns något
    public void printMonster(){
        if (monster != null){
            System.out.println("There's a " + monster.getName() + " in the room");
        }
    }
    public ArrayList<Item> getItems(){
        return items;
    }


    //printar ut allt i rummet
    public void printRoom(){
        printRoomDescription();
        printItems();
        printMonster();
        printDoors();
    }


    public void addItem(Item item) {
        items.add(item);
    }

    public void removeMonster(){
        monster = null;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    public Door getDoorsDirection(String direction){
        for (Door door : doors){
            if (door.getDirection().equals(direction)){
                return door;
            }
        }
        return null;
    }
    public boolean isLocked(String direction){
        for (Door door : doors){
            if (door.getDirection().equals(direction)){
                if (door.getLocked()){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkDoors(String input) {
        for (Door door : doors){
            if (door.getDirection().equals(input)){
                return true;
            }
        }
        return false;
    }

    public Door getDoor(String input) {
        for (Door door : doors){
            if (door.getDirection().equals(input)){
                return door;
            }
        }
        return null;
    }
}
