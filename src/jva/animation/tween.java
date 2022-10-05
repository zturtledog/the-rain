package jva.animation;

public class tween {
    float max;
    float min;
    protected float pval = 0;
     
    protected int dir = 1;

    //floaaar
    public tween(float maxval, float minval) {
        min = minval;
        max = maxval;
    }

    public float step() {
        

        if (pval>max) {pval=max;dir = -1;}
        if (pval<min) {pval=min;dir = 1;}

        pval += equation();

        return pval;
    }

    protected float peek() {
        return pval;
    }

    public float equation() {
        return dir;
    }
}
