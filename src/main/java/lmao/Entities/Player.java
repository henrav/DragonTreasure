package lmao.Entities;

import lmao.Drawable;
import lmao.Items.Item;
import lmao.Items.Potion;
import lmao.Items.Treasure;

import java.util.ArrayList;

public class Player extends Entities{
    private ArrayList<Item> inventory = new ArrayList<Item>();


    public Player(String name, int health, int damage){
        super(name, health, damage);
    }


    public void drinkPotion(){
        //kollar om spelaren har en "potion" i sitt inventory, om dem har så dricker dem det
        for (Item item : this.inventory){
            if (item.getType().equals("Potion")){
                this.setHealth(this.getHealth() + ((Potion) item).drink());
                System.out.println("You drank a potion and gained" + " "+((Potion) item).drink() + " " +"health");
                this.inventory.remove(item);
                return;
            }
        }
        System.out.println("You don't have any potions");



    }
    //lägger till ett item till inventoriet om det inte är fullt
    public void addItem(Item item){
        if (inventory.size() < 10){
            inventory.add(item);
        }
    }

    @Deprecated
    public void printInventory(){ //inte använd längre, printar bara ut spelarens items
        if (inventory.size() == 0){
            System.out.println("Your inventory is empty");
        }
        else{
            System.out.println("Your inventory contains:");
            for (Item item : inventory){
                System.out.println(item.getName());
            }
        }
    }
    public void removeItem(Item item){
        inventory.remove(item);
    }
    public ArrayList<Item> getInventory(){
        return inventory;
    }


    //kollar hur mycket damage spelaren gör med vapen
    @Override
    public int getDamage() {
        int damage = super.getDamage(); //använder sig av superklassens getDamage för att få spelarens bas damage
        for (Item item : inventory){
            if (item.getType().equals("Weapon")){
                damage += ((lmao.Items.Weapon) item).getDamage(); //om spelaren har ett vapen så lägger den till vapnets damage till spelarens damage
            }
        }
        return damage;
    }

    @Override
    public void attack(Entities entity) { //attack funktionen som används när spelaren attackerar en fiende
        entity.takeDamage(getDamage());
    }

    public boolean hasKey() { // används för att kolla om spelaren har en nyckel, skulle dock kunna lägga till en klass variable för att se om spelaren har en nyckel
                                //Skulle kanske vara enklare
        for (Item item : inventory){
            if (item.getType().equals("Key")){
                return true;
            }
        }
        return false;
    }

    public boolean hasTreasure() { //kollar om spelaren har en skatt i sitt inventory för att se om dem har vunnit när dem når rum 8
        for (Item item : inventory){
            if (item.getType().equals("Treasure")){
                return true;
            }
        }
        return false;
    }

    public void printTreasure() { //printar ut skatten som spelaren har i sitt inventory
        for (Item item : inventory){
            if (item.getType().equals("Treasure")){
                ((Treasure) item).draw();
            }
        }
    }
}
