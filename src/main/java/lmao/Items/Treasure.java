package lmao.Items;

import lmao.Drawable;

public class Treasure extends Item implements Drawable {
    private int value;
    public Treasure(String name, String ItemDescription) {
        super(name, ItemDescription);
    }

    @Override
    public String getType() {
        return "Treasure";
    }


    @Override
    public void draw() {
        System.out.println(
                "                  _.--.\n"+
                        "              _.-'_:-'||\n"+
                        "          _.-'_.-::::'||\n"+
                        "     _.-:'_.-::::::'  ||\n"+
                        "   .'`-.-:::::::'     ||\n"+
                        "  /.'`;|:::::::'      ||_\n"+
                        " ||   ||::::::'      _.;._'-._\n"+
                        " ||   ||:::::'   _.-!oo @.!-._'-.\n"+
                        " \'.  ||:::::.-!() oo @!()@.-'_.||\n"+
                        "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n"+
                        "     `>'-.!@%()@'@_%-'_.-o _.|'||\n"+
                        "      ||-._'-.@.-'_.-' _.-o  |'||\n"+
                        "      ||=[ '-._.-\\U/.-'    o |'||\n"+
                        "      || '-.]=|| |'|      o  |'||\n"+
                        "      ||      || |'|        _| ';\n"+
                        "      ||      || |'|    _.-'_.-'\n"+
                        "      |'-._   || |'|_.-'_.-'\n"+
                        "      '-._'-.|| |' `_.-'\n"+
                        "           '-.||_/.-'\n");
    }
}
