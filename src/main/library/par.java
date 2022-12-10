package main.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class par {
    public static HashMap<String, pardat> file(String path) {
        ArrayList<String> strings = new ArrayList<String>();
        HashMap<String, pardat> lines = new HashMap<String, pardat>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine().trim();
                if (line.length() > 0)
                    strings.add(line);
            }
            myReader.close();

            lines = parse(strings);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return lines;
    }

    private static HashMap<String, pardat> parse(ArrayList<String> lines) {
        boolean recording = false;
        int depth = 0;
        String pline = "";
        HashMap<String, pardat> surface = new HashMap<String, pardat>();
        ArrayList<String> record = new ArrayList<String>();

        for (String line : lines) {
            if (!line.startsWith("##")) {
                if (line.equals(":{")) {
                    if (!recording) {
                        recording = true;
                    }
                    depth++;
                } else if (line.equals("}")) {
                    if (depth == 1) {
                        recording = false;
                        record.remove(0);
                        surface.put(pline, new pardat(parse(record)));
                        record.clear();
                    }
                    depth--;
                }

                if (recording) {
                    record.add(line);
                } else {
                    pline = line;
                    if (line.contains(":")) {
                        surface.put(line.split(":")[0].trim(),
                                new pardat(line.replaceFirst(line.split(":")[0] + ":", "").trim()));
                    }
                }
            }
        }
        return surface;
    }

    public static class pardat {
        public boolean isblock = false;
        public HashMap<String, pardat> block;
        public String value = "";

        public pardat(HashMap<String, pardat> map) {
            isblock = true;
            block = map;
        }

        public pardat(String val) {
            value = val;
        }

        public Object get(String path) {
            /*
             * >mcv3
             * .split ":"
             * #also do assurances
             * if isblock
             * recurse ^[0]
             * return
             * else
             * return value
             */

            String[] ppth = path.split(":", 2);

            // System.out.println(ppth.length+" : "+ppth[0]+"["+(ppth.length>1?ppth[1]:"none")+"]");

            // todo:assurences

            if (ppth.length > 1) {
                if (isblock) {
                    if (block.containsKey(ppth[0])) {
                        return block.get(ppth[0]).get(ppth[1]);
                    } else {
                        return block;
                    }
                } else {
                    return value;
                }
            } else {// todo:assurences
                if (isblock) {
                    if (ppth[0].contains(".")) {
                        String[] pppth = ppth[0].split("\\.",2);

                        if(block.containsKey(pppth[0])) {
                            return block.get(pppth[0]).get(pppth[1]);
                        }
                    }
                    return block;
                } else {
                    return value;
                }
            }
        }

        @Override
        public String toString() {
            if (isblock) {
                return block.toString();// "[is a block]";
            }
            return value;
        }
    }

    public static ArrayList<String> transarray(String src) {
        ArrayList<String> results = new ArrayList<String>();
        String record = "";
        boolean instring = false;
        boolean inarray = false;
        int depth = 0;

        for (int i = 1; i < src.length() - 1; i++) {
            if (!inarray && !instring) {
                if (src.charAt(i) == ',') {
                    results.add(record);
                    record = "";
                }
            }

            if (src.charAt(i) == '\'')
                instring = !instring;

            if (src.charAt(i) == '[') {
                inarray = true;
                depth++;
            }

            if (src.charAt(i) == ']') {
                if (depth == 1) {
                    inarray = false;
                }
                depth--;
            }

            if (!inarray && !instring && !(src.charAt(i) == ',')) {
                record += src.charAt(i);
            } // 9,'gorp,',[0,'hehhe',[]]
        }
        results.add(record);
        return results;
    }

    public static int[] hextopcol(String inp) {
        String line = inp.substring(1);
        System.out.println(line);
        int[] retrn = { 0, 0, 0, 255 };

        if (line.length() > 5) {// defaultalpha
            retrn[0] = Integer.parseInt(line.substring(0, 2), 16);
            retrn[1] = Integer.parseInt(line.substring(2, 4), 16);
            retrn[2] = Integer.parseInt(line.substring(4, 6), 16);
            if (line.length() > 6) {// incalpha
                retrn[3] = Integer.parseInt(line.substring(6, 8), 16);
            }
        }

        return retrn;
    }
}
