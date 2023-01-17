import GLOOP.*;

import java.time.ZoneId;

public class Clock {
    int delta1Hour = 0, delta1Minute = 0, delta1Second = 0;
    boolean firsttime = true;
    ZoneId zone;
    GLZylinder display, hourPointer, minutePointer, secondPointer, centre;
    GLTafel zoneDisplay;

    public void build(double x, double y, double z, String timeZone){
            display = new GLZylinder(x, y, z, 10, 2, "src/clock.jpg");
            display.setzeFarbe(1, 1, 1);


        centre = new GLZylinder(x, y, z +1, 0.5, 2);
        centre.setzeFarbe(0, 0, 0);

        hourPointer = new GLZylinder(0, 0, 0, 0.5, 5);
        hourPointer.setzeFarbe(0, 1, 0);
        hourPointer.drehe(-90, 0, 0);
        hourPointer.setzePosition(x, y + 2.5, z + 1);

        minutePointer = new GLZylinder(0, 0, 0, 0.5, 7.5);
        minutePointer.setzeFarbe(0, 0, 1);
        minutePointer.drehe(-90, 0, 0);
        minutePointer.setzePosition(x, y + 3.75, z + 1);

        secondPointer = new GLZylinder(0, 0, 0, 0.25, 9);
        secondPointer.setzeFarbe(1, 0, 1);
        secondPointer.drehe(-90, 0, 0);
        secondPointer.setzePosition(x, y + 4.5, z + 1);

        sign(x, y - 20, z, timeZone);


    }

    public void run(String timeZone){

        zone = ZoneId.of(timeZone);

        if(firsttime){
            secondPointer.rotiere(-360/60*getSecond(zone), 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            minutePointer.rotiere(-360/60*getMinute(zone), 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            hourPointer.rotiere(-360/12*getHour(zone), 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            firsttime = false;
        }

        if(getSecond(zone) - delta1Second == 1 || getSecond(zone) - delta1Second == -59){
            secondPointer.rotiere(-360/60, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }
        if(getMinute(zone) - delta1Minute == 1 || getMinute(zone) - delta1Minute == -59){
            minutePointer.rotiere(-360/60, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }
        if(getHour(zone) - delta1Hour == 1 || getHour(zone) - delta1Hour == -23){
            minutePointer.rotiere(-360/12, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }

        //System.out.println(getSecond(zone) + " -- " + delta1Second + "--" + zone);
        delta1Hour = getHour(zone);
        delta1Minute = getMinute(zone);
        delta1Second = getSecond(zone);
        System.out.println(java.time.LocalTime.now(zone));

    }

    public void sign(double px, double py, double pz, String zone){
        zoneDisplay = new GLTafel(px, py, pz, 20, 5);
        zoneDisplay.setzeText(zone, 16);
    }



    private int getHour(ZoneId zone) {
        return java.time.LocalTime.now(zone).getHour();
    }
    private int getMinute(ZoneId zone) {
        return java.time.LocalTime.now(zone).getMinute();
    }
    private int getSecond(ZoneId zone) {
        return java.time.LocalTime.now(zone).getSecond();
    }
}
