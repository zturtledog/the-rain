import static com.raylib.Raylib.*;

import main.Iworld;
import main.sizemapinterface;
import main.tilemap;
import main.library.debugdraw;
import main.library.point;
import main.library.renderer;
import main.library.vector_sprite;
import main.tilemap.state;
import main.tilemap.tldata;
import main.tiles.crop_tile;

import static com.raylib.Jaylib.Color;

public class App {
    static Iworld world = new Iworld();
    static tilemap map = new tilemap();
    public static void main(String[] args) {
        new renderer(400, 400, "May The Garden Ever Grow", "src/resources/special/icon.png") {
            vector_sprite test;

            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                map.init(world);

                map.states.put("none",new state("src/resources/states/undiscovererd.png"));

                map.tiles.put("test", new crop_tile("src/resources/tiles/wheat_1.par") {
                    @Override
                    public void activated(tilemap map, Iworld world, tldata data, point pos) {
                        map.at(pos).id="empty";
                    }
                });

                map.at(1, 1).id = "test";
                map.incwidth(this, world);

                test = new vector_sprite("src/resources/tiles/windmill.par");
            }
            
            @Override
            public void draw() {
                ClearBackground(new Color(35,35,45,255));

                world.update();
                map.draw(world);

                test.draw(((0 - map.size / 2) + GetScreenWidth() / 2),(((GetScreenHeight() / 2) - ((map.width * (map.size / 2)) / 2 + map.size / 4))) + map.size / 2, map.size);
                
                debugdraw.rect(0,0,80,height());

                world.scheduled_tick_is_now = false;
            }

            @Override
            public void resized() {
                //temporary
                int off = ((width() <= height())?width():height())-80;

                //tules

                //@note this may be overwriten when given a value that results in a -0 scenerio
                //fixed tilemap boundry scaling and division by zero
                int ss = ((int)off/map.width);
                sizemapinterface smp = (ssa,tr)->(ssa-ssa%tr)==0?tr:(ssa-ssa%tr);
                map.resize(world, smp, ss);
            }

            @Override
            public void unload() {
                map.unload();
            }
        };
    }
}
