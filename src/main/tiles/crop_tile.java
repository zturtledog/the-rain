package main.tiles;

import java.util.HashMap;

import main.tile;
import main.library.point;
import main.library.vector_sprite;
import main.tilemap.tldata;

public class crop_tile extends tile {
    vector_sprite spr;
    int width = 10;

    HashMap<String, point> offsets = new HashMap<String,point>();

    public crop_tile(String src) {
        spr = new vector_sprite(src);

        offsets = new HashMap<String,point>();
        width = 8;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                offsets.put(i+":"+j,point.random(-5,5));
            }
        }
    }

    @Override
    public void draw(tldata data, int x, int y, int s) {
        // debugdraw.rect(x , y , s/(width*12), s/(width*12));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // acidental triginometry
                // j*s/2-(i*s/2)
                // i*s/4+(j*(s/4))
                int x1 = j*(s/width)/2-(i*(s/width)/2)+s/2;
                int y1 = (i*(s/width)/4+(j*((s/width)/4)))+(s/2);

                // debugdraw.rect(x + x1, y + y1, s/(width*12), s/(width*12));

                point off = offsets.get(i+":"+j);
                int size = (int)((s/1.5) / width);
                spr.draw(x + x1 - (size/2) + off.x, y + y1 +off.y/2 , size);
            }
        }
    }
}
