package jva;

import java.util.HashMap;

import jva.animation.tween;
import jva.libish.intersect;
import jva.libish.renderer;
import jva.libish.intersect.point;
import jva.tiles.tile;

import static com.raylib.Raylib.*;

public class tilesys {
    HashMap<String, tile> tiles = new HashMap<String, tile>();
    public int width = 1;
    public int maxwidth = 9;
    public int minwidth = 3;
    private int size;

    //.grid
    public tldata[][] tls = new tldata[width + 1][width + 1];

    //#convenient, but unnesisary 
    private tween bob = new tween(102, -102);
    private tile shadow;
    private tile selectile;

    //.selection interface
    public point selection = new point(0, 0);
    public boolean iselect = false;
    

    //.init
    public tilesys init() {
        shadow = new tile("src/resources/special/shadow.png");
        selectile = new tile("src/resources/special/base.png");

        if (width < minwidth) {
            width = minwidth;
            tls = new tldata[width + 1][width + 1];
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tls[i][j] = new tldata();
            }
        }
        return this;
    }

    // int tmer = 0;
    public void draw(renderer context) {
        if (width < minwidth) {
            width = minwidth;
        }

        // tmer++;
        // if (tmer > 500) {
        // incwidth(context);
        // tmer=0;
        // }
        // System.out.println(tmer);

        iselect = false;

        int step = (((int) bob.step()) / 12) - 12;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x = (((j * (size / 2) - (i * size / 2)) - size / 2) + GetScreenWidth() / 2);
                int y = (((i * (size / 4) + (j * (size / 4))))
                        + ((GetScreenHeight() / 2) - ((width * (size / 2)) / 2 + size / 4)));

                // .draw

                try {
                    shadow.draw(context, x, y + size / 8);// +(size/32*i*2) //+(size/32*2)*(j/2)
                    tiles.get(tls[i][j].id).draw(context, x, (int) (y + step));
                    tiles.get(tls[i][j].id).update(context, tls[i][j], i, j, x, y);
                } catch (Exception e) {
                    System.out.println("Invalid tile id");
                }

                // ?box colom.x
                // debugdraw.quad(new point(x,y+step), new point(x+size,y+step), new
                // point(x+size,y+size/2+step+size/32), new point(x,y+size/2+step+size/32));

                point lf = new point(x, y + step + (size / 4));
                point tp = new point(x + size / 2, y + step);
                point rt = new point(x + size, y + size / 4 + step);
                point bt = new point(x + size / 2, y + step + size / 2);
                point bbt = new point(x + size / 2, y + step + size / 2 + (size / 2));
                point brt = new point(x + size, y + size / 4 + step + (size / 2));
                point blf = new point(x, y + step + (size / 4) + (size / 2));

                // debug
                // debugdraw.quad(lf, tp, rt, bt);
                // if (i + 1 >= width)
                //     debugdraw.quad(lf, bt, bbt, blf);
                // if (j + 1 >= width)
                //     debugdraw.quad(rt, bt, bbt, brt);

                if ((intersect.quad(lf, tp, rt, bt, new point(GetMouseX(), GetMouseY()))) ||
                        (j + 1 >= width && intersect.quad(rt, bt, bbt, brt, new point(GetMouseX(), GetMouseY()))) ||
                        (i + 1 >= width && intersect.quad(lf, bt, bbt, blf, new point(GetMouseX(), GetMouseY()))) && !iselect) {
                    selectile.draw(context, x, y + step);
                    iselect = true;
                    selection = new point(i, j);
                }
            }
        }
    }

    //.resize all textures
    public void resize(renderer context, int s) {
        size = s;
        tiles.forEach((k, v) -> v.resize(context, s));
        shadow.resize(context, s);
        selectile.resize(context, s);
    }

    //.register a tile
    public void regis(String name, tile inst) {
        tiles.put(name, inst);
    }

    //.increment width
    public void incwidth(renderer context) {
        context.resized();

        width += 2;
        if (width > maxwidth) {
            width = maxwidth;
            return;
        }

        tldata[][] newtls = new tldata[width + 1][width + 1];

        for (int i = 0; i < width + 1; i++) {
            for (int j = 0; j < width + 1; j++) {
                newtls[i][j] = new tldata();
            }
        }

        // System.out.println(tls.length+"::"+tls[0].length+"::"+width);

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < width - 2; j++) {
                newtls[i + 1][j + 1] = tls[i][j];
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

    public void set(int i, int j, String string) {
        tls[i][j].id = string;
    }

    public String at(point slct) {
        return tls[slct.x][slct.y].id;
    }

    public class tldata {
        public String id = "undisc";
    }
}
