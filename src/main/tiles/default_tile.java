package main.tiles;

import main.Iworld;
import main.sizemapinterface;
import main.tile;
import main.library.sprite;
import main.tilemap.tldata;

public class default_tile extends tile {
    sprite spr;

    public default_tile(String src) {
        spr = new sprite(src);
    }

    @Override
    public void draw(tldata data, int x, int y, int s) {
        spr.draw(x, y);
    }

    @Override
    public void resize(Iworld world, int size) {
        spr.resize(world, size);
    }

    @Override
    public void resize(Iworld world, sizemapinterface smp, int ss) {
        spr.resize(world, (int)smp.map(ss,32));
    }

    @Override
    public void unload() {
        spr.unload();
    }
}
