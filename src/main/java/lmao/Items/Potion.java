package lmao.Items;

public class Potion extends Item{
    private int health = 100;
    public Potion(String name, String ItemDescription) {
        super(name, ItemDescription);
    }
    public void setHealth(int health){
        this.health = health;
    }


    //
    public int drink(){
        return health;
    }

    @Override
    public String getType() {
        return "Potion";
    }
}
