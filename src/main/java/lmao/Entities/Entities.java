package lmao.Entities;

public abstract class Entities {
    private String name;
    private int health;
    private int damage;

    public Entities(String name, int health, int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public String getName(){
        return name;
    }
    public int getHealth(){
        return health;
    }
    public int getDamage(){
        return damage;
    }
    public void attack(Entities entity){
        entity.setHealth(entity.getHealth() - damage);
    }

    public void takeDamage(int damage){
        health -= damage;
    }
}
