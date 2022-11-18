package main.library;
import static com.raylib.Raylib.*;

public class renderer {
    public renderer(int w, int h, String title, String icon) {
        SetConfigFlags(4);

        preload();

        InitWindow(w, h, title);

        SetWindowIcon(LoadImage(icon));

        init();

        resized();

        SetTargetFPS(60);
        while (!WindowShouldClose()) {
            if (IsWindowResized()) {
                resized();
            }
            
            BeginDrawing();

            draw();

            EndDrawing();
        }
        CloseWindow();
    }

    //overides

    public void resized() {}

    public void preload() {}

    public void draw() {}

    public void init() {}

    //interface

    public static int width() {return GetScreenHeight();}
    public static int height() {return GetScreenWidth();}
}
