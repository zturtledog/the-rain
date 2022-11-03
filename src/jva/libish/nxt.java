package jva.libish;

import java.util.HashMap;

public class nxt {
    HashMap<String, Object> lsta = new HashMap<String, Object>();

    public boolean getbool(String name) {
        if (lsta.containsKey(name))
            return (boolean) (lsta.get(name));
        else
            return false;
    }

    public void setbool(String name, boolean value) {
        lsta.put(name, value);
    }

    public Object get(String name) {
        if (lsta.containsKey(name))
            return (lsta.get(name));
        else
            return null;
    }

    public void set(String name, Object value) {
        lsta.put(name, value);
    }
}
