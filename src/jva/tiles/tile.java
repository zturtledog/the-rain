package jva.tiles;

import jva.libish.renderer;
import jva.tilesys.tldata;

public class tile {
    public String id = "undisc";

    public tile() {
    }

    public void resize(renderer context, int s) {
    }

    public void draw(int x, int y) {
    }

    public void update(tldata data, int i, int j, int x, int y, int s) {}

    public tldata generate() {
        tldata exp = new tldata();
        exp.id = id;

        return exp;
    }
}
