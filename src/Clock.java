import GLOOP.*;
public class Clock {
    int delta1Hour = 0, delta1Minute = 0, delta1Second = 0;
    boolean firsttime = true;
    GLZylinder display, hourPointer, minutePointer, secondPointer, centre;

    public void build(double x, double y, double z){
        display = new GLZylinder(x, y, z, 10, 2);
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
    }

    public void run(int delta2Hour, int delta2Minute, int delta2Second){

        if(firsttime){
            secondPointer.rotiere(-360/60*delta2Second, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            minutePointer.rotiere(-360/60*delta2Minute, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            hourPointer.rotiere(-360/12*delta2Hour, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
            firsttime = false;
        }

        if(delta2Second - delta1Second == 1 || delta2Second - delta1Second == -59){
            secondPointer.rotiere(-360/60, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }
        if(delta2Minute - delta1Minute == 1){
            minutePointer.rotiere(-360/60, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }
        if(delta2Hour - delta1Hour == 1){
            minutePointer.rotiere(-360/12, 0, 0, 1, display.gibX(), display.gibY(), display.gibZ());
        }


        System.out.println(delta2Second + " -- " + delta1Second);
        delta1Hour = delta2Hour;
        delta1Minute = delta2Minute;
        delta1Second = delta2Second;

    }
}
