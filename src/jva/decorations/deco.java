package jva.decorations;

import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;

import jva.libish.renderer;
import jva.tilesys.tldata;

import static com.raylib.Raylib.*;

import java.util.HashMap;

import static com.raylib.Jaylib.Color;


public class deco {
    Image img;
    Image nimg;
    Texture texture;
    String src;

    public deco(String tex) {
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

    public void draw(HashMap<String,deco> decor, tldata data, int x, int y, int s) {
        // if (data != null) {
        //     data.decorations.forEach(id -> decor.get(id).draw(context, null, null, x, y-s/2+s/32, 0));
        // }

        DrawTexture(texture, x, y, new Color(255,255,255,255)); 
    }
}
