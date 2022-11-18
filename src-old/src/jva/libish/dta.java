package jva.libish;

import java.util.ArrayList;

public class dta {
    public static nxt load(String data) {
        nxt end = new nxt();
        nxt vrs = new nxt();

        Object[] lines = data.split("/\r?\n/");

        for (int i = 0; i < lines.length; i++) {
            lines[i] = ((String)lines[i]).trim();

            String line = ((String)lines[i]);

            if (line.length()>0) {
                if (line.charAt(0) != '#') {
                    if (line.charAt(0) != ':') {
                        if (line != ":end") {
                            //
                        } else {
                            //
                        }
                    } 
                    else if (line.charAt(0) != '.') {
                        String[] lndta = line.substring(1).split("=");

                        vrs.set(lndta[0].trim().replaceFirst(".", ""), lndta[1].trim());
                    } 
                    else {
                        //key
                    }
                }
            }
        }

        return end;
    }
}
