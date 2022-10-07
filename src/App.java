//#author: confusedParrotFish
//#purpose: entrypoint and

import static com.raylib.Raylib.*;

import jva.tilesys;
import jva.libish.nxt;
import jva.libish.renderer;
import jva.tiles.default_tile;

import static com.raylib.Jaylib.Color;

public class App {
    static int mode;

    static int padding = 40;

    public static void main(String args[]) {
        new renderer(400, 400, "May The Garden Ever Grow", "src/resources/special/dirt.png") {
            //tile varibles
            tilesys tilemaj;

            //world
            public nxt world;
            
            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                tilemaj = new tilesys();

                tilemaj.init();

                world = new nxt();

                //.init tiles
                tilemaj.regis("undisc", new default_tile("src/resources/tiles/undiscovererd.png")
                    .decor("undisc"));
                tilemaj.regis("desert", new default_tile("src/resources/tiles/desert.png")
                    .decor("blank")
                    .decor("cactus")
                    .decor("cactus_0")
                    .decor("cactus_small_boulder")
                    .decor("cactus_small")
                    .decor("boulder")
                    );

                //.init decorations
                tilemaj.regisdeco("undisc", new default_tile("src/resources/decorations/undisc.png"));
                tilemaj.regisdeco("blank", new default_tile("src/resources/decorations/blank.png"));
                tilemaj.regisdeco("cactus", new default_tile("src/resources/decorations/cactus.png"));
                tilemaj.regisdeco("cactus_0", new default_tile("src/resources/decorations/cactus1.png"));
                tilemaj.regisdeco("cactus_small_boulder", new default_tile("src/resources/decorations/cactusmallboulder.png"));
                tilemaj.regisdeco("cactus_small", new default_tile("src/resources/decorations/cactusmall.png"));
                tilemaj.regisdeco("boulder", new default_tile("src/resources/decorations/boulder.png"));
                
                tilemaj.setid(1,1,"desert");
                tilemaj.decorate(1,1,"cactus");
            }

            @Override
            public void draw() {
                ClearBackground(new Color(35,35,45,255));

                //debug
                if (IsKeyPressed(KEY_ONE)) {
                    nxt.setbool("debuglines", nxt.getbool("debuglines"));
                }

                //draw
                tilemaj.draw(this, world);

                //reveals
                if (tilemaj.iselect && tilemaj.at(tilemaj.selection).id == "undisc") {
                    if (IsMouseButtonPressed(0)) {
                        tilemaj.set(tilemaj.selection.x, tilemaj.selection.y, (tilemaj.tilebyid("desert")).generate());
                    }
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
                tilemaj.resize(world, ss==0?32:ss);
            }
            
            public static int getoff() {
                return ((width() <= height())?width():height());
            }
        };
    }
}