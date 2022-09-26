package actial;
import java.util.HashMap;

import tiles.tile;

public class tilesys {
    HashMap<String, tile> tiles = new HashMap<String, tile>();
    public int width = 1;

    public void draw(renderer context) {
    }

    public void resize(renderer context, int s) {
        tiles.forEach((k,v) -> v.resize(context, s));
    }

    public void regis(String name, tile inst) {
        tiles.put(name, inst);
    }

    public void save(savobj save) {
        //TODO: save
    }

    public savobj load() {
        //TODO: load
        return new savobj();
    }
}
