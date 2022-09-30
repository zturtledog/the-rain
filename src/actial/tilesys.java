package actial;

import java.util.ArrayList;
import java.util.HashMap;

import animation.tween;

import static com.raylib.Raylib.*;

import tiles.tile;

public class tilesys {
    HashMap<String, tile> tiles = new HashMap<String, tile>();
    public int width = 5;
    public int maxwidth = 9;
    public int minwidth = 3;

    public ArrayList<ArrayList<String>> tls = new ArrayList<ArrayList<String>>();

    private tile shadow;

    private tween bob = new tween(102, -102);

    private int size;

    public tilesys() {
        shadow = new tile("src/resources/special/shadow.png");
    }

    public void draw(renderer context) {
        if (width < minwidth) {
            width = maxwidth;
        }

        int step = (((int) bob.step()) / 12) - 12;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x = (((j * (size / 2) - (i * size / 2)) - size / 2) + GetScreenWidth() / 2);
                int y = (((i * (size / 4) + (j * (size / 4))))
                        + ((GetScreenHeight() / 2) - ((width * (size / 2)) / 2 + size / 4)));

                // TODO: redo renderer

                shadow.draw(context, x, y + size / 8);// +(size/32*i*2) //+(size/32*2)*(j/2)
                tiles.get("undisc").draw(context, x, (int) (y + step));

            }
        }
    }

    public void resize(renderer context, int s) {
        size = s;
        tiles.forEach((k, v) -> v.resize(context, s));
        shadow.resize(context, s);
    }

    public void regis(String name, tile inst) {
        tiles.put(name, inst);
    }

    public void incwidth() {
        width++;
        if (width > maxwidth) {
            width = maxwidth;
        }
    }

    public void save(savobj save) {
        // TODO: save
    }

    public savobj load() {
        // TODO: load
        return new savobj();
    }
}
