package lmao.Items;

public abstract class Item {
    private String name;
    private String ItemDescription;
    public Item(String name, String ItemDescription){
        this.name = name;
        this.ItemDescription = ItemDescription;
    }
    public String getName(){
        return name;
    }
    public String getItemDescription(){
        return ItemDescription;
    }
    public abstract String getType();



}
