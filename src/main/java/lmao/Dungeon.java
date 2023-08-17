package lmao;

import lmao.Entities.Dragon;
import lmao.Entities.Entities;
import lmao.Entities.Player;
import lmao.Items.Item;
import lmao.Items.Treasure;

import java.util.ArrayList;
import java.util.Scanner;

import static lmao.DragonTreasure.setupGame;

public class Dungeon {
    private static Room currentRoom;

    private static Room lastRoom;
    private static ArrayList<Room> rooms = new ArrayList<>();


    private static Player player;

    private static String welcomeMessage;
    private static boolean playing = true;



    public static void main(String[] args) {
        playGame();
    }


    private static void playGame() {
        setupGame();// skapar alla object som behövs för spelet och listor med alla rum och items
        System.out.println(welcomeMessage);
        System.out.println("Do you want to play? (y/n)"); // kollar om spelaren är tillräckligt modig för att spela spelet
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("n")) { // om spelaren inte vill spela så avslutas spelet
            System.exit(0);
        } else if (input.equals("y")) {
            while (playing) {  //spelet körs så länge spelaren lever eller inte har vunnit
                checkForEnemies(); // kollar först for fiender i rummet så den inte börjar snacka om rummet innan fienden är död
                checkPlayerDeath();
                currentRoom.printRoom(); //printar rummet och dess dörrar, nu i efterhand verkar det lite dumt att printa det varje gång, kanske endast när
                // man faktiskt byter
                input = scanner.nextLine();
                inputHandler(input);// tar hand om användarens input, metoden finns på rad 95
                checkWin();// kollar om spelaren har vunnit// kollar om spelaren har dött
            }

        }
        else {
            System.out.println("Invalid input");
            playGame();
        }
    }



    public static void unLock(Door door){
        if (player.hasKey()){ //kollar om spelaren har en nyckel i sitt inventory
            door.setLocked(false);
            System.out.println("You unlocked the door");
            lastRoom = currentRoom;
            currentRoom = door.getLeadsTo(); //byter rum
        }
        else{
            System.out.println("You don't have a key"); //spelaren hade inte nyckeln
        }
    }



    public static void changeRoom(Door door){
        if (door.getLocked()){     //om dörren är låst
            System.out.println("The door is locked");
            System.out.println("Do you want to unlock it? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("y")){
                unLock(door);  // om spelaren vill låsa upp kallas "unLock" metoden
            }
            if (input.equals("n")){
                System.out.println("You stay in the same room");
            }
        }
        else { //om den inte är låst
            lastRoom = currentRoom;
            currentRoom = door.getLeadsTo();
        }

    }
    public static void inputHandler(String input){//ser jätte fult ut med alla if satser, försökte med en "switch" sats men defaulten var lite konstig så
        // printa ut "invalid input" varje gång, var troligtvist något jag missa men funkar med if satser så jag lämnar det så
        if (currentRoom.checkDoors(input)){ //kollar om inputen är en dörr och retunerar true om det finns en dörr med denna riktning
            changeRoom(currentRoom.getDoor(input));//om det finns byter den rum och kallar "changeRoom" metoden
        }
        else if (input.equals("help")){
            System.out.println(welcomeMessage);
        }
        else if(input.equals("quit")){
            System.exit(0);
        }
        else if (input.equals("p")){
            player.drinkPotion();
        }
        else if (input.equals("i")){
            System.out.println("You have " + player.getHealth() + " health"); //kunde a en funktion i "Player" som printar ut allt det här
            System.out.println("You have " + player.getDamage() + " damage");
            System.out.println("You have " + player.getInventory().size() + " items");
            for (Item item : player.getInventory()){
                System.out.println(item.getName());
            }
        }
        else if (input.equals("v")){ //kollar om spelaren vill plocka upp items
            pickUpItems();
        }
        else {
            System.out.println("Invalid input");
        }
    }

    private static void pickUpItems() { //kanske borde ha den här metoden i "Player" för det är ju trots allt han som plockar upp items
        if (currentRoom.getItems().size() > 0) {
            System.out.println("You found some items");
            for (Item item : currentRoom.getItems()) {
                System.out.println("["+"'"+item.getName()+"'"+"]");
                System.out.println("["+ "'"+item.getItemDescription()+"'" +"]");
                player.addItem(item);
            }
            currentRoom.getItems().clear();
        }
        else{
            System.out.println("There are no items in this room");
        }
    }


    // tar hand om användarens input

    public static void checkWin(){
        if (currentRoom.getRoomName().equals("Room 8") && player.hasTreasure()){ //kollar om spelaren har skatten och är i rum 8
            System.out.println("You won the game");
            currentRoom.printRoomDescription();
            Dungeon.playing = false;
        }
        else if (currentRoom.getRoomName().equals("Room 8") && !player.hasTreasure()){ //kollar om spelaren inte har skatten och är i rum 8
            System.out.println("You need the treasure to win");
            System.out.println("You go back to the previous room because you don't have the treasure");
        }
    }
    public static boolean checkPlayerDeath(){
        if (player.getHealth() <= 0){ //kollar om spelaren har 0 eller mindre i health
            System.out.println("You died");
            System.exit(0);
            return true;
        }
        return false;
    }
    public static boolean checkMonsterDeath(){
        if (currentRoom.getMonster().getHealth() <= 0){
            System.out.println("You killed the " + currentRoom.getMonster().getName());
            currentRoom.removeMonster();
            return true;
        }
        else{
            return false;
        }
    }


    public static void checkForEnemies(){   //kollar om det finns några monster i rummet, om det finns så frågar den om spelaren vill slåss
                                            // om spelaren inte vill slåss så går den tillbaka till förra rummet
                                            //som spelaren vill så kallas "combat" metoden
        if (currentRoom.getRoomName().equals("Room 7") && currentRoom.getMonster() != null){ //om man går in i sista rummet kan man inte undvika att slåss
            // med draken
            System.out.println("It is the last boss of this corrupt dungeon, you can't out run your fate, you'll have to fight him");
            ((Dragon)currentRoom.getMonster()).draw(); //ritar draken, finns i dragon klassen
            combat();
        }

        else if (currentRoom.getMonster() != null) { //kollar om rummet har ett monster i sig
            System.out.println("There is a " + currentRoom.getMonster().getName() + " in the room");
            System.out.println("Do you want to fight it? (y/n)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("y")) {
                combat();
                return;
            }
            if (input.equals("n")) {
                currentRoom = lastRoom;
                System.out.println("You went back to the previous room");
            } else {
                System.out.println("(y/n)!");
                checkForEnemies();

            }
        }
}




    public static void combat() { //kollar om spelaren eller monstret har dött, om inte så fortsätter de att slåss
        boolean combat = true;
        System.out.println("You are fighting a " + currentRoom.getMonster().getName());
        System.out.println("You have " + player.getHealth() + " health");
        while (combat) {
            System.out.println("Press a to attack");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "a":
                    player.attack(currentRoom.getMonster());
                    System.out.println("You attacked the " + currentRoom.getMonster().getName() + " for " + player.getDamage() + " damage");
                    if (checkMonsterDeath()) {
                        combat = false;
                    }
                    else {
                        currentRoom.getMonster().attack(player);
                        System.out.println("The " + currentRoom.getMonster().getName() + " attacked you for " + currentRoom.getMonster().getDamage() + " damage");
                        if (checkPlayerDeath()) {
                            combat = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }
    public static void setCurrentRoom(Room room){ //dessa används i "DragonTreasure" klassen för att sätta alla saker
        currentRoom = room;
    }
    public static void setRooms(ArrayList<Room> room ){
        rooms = room;
    }
    public static void setPlayer(Player spelare){
        player = spelare;
    }
    public static void setWelcomeMessage(String message){
        welcomeMessage = message;
    }






}
