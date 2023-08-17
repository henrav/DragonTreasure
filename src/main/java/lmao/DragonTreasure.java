package lmao;

import lmao.Entities.Dragon;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Scanner;
import lmao.Entities.*;
import lmao.Items.*;


public class DragonTreasure {

    private String WelcomeMessage;
    private String GameOverMessage;
    private String WinMessage;


    public static void setupGame(){
        Player player = new Player("Player", 100, 10);//skapar en spelare


        ArrayList<Room> rooms = new ArrayList<>(); //skapar en arraylist för rummen


        Room room1 = new Room("Room 1", "This is the entrance to the dungeon"); //skapar rum 1 och dess objekt och lägger till dem i arraylisten
        room1.addDoor(new Door("Door 1", "This is door 1", false, "N"));
        room1.addDoor(new Door("Door 2", "This is door 2", false, "E"));
        rooms.add(room1);


        Room room2 = new Room("Room 2", "This room is empty"); //skapar rum 2 och dess objekt och lägger till dem i
        // arraylisten
        room2.addDoor(new Door("Door 3", "This is door 3", false,  "S"));
        room2.addDoor(new Door("Door 4", "This is door 4", false, "N"));
        rooms.add(room2);


        Room room3 = new Room("Room 3", "This is a dimly lit room you cant see very much");//skapar rum 3 och dess objekt och lägger till dem i arraylisten
        room3.addDoor(new Door("Door 5", "This is door 5", false,  "S"));
        Key key = new Key("Key 1", "This is key 1");
        Potion potion = new Potion("The Blood of Gul'dan", "The blood of Gul'dan is a powerful potion that grants the user immense power");
        room3.addItem(potion);
        rooms.add(room3);


        Room room4 = new Room("Room 4", "You can smell something foul coming from the east");//skapar rum 4 och dess objekt och lägger till dem i arraylisten
        room4.addDoor(new Door("Door 6", "This is door 6", false,  "W"));
        room4.addDoor(new Door("Door 7", "This is door 7", false,  "E"));
        room4.addDoor(new Door("Door 8", "This is door 8", false,  "S"));
        rooms.add(room4);


        Entities dragon = new Dragon("Dragon", 100, 10);
        Item weapon = new Weapon("The Ashbringer", "Named for its ability to slaughter undead, leaving nothing but ash in its wake. Ashbringer passed " +
                "through several hands, before it came to the legendary paladin Tirion Fordring, who used it to shatter the Lich King’s runeblade, " +
                "Frostmourne, on the top of Icecrown Citadel", 50);
        Room room5 = new Room("Room 5", "theres no treasure inside this room either", dragon);
        room5.addDoor(new Door("Door 9", "This is door 9", false,  "W"));//skapar rum 5 och dess objekt och lägger till dem i arraylisten
        room5.addItem(weapon);
        room5.addItem(key);
        rooms.add(room5);


        Room room6 = new Room("Room 6", "This room is filled with skeletons and gold coins littering the floor, you wonder what foul creature could have done this");
        room6.addDoor(new Door("Door 10", "This is door 10", false,  "N"));
        room6.addDoor(new Door("Door 11", "This is door 11", true, "S"));//skapar rum 6 och dess objekt och lägger till dem i arraylisten
        rooms.add(room6);


        Dragon dragon2 = new Dragon("Dragon", 100, 30);
        Item treasure = new Treasure("Treasure", "This is treasure");
        Room room7 = new Room("Room 7", "You've finally found the treasure, you see a faint light to the south of this room", dragon2);
        room7.addDoor(new Door("Door 12", "This is door 12", false, "S"));
        room7.addDoor(new Door("Door 13", "This is door 13", false, "N"));//skapar rum 7 och dess objekt och lägger till dem i arraylisten
        room7.addItem(treasure);
        rooms.add(room7);

        Room room8 = new Room("Room 8", "You've finally found the the exit of this forsaken dungeon");//skapar rum 8 och dess objekt och lägger till dem i arraylisten
        room8.addDoor(new Door("Door 14", "This is door 14", false, "N"));


        room1.getDoors().get(0).setLeadsTo(room2);//sätter vilka rum som leder till vilka
        room1.getDoors().get(1).setLeadsTo(room4);//detta kunde endast ske efter att alla rum skapats och lagts till i arraylisten
        room2.getDoors().get(0).setLeadsTo(room1);//Finns säkert bättre sätt att "initialisera" all den här datan i den här klassen
        room2.getDoors().get(1).setLeadsTo(room3);//som typ någon json fil eller text fil, eller nån byte array sak
        room3.getDoors().get(0).setLeadsTo(room2);
        room4.getDoors().get(0).setLeadsTo(room1);
        room4.getDoors().get(1).setLeadsTo(room5);
        room4.getDoors().get(2).setLeadsTo(room6);
        room5.getDoors().get(0).setLeadsTo(room4);
        room6.getDoors().get(0).setLeadsTo(room4);
        room6.getDoors().get(1).setLeadsTo(room7);
        room7.getDoors().get(1).setLeadsTo(room6);
        room7.getDoors().get(0).setLeadsTo(room8);
        room8.getDoors().get(0).setLeadsTo(room7);


        Dungeon.setRooms(rooms); //sätter alla rum i dungeon klassen
        Dungeon.setCurrentRoom(room1);//sätter start rummet
        Dungeon.setPlayer(player);//sätter spelaren
        Dungeon.setWelcomeMessage("Welcome to the DragonTressure" + "\n" + "You are a brave adventurer who has heard of a great treasure hidden in a dungeon" +
                "\n" + "You have finally found the entrance to the dungeon, but beware, there are many dangers lurking inside" + "\n" +
                "To move around in the dungeon you press 's' for South, 'n' for North, 'e' for East and 'w' for West" + "\n" +
                "To pick up an item you press 'v'" + "\n" +
                "To use an Potion you press 'p'" + "\n" +
                "To see your stats you press 'i'" + "\n" +
                "To Quit the game you type 'quit'" + "\n" +
                "To see this guide again you type 'help'" + "\n" +
                "THIS GAME IS CASE SENSITIVE");//sätter
        // välkomstmeddelandet

    }
}
