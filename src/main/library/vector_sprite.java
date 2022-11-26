package main.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import main.library.par.pardat;

import com.raylib.Jaylib.Color;
import static com.raylib.Raylib.*;

public class vector_sprite {
    ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();

    int templwid = 0;

    public vector_sprite(String src) {
        HashMap<String, pardat> raw = par.file(src);

        // for (Entry<String, pardat> entry : raw.entrySet()) {
        // String key = entry.getKey();
        // pardat value = entry.getValue();

        // System.out.println(key+":"+value.toString());
        // }

        // if (raw.containsKey("settings")) {
        // if (raw.get("settings").isblock) {
        // if (raw.get("settings").block.containsKey("animate") &&
        // !raw.get("settings").block.get("animate").isblock &&
        // raw.get("settings").block.get("animate").value.equals("false")) {

        templwid = raw.containsKey("data")
                ? Integer.parseInt(raw.get("data").block.get("frame-0").block.get("length").value)
                : 1;

        if (raw.containsKey("data")) {
            for (int i = 0; i < templwid; i++) {
                System.out.println(par.transarray(raw.get("data").block.get("frame-0").block.get(""+i).value).size());
            }
        }
    }

    public void draw(int x, int y, int s) {
        if (templwid > 0)
            for (int i = 0; i < templwid; i++) {
                for (int j = 0; j < templwid; j++) {
                    DrawRectangle(x + i * (s / templwid), y + j * (s / templwid), s / templwid, s / templwid,
                            new Color(x + (i * 18), y + (j * 18), (x + y) / 2, 255));
                }
            }
    }
}
