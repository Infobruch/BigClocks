import GLOOP.*;

import javax.media.opengl.GL;

public class ClockScene {
    GLKamera cam;
    GLLicht light;
    GLTastatur kb;
    Clock clock1;

    public void run(){
        light = new GLLicht();
        light.setzePosition(0, 1000, 1000);

        kb = new GLTastatur();

        cam = new GLEntwicklerkamera(1000, 1000);
        cam.setzePosition(0, 0, 100);

        clock1 = new Clock();
        clock1.build(0, 0, 0 , "Europe/Brussels");

        while(!kb.esc()){
            clock1.run();
        }

    }
}
