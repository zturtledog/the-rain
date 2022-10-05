package jva.tiles;

import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;

import jva.libish.renderer;
import jva.tilesys.tldata;

import static com.raylib.Raylib.*;

import java.util.HashMap;

import static com.raylib.Jaylib.Color;

public class tile {
    Image img;
    Image nimg;
    Texture texture;
    String src;

    public tile(String tex) {
        src = tex;
        img = LoadImage(tex);
        texture = LoadTextureFromImage(img);
        nimg = img;
    }

    public void resize(renderer context, int s) {
        System.out.println("xpanse");

        nimg = img;

        //no clue if this does anything
        texture.close();
        
        ImageResizeNN(nimg, s, s);
        texture = LoadTextureFromImage(nimg); 
    }

    public void draw(renderer context, HashMap<String,tile> deco, tldata data, int x, int y) {
        if (data != null) {
            data.decorations.forEach(id -> deco.get(id).draw(context, null, null, x, y));
        }

        DrawTexture(texture, x, y, new Color(255,255,255,255)); 
    }
    public void update(renderer context, tldata data, int i, int j, int x, int y) {}
}
