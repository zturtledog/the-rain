package jva.tiles;

import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import static com.raylib.Jaylib.Color;

import jva.libish.nxt;
import jva.tilesys.tldata;

public class default_tile extends tile {
    Image img;
    Image nimg;
    Texture texture;
    String src;

    public ArrayList<String> pdeco = new ArrayList<String>();

    public default_tile(String tex) {
        src = tex;
        img = LoadImage(tex);
        texture = LoadTextureFromImage(img);
        nimg = img;
    }

    @Override
    public void resize(nxt world, int s) {
        System.out.println("xpanse");

        nimg = img;

        // no clue if this does anything
        texture.close();

        ImageResizeNN(nimg, s, s);
        texture = LoadTextureFromImage(nimg);
    }

    @Override
    public void draw(int x, int y) {
        DrawTexture(texture, x, y, new Color(255, 255, 255, 255));
    }

    public default_tile decor(String id) {
        pdeco.add(id);

        return this;
    }

    @Override
    public tldata generate() {
        tldata spr = super.generate();

        if (pdeco.size()>0)
        spr.decorations = pdeco.get(Math.round((float)(Math.random()*(pdeco.size()-1))));

        return spr;
    }
}
