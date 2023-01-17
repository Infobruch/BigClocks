
import GLOOP.*;

import java.time.ZoneId;
import java.time.temporal.TemporalField;


public class BigClocks {

    Clock clock1, clock2;
    GLLicht light;
    GLKamera cam;
    GLTastatur kb;
    public void run() {

        light = new GLLicht();
        cam = new GLKamera(1000, 1000);
        cam.setzePosition(0, 0, 50);
        kb = new GLTastatur();


        clock1 = new Clock();
        clock1.build(0, 0, 0, "Europe/Moscow");

        clock2 = new Clock();
        clock2.build(0, 20, 0, "Europe/London");

        while(!kb.esc()){
            clock1.run("Europe/Moscow");
            clock2.run("Europe/London");
            //System.out.println(ZoneId.getAvailableZoneIds());
            Sys.warte();
        }

    }
}
