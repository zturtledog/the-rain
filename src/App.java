import static com.raylib.Raylib.*;

import main.Iworld;
import main.tilemap;
import main.library.debugdraw;
import main.library.renderer;
import main.library.sprite;

import static com.raylib.Jaylib.Color;

public class App {
    static Iworld world = new Iworld();
    static tilemap map = new tilemap();
    public static void main(String[] args) {
        new renderer(400, 400, "May The Garden Ever Grow", "src/resources/special/dirt.png") {
            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                map.init(world);

                map.states.put("none",new sprite("src/resources/tiles/undiscovererd.png"));
            }
            
            @Override
            public void draw() {
                ClearBackground(new Color(35,35,45,255));

                world.update();
                map.draw(world);
                
                debugdraw.rect(0,0,80,height());
            }

            @Override
            public void resized() {
                //temporary
                int off = ((width() <= height())?width():height())-80;

                //tules

                //@note this may be overwriten when given a value that results in a -0 scenerio
                //fixed tilemap boundry scaling and division by zero
                int ss = ((int)off/map.width);
                ss-=ss%32;
                map.resize(world, ss==0?32:ss);
            }
        };
    }
}
