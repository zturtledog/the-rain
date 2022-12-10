package main;
public class Iworld {
    public boolean debug_draw_lines = !!false;
    public boolean debug_animate_bounce = true;
    
    public int time = 0;

    public boolean scheduled_tick_is_now;
    public long total_number_of_ticks = 0;
    public long time_since_last_tick = 0;
    public int time_to_next_tick = 10;

    public void update() {
        time++;

        long time = System.nanoTime();
        if ((time-time_since_last_tick)*0.000000001 >= time_to_next_tick) {
            System.out.println("haha tick: "+total_number_of_ticks+" in: "+(time-time_since_last_tick)+"ns");
            time_since_last_tick=time;
            total_number_of_ticks++;
            scheduled_tick_is_now = true;
        }
    }
}
