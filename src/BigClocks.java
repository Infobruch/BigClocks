
import GLOOP.*;
public class BigClocks {

    Clock clock1;
    GLLicht light;
    GLKamera cam;
    GLTastatur kb;
    public void run() {

        light = new GLLicht();
        cam = new GLKamera(500, 500);
        cam.setzePosition(0, 0, 50);
        kb = new GLTastatur();


        clock1 = new Clock();
        clock1.build(0, 0, 0);
        while(!kb.esc()){
            clock1.run(getHour(), getMinute(), getSecond());
            Sys.warte();
        }

    }

    private int getHour() {
        return java.time.LocalTime.now().getHour();
    }
    private int getMinute() {
        return java.time.LocalTime.now().getMinute();
    }
    private int getSecond() {
        return java.time.LocalTime.now().getSecond();
    }


}
