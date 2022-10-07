package jva.tiles;

import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;
import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.Color;

import jva.libish.renderer;

public class default_tile extends tile {
    Image img;
    Image nimg;
    Texture texture;
    String src;

    public default_tile(String tex) {
        src = tex;
        img = LoadImage(tex);
        texture = LoadTextureFromImage(img);
        nimg = img;
    }

    @Override
    public void resize(renderer context, int s) {
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

}
