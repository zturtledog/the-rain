package actial;

import java.util.HashMap;

import animation.tween;

import static com.raylib.Raylib.*;

import tiles.tile;

public class tilesys {
    HashMap<String, tile> tiles = new HashMap<String, tile>();
    public int width = 3;
    public int maxwidth = 9;
    public int minwidth = 3;

    public String[][] tls = new String[width+1][width+1];

    private tile shadow;

    private tween bob = new tween(102, -102);

    private int size;

    public tilesys() {
        shadow = new tile("src/resources/special/shadow.png");

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tls[i][j] = "undisc";
            }
        }
    }

    // int tmer = 0;
    public void draw(renderer context) {
        if (width < minwidth) {
            width = maxwidth;
        }

        // tmer++;
        // if (tmer > 500) {
        //     incwidth(context);
        //     tmer=0;
        // }
        // System.out.println(tmer);

        int step = (((int) bob.step()) / 12) - 12;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x = (((j * (size / 2) - (i * size / 2)) - size / 2) + GetScreenWidth() / 2);
                int y = (((i * (size / 4) + (j * (size / 4))))
                        + ((GetScreenHeight() / 2) - ((width * (size / 2)) / 2 + size / 4)));

                shadow.draw(context, x, y + size / 8);// +(size/32*i*2) //+(size/32*2)*(j/2)
                tiles.get(tls[i][j]).draw(context, x, (int) (y + step));
                tiles.get(tls[i][j]).update(context, i, j);
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

    public void incwidth(renderer context) {
        context.resized();

        width+=2;
        if (width > maxwidth) {
            width = maxwidth;
            return;
        }

        String[][] newtls = new String[width+1][width+1];

        for (int i = 0; i < width+1; i++) {
            for (int j = 0; j < width+1; j++) {
                newtls[i][j] = "undisc";
            }
        }

        // System.out.println(tls.length+"::"+tls[0].length+"::"+width);

        for (int i = 0; i < width-2; i++) {
            for (int j = 0; j < width-2; j++) {
                newtls[i+1][j+1] =  
                tls[i][j];
            }
        }

        tls = newtls;
    }

    public void save(savobj save) {
        // TODO: save
    }

    public savobj load() {
        // TODO: load
        return new savobj();
    }
}
