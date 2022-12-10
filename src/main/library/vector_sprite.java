package main.library;

import java.util.ArrayList;
import java.util.HashMap;

import main.library.par.pardat;

import com.raylib.Jaylib.Color;
import static com.raylib.Raylib.*;

public class vector_sprite {
    ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    HashMap<String, Color> index = new HashMap<String, Color>();

    int templwid = 0;

    public vector_sprite(String src) {
        pardat raw = new pardat(par.file(src));

        int length = Integer.parseInt((String) raw.get("data:length.length"));
        int idxlen = Integer.parseInt((String) raw.get("index:length.length"));

        templwid = length;

        data = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < length; i++) {
            ArrayList<String> tempstr = par.transarray((String) raw.get("data:" + i + "." + i));

            if (tempstr.size() < templwid) {
                templwid = tempstr.size();
            }

            data.add(tempstr);
        }

        index = new HashMap<String, Color>();
        for (int i = 0; i < idxlen; i++) {
            int[] tempcal = par.hextopcol((String) raw.get("index:" + i + "." + i));
            index.put(("" + i), new Color(tempcal[0], tempcal[1], tempcal[2], tempcal[3]));
        }

        // System.out.println(index);
        // System.out.println(util.intarrtostr(par.hextopcol((String)raw.get("index:0.0"))));
    }

    public void draw(int x, int y, int s) {
        if (templwid > 0)
            for (int i = 0; i < templwid; i++) {
                for (int j = 0; j < templwid; j++) {
                    String slle = data.get(j).get(i);

                    if (index.containsKey(slle))
                    DrawRectangle(
                        x + i * (s / templwid), 
                        y + j * (s / templwid), 
                        s / templwid, 
                        s / templwid,
                        // new Color(x + (i * 18), y + (j * 18), (x + y) / 2, 255)
                        index.get(slle)
                    );
                }
            }
    }
}
