import GLOOP.*;
import java.time.ZoneId;
import java.time.LocalTime;

public class Clock{
    GLZylinder display, centre;
    GLTafel zoneDisplay;
    Pointer secondPointer, minutePointer, hourPointer;
    ZoneId zone;

    public void build(double px, double py, double pz, String timeZone){
        zone = ZoneId.of(timeZone);

        display = new GLZylinder(px, py, pz, 10, 2, "src/clock.jpg");

        centre = new GLZylinder(px, py, pz, 1, 4);
        centre.setzeFarbe(0, 0, 0);

        zoneDisplay = new GLTafel(px, py - 15, pz, 5, 5);
        zoneDisplay.setzeTextfarbe(1, 1, 1);
        zoneDisplay.setzeFarbe(0, 0, 0);
        zoneDisplay.setzeText(timeZone, 5);

        secondPointer = new Pointer();
        secondPointer.build(px, py, pz, 0.25, 9, 0, 0, 1, "seconds", getSecond(zone), getMinute(zone), getHour(zone));

        minutePointer = new Pointer();
        minutePointer.build(px, py, pz, 0.5, 8, 0, 1, 0, "minutes", getSecond(zone), getMinute(zone), getHour(zone));

        hourPointer = new Pointer();
        hourPointer.build(px, py, pz, 0.75, 6, 1, 0, 0, "hours", getSecond(zone), getMinute(zone), getHour(zone));


    }

    public void run(){
        secondPointer.run(getSecond(zone), getMinute(zone), getHour(zone));
        minutePointer.run(getSecond(zone), getMinute(zone), getHour(zone));
        hourPointer.run(getSecond(zone), getMinute(zone), getHour(zone));
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