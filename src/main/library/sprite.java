package main.library;

import static com.raylib.Raylib.DrawTexture;
import static com.raylib.Raylib.ImageResizeNN;
import static com.raylib.Raylib.LoadImage;
import static com.raylib.Raylib.LoadTextureFromImage;

import com.raylib.Jaylib.Color;
import com.raylib.Raylib.Image;
import com.raylib.Raylib.Texture;
import static com.raylib.Raylib.*;

import main.Iworld;

public class sprite {
    Image img;
    Image nimg;
    Texture texture;
    String src;

    public sprite(String tex) {
        src = tex;
        img = LoadImage(tex);
        texture = LoadTextureFromImage(img);
        nimg = img;
    }

    public void resize(Iworld world, int s) {
        nimg = img;

        // no clue if this does anything
        texture.close();

        ImageResizeNN(nimg, s, s);
        texture = LoadTextureFromImage(nimg);
    }

    public void draw(int x, int y) {
        DrawTexture(texture, x, y, new Color(255, 255, 255, 255));
    }

    public void unload() {
        UnloadTexture(texture);
    }

    public int size() {
        return texture.width();
    }

    // public void resize(Iworld world, sizemapinterface smp, int ss) {
    //     nimg = img;

    //     int s = (int) smp.map(ss,img.width()>img.height()?img.width():img.height());

    //     // no clue if this does anything
    //     texture.close();

    //     ImageResizeNN(nimg, s, s);
    //     texture = LoadTextureFromImage(nimg);
    // }
}
