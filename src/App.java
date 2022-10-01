//#author: confusedParrotFish
//#purpose: entrypoint and

import static com.raylib.Raylib.*;

import actial.renderer;
import actial.tilesys;
import tiles.tile;

import static com.raylib.Jaylib.Color;

public class App {
    static int mode;

    static int padding = 40;

    public static void main(String args[]) {
        new renderer(400, 400, "May The Garden Ever Grow", "src/resources/tiles/undiscovererd.png") {
            //tile varibles
            tilesys tilemaj;
            
            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                tilemaj = new tilesys();

                //.init tiles
                tilemaj.regis("undisc", new tile("src/resources/tiles/undiscovererd.png"));
                tilemaj.regis("air", new tile("src/resources/special/base.png"));
                tilemaj.regis("dirt", new tile("src/resources/tiles/dirt.png"));
            }

            @Override
            public void draw() {
                ClearBackground(new Color(35,35,45,255));

                //draw
                tilemaj.draw(this);
            }

            @Override
            public void resized() {
                int off = getoff()-(padding*2);

                //tules

                int ss = (int)off/tilemaj.width;
                ss-=ss%32;
                tilemaj.resize(this, ss==0?32:ss);
            }
            
            public static int getoff() {
                if (width() <= height()) {
                    return width();
                }
                return height();
            }
        };
    }

    
}