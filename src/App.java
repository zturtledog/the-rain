//#author: confusedParrotFish
//#purpose: entrypoint and

import static com.raylib.Raylib.*;

import jva.tilesys;
import jva.weather;
import jva.libish.nxt;
import jva.libish.point;
import jva.libish.renderer;
import jva.tiles.default_tile;
import jva.tilesys.tldata;

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

            private weather wther;
            
            @Override
            public void init() {
                SetWindowMinSize(400, 400);

                tilemaj = new tilesys();

                tilemaj.maxwidth=100;

                tilemaj.init();

                world = new nxt();

                //.init tiles
                tilemaj.regis("undisc", new default_tile("src/resources/tiles/undiscovererd.png") {
                        @Override public void activated(point slc, tilesys tilesys, nxt world, tldata data) {
                            tilesys.set(slc.x, slc.y, tilesys.tilebyid("farmland").generate());//tilesys.at(new point(x, y)).id
                        };
                    }.decor("undisc"));
                tilemaj.regis("farmland", new default_tile("src/resources/tiles/farmland.png")
                    .decor("blank"));
                tilemaj.regis("desert", new default_tile("src/resources/tiles/desert.png")
                    .decor("blank")
                    .decor("cactus")
                    .decor("cactus_0")
                    .decor("cactus_small_boulder")
                    .decor("cactus_small")
                    .decor("boulder"));
                tilemaj.regis("water", new default_tile("src/resources/tiles/water.png")
                    .decor("lilipad")
                    .decor("lilipad_0")
                    .decor("blank")
                    .decor("blank")
                    .decor("blank"));

                //.init decorations
                tilemaj.regisdeco("undisc", new default_tile("src/resources/decorations/undisc.png"));
                tilemaj.regisdeco("blank", new default_tile("src/resources/decorations/blank.png"));

                tilemaj.regisdeco("cactus", new default_tile("src/resources/decorations/cactus.png"));
                tilemaj.regisdeco("cactus_0", new default_tile("src/resources/decorations/cactus1.png"));
                tilemaj.regisdeco("cactus_small_boulder", new default_tile("src/resources/decorations/cactusmallboulder.png"));
                tilemaj.regisdeco("cactus_small", new default_tile("src/resources/decorations/cactusmall.png"));
                tilemaj.regisdeco("boulder", new default_tile("src/resources/decorations/boulder.png"));

                tilemaj.regisdeco("lilipad", new default_tile("src/resources/decorations/lilipad.png"));
                tilemaj.regisdeco("lilipad_0", new default_tile("src/resources/decorations/lilipad1.png"));
                tilemaj.regisdeco("cattails", new default_tile("src/resources/decorations/cattails.png"));

                //.init debug
                world.setbool("bounce", true);

                //init world var
                world.setbool("nvoidil", true);

                //.init weather
                wther = new weather();

                wther.regis("rain",new default_tile("src/resources/special/null_safty.png.png"));

                //.test
                // tilemaj.setid(1,1,"desert");
                // tilemaj.decorate(1,1,"cactus");
                tilemaj.set(1, 1, tilemaj.tilebyid("desert").generate());

                wther.state = "none";
            }

            @Override
            public void draw() {
                if (world.getbool("nvoidil")){
                ClearBackground(new Color(60,60,180,255));}//
                if (!world.getbool("nvoidil")){
                ClearBackground(new Color(35,35,45,255));}

                //debug
                if (IsKeyPressed(KEY_ONE)) {
                    world.setbool("debuglines", !world.getbool("debuglines"));
                }
                if (IsKeyPressed(KEY_TWO)) {
                    world.setbool("bounce", !world.getbool("bounce"));
                }
                if (IsKeyPressed(KEY_THREE)) {
                    wther.state = wther.state!="rain"?"rain":"none";
                }
                if (IsKeyPressed(KEY_FOUR)) {
                    world.setbool("nvoidil", !world.getbool("nvoidil"));
                }

                //draw
                tilemaj.draw(this, world);
                wther.draw(world);
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
                wther.resize(world, ss==0?32:ss);
            }
            
            public static int getoff() {
                return ((width() <= height())?width():height());
            }
        };
    }
}