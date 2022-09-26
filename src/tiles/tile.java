package tiles;
import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;

import actial.renderer;

import static com.raylib.Raylib.*;
import static com.raylib.Jaylib.Color;

public class tile {
    Image img;
    Image nimg;
    Texture texture;

    public tile(String tex) {
        img = LoadImage(tex);
        texture = LoadTextureFromImage(img);
        nimg = img;
    }

    public void resize(renderer context, int s) {
        nimg = img;
        ImageResizeNN(nimg, s, s);
        texture = LoadTextureFromImage(nimg); 
    }

    public void draw(renderer context, int x, int y) {
        DrawTexture(texture, x, y, new Color(255,255,255,255)); 
    }
}
