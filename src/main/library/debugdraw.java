package main.library;

import static com.raylib.Raylib.*;

import static com.raylib.Jaylib.Color;


public class debugdraw {
    // void DrawLine(int startPosX, int startPosY, int endPosX, int endPosY, Color color); 

    public static void triangle(point a, point b, point c) {
        Color color = new Color(255,0,0,255);

        DrawLine( a.x,  a.y,  b.x,  b.y, color);
        DrawLine( b.x,  b.y,  c.x,  c.y, color);
        DrawLine( c.x,  c.y,  a.x,  a.y, color);
    }

    public static void quad(point a, point b, point c, point d) {
        Color color = new Color(255,0,0,255);

        DrawLine( a.x,  a.y,  b.x,  b.y, color);
        DrawLine( b.x,  b.y,  c.x,  c.y, color);

        // DrawLine( b.x,  b.y,  d.x,  d.y, color);
        
        DrawLine( c.x,  c.y,  d.x,  d.y, color);
        DrawLine( d.x,  d.y,  a.x,  a.y, color);
    }

    public static void rect(int x, int y, int w, int h) {
        Color color = new Color(255,0,0,255);

        DrawLine( x,  y,  x+w,  y, color);
        DrawLine( x+w,  y,  x+w, y+h, color);
        DrawLine( x+w,  y+h,  x,  h+y, color);
        DrawLine( x,  y+y,  x,  y, color);
    }
}
