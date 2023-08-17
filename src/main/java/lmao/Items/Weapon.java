package lmao.Items;

public class Weapon extends Item{
    private int damage;
    public Weapon(String name, String ItemDescription, int damage) {
        super(name, ItemDescription);
        this.damage = damage;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }

    @Override
    public String getType() {
        return "Weapon";
    }

    public int getDamage() {
        return damage;
    }
}
