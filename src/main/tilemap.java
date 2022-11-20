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
    public HashMap<String, state> states = new HashMap<String, state>();
    public HashMap<String, tile> tiles = new HashMap<String, tile>();

    // .sprites in use
    public sprite shadow;
    public sprite select;

    // bounce anim
    int step = 0;
    float step_lerp = 0;
    int step_dir = -1;
    int step_height = 15;

    public void draw(Iworld world) {
        // temporary
        step_height = size / 8 / 2;// -/20
        if (world.debug_animate_bounce) {
            if (step_lerp >= step_height) {
                step_dir = -1;
            } else if (step_lerp <= -step_dir) {
                step_dir = 1;
            }
            step_lerp += ((float) step_dir) / 7;
            step = (int) Math.round(step_lerp);
        } else {
            step = 0;
        }
        // System.out.println(step_lerp);

        iselect = false;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x = (((j * (size / 2) - (i * size / 2)) - size / 2) + GetScreenWidth() / 2);
                int y = (((i * (size / 4) + (j * (size / 4))))
                        + ((GetScreenHeight() / 2) - ((width * (size / 2)) / 2 + size / 4)));

                // .draw

                shadow.draw(x, y + size / 8);
                if (states.containsKey(at(i, j).state)) {
                    states.get(at(i, j).state).draw(x, y + step);
                } else {
                    // TODO: error
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

                if (!iselect) {
                    if (intersect.quad(lf, tp, rt, bt, new point(GetMouseX(), GetMouseY())) ||
                            (j + 1 >= width && intersect.quad(rt, bt, bbt, brt, new point(GetMouseX(), GetMouseY()))) ||
                            (i + 1 >= width && intersect.quad(lf, bt, bbt, blf, new point(GetMouseX(), GetMouseY())))) {
                        select.draw(x, y + step);
                        iselect = true;
                        selection = new point(i, j);
                    }
                }

                if (tiles.containsKey(at(i, j).id)) {
                    tiles.get(at(i, j).id).draw(at(i, j), x, y + step - size / 2 + size / 32, size);

                    if (world.scheduled_tick_is_now) {
                        tiles.get(at(i, j).id).update(this, world, at(i, j), i, j);
                    }
                }
            }
        }

        if (iselect) {
            if (IsMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                if (tiles.containsKey(at(selection).id)) {
                    tiles.get(at(selection).id).activated(this, world, at(selection), selection);
                }
            }
        }
    }

    public void init(Iworld world) {
        shadow = new sprite("src/resources/special/shadow.png");
        select = new sprite("src/resources/tiles/base.png");

        states = new HashMap<String, state>();

        tls = new tldata[width + 1][width + 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                tls[i][j] = new tldata();
            }
        }
    }

    public void unload() {
        states.forEach((k, v) -> v.unload());
        tiles.forEach((k, v) -> v.unload());
        shadow.unload();
        select.unload();
    }

    // .resize all textures
    public void resize(Iworld world, sizemapinterface smp, int ss) {
        size = (int) smp.map(ss,32);
        states.forEach((k, v) -> v.resize(world, size));
        tiles.forEach((k, v) -> v.resize(world, smp, ss));
        shadow.resize(world, size);
        select.resize(world, size);
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

    public class tldata {
        public String state = "none";
        public String id = "";
    }

    public static class state extends sprite {
        public state(String tex) {
            super(tex);
        }

        public void update(tilemap map, Iworld world, tldata data, int i, int j) {
            //
        }
    }
}
