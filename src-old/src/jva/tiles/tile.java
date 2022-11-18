package jva.tiles;

import jva.tilesys;
import jva.libish.nxt;
import jva.libish.point;
import jva.tilesys.tldata;

public class tile {
    public String id = "undisc";

    public tile() {
    }

    public void resize(nxt world, int s) {
    }

    public void draw(int x, int y) {
    }

    public void update(tldata data, int i, int j, int x, int y, int s) {}

    public tldata generate() {
        tldata exp = new tldata();
        exp.id = id;

        return exp;
    }

    public void activated(point selection, tilesys tilesys, nxt world, tldata tldata) {
    }
}
