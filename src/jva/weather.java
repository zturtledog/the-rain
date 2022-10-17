package jva;

import jva.libish.nxt;
import jva.tiles.default_tile;
import jva.tiles.tile;

import static com.raylib.Raylib.*;

import java.util.HashMap;

public class weather {
    int size = 32;

    public HashMap<String,tile> states = new HashMap<String,tile>();

    public String state = "none";
    
    public void draw(nxt world) {
        int width = (int) Math.ceil(GetScreenWidth()/size);
        int height = (int) Math.ceil(GetScreenHeight()/size);

        if (state != "none")
        for (int i = 0; i < width+1; i++) {
            for (int j = 0; j < height+1; j++) {
                states.get(state).draw(i*size, j*size);         //>=<=>=<<=<
            }
        }
    }

    public void resize(nxt world, int s) {
        size = s;

        states.forEach((k, v) -> v.resize(world, s));
    }

    public void regis(String string, default_tile tile) {
        states.put(string, tile);
    }
}
