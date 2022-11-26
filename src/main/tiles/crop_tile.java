package main.tiles;

import main.tile;
import main.library.vector_sprite;
import main.tilemap.tldata;

public class crop_tile extends tile {
    vector_sprite spr;

    public crop_tile(String src) {
        spr = new vector_sprite(src);
    }

    @Override
    public void draw(tldata data, int x, int y, int s) {
        int width = 12;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x1 = (((j * (s / 2) - (i * s / 2)) - s / 2)) + s;
                int y1 = (((i * (s / 4) + (j * (s / 4))))
                        + (((width * (s / 2)) / 2 + s / 4))) - s;

                spr.draw(x + x1, y + y1, s);
            }
        }
    }
}
