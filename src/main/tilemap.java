package main;
import static com.raylib.Raylib.*;

import java.util.HashMap;

import main.library.debugdraw;
import main.library.intersect;
import main.library.point;
import main.library.renderer;
import main.library.sprite;

public class tilemap {
    public int width = 3;
    public int size = 32;
    public int maxwidth = 7;

    public boolean iselect = false;

    public point selection = new point(0, 0);

    // .grid
    public tldata[][] tls = new tldata[width + 1][width + 1];

    // .registry
    public HashMap<String,sprite> states = new HashMap<String,sprite>();

    // .sprites in use
    public sprite shadow;
    public sprite select;

    public void draw(Iworld world) {
        //temporary
        int step = (int) Math.round(Math.sin(world.time/10) < 0.5 ? 4 * Math.sin(world.time/10) * Math.sin(world.time/10) * Math.sin(world.time/10) : 1 - Math.pow(-2 * Math.sin(world.time/10) + 2, 3) / 2);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x = (((j * (size / 2) - (i * size / 2)) - size / 2) + GetScreenWidth() / 2);
                int y = (((i * (size / 4) + (j * (size / 4))))
                        + ((GetScreenHeight() / 2) - ((width * (size / 2)) / 2 + size / 4)));

                // .draw

                shadow.draw(x, y+size/8);
                if (states.containsKey(at(i, j).state)) {
                    states.get(at(i, j).state).draw(x, y+step);
                }
                else {
                    //TODO: error
                }

                // .intersection

                point lf = new point(x, y + step + (size / 4));
                point tp = new point(x + size / 2, y + step);
                point rt = new point(x + size, y + size / 4 + step);
                point bt = new point(x + size / 2, y + step + size / 2);
                point bbt = new point(x + size / 2, y + step + size / 2 + (size / 2));
                point brt = new point(x + size, y + size / 4 + step + (size / 2));
                point blf = new point(x, y + step + (size / 4) + (size / 2));

                // debug
                if (world.debug_draw_lines) {
                    debugdraw.quad(lf, tp, rt, bt);
                    if (i + 1 >= width)
                        debugdraw.quad(lf, bt, bbt, blf);
                    if (j + 1 >= width)
                        debugdraw.quad(rt, bt, bbt, brt);
                }

                if ((intersect.quad(lf, tp, rt, bt, new point(GetMouseX(), GetMouseY()))) ||
                        (j + 1 >= width && intersect.quad(rt, bt, bbt, brt, new point(GetMouseX(), GetMouseY()))) ||
                        (i + 1 >= width && intersect.quad(lf, bt, bbt, blf, new point(GetMouseX(), GetMouseY())))
                                && !iselect) {
                    select.draw(x, y+step);
                    iselect = true;
                    selection = new point(i, j);
                }

                //TODO:tile update on tile deco
            }
        }

    }

    public void init(Iworld world) {
        shadow = new sprite("src/resources/special/shadow.png");
        select = new sprite("src/resources/tiles/base.png");

        states = new HashMap<String,sprite>();

        tls = new tldata[width + 1][width + 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tls[i][j] = new tldata();
            }
        }
    }

    // .resize all textures
    public void resize(Iworld world, int s) {
        size = s;
        states.forEach((k, v) -> v.resize(world, s));
        // decorations.forEach((k, v) -> v.resize(world, s));
        shadow.resize(world, s);
        select.resize(world, s);
    }

    public void incwidth(renderer context, Iworld world) {
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

        for (int i = 0; i < width - 2; i++) {
            for (int j = 0; j < width - 2; j++) {
                newtls[i + 1][j + 1] = tls[i][j];
            }
        }

        tls = newtls;

        context.resized();
    }

    // .tile operations
    public void set(int i, int j, tldata data) {
        tls[i][j] = data;
    }

    public void set(point pos, tldata data) {
        tls[pos.x][pos.y] = data;
    }

    public tldata at(int i, int j) {
        return tls[i][j];
    }

    public tldata at(point slct) {
        return tls[slct.x][slct.y];
    }

    class tldata {
        public String state = "none";
    }
}
