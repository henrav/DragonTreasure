package lmao.Items;

public class Key extends Item{
    public Key(String name, String ItemDescription) {
        super(name, ItemDescription);
    }

    @Override
    public String getType() {
        return "Key";
    }

    public String getKeys() {
        return "Key";
    }
}
