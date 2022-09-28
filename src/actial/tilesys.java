package actial;
import java.util.ArrayList;
import java.util.HashMap;

import tiles.tile;

public class tilesys {
    HashMap<String, tile> tiles = new HashMap<String, tile>();
    public int width = 3;

    public ArrayList<String> tls;
    private int size;


    public void draw(renderer context) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tiles.get("dirt").draw(context, j*(size/2)-(i*size/2),(i)*(size/4)+(j*(size/4)));
            }
        }
    }

    public void resize(renderer context, int s) {
        size = s;
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
