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
        new renderer(400, 400, "May The Garden Ever Grow", "src/resources/special/dirt.png") {
            //tile varibles
            tilesys tilemaj;
            
            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                tilemaj = new tilesys();

                tilemaj.init();

                //.init tiles
                tilemaj.regis("undisc", new tile("src/resources/tiles/undiscovererd.png"));
                
                // tilemaj.set(1,1,"dirt");

                tilemaj.incwidth(this);
            }

            @Override
            public void draw() {
                ClearBackground(new Color(35,35,45,255));

                //draw
                tilemaj.draw(this);

                //reveals
                if (tilemaj.iselect && tilemaj.at(tilemaj.selection) == "undisc") {
                    // tilemaj.set(tilemaj.selection.x, tilemaj.selection.y, "dirt");
                }
            }

            @Override
            public void resized() {
                int off = getoff()-(padding*2);

                //tules

                //@note this may be overwriten when given a value that results in a -0 scenerio
                //fixed tilemap boundry scaling and division by zero
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