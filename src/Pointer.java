import GLOOP.*;

import java.util.Vector;

public class Pointer {
    GLZylinder pointerBody;
    private String pointerType;
    private double delta1Time, deltaTime;
    private GLVektor vPointerPos;

    public void build(double px, double py, double pz, double thickness, double length, double r, double g, double b, String setPointerType, double setSeconds, double setMinutes, double setHours){


        pointerBody = new GLZylinder(px, py, pz, thickness, length);
        pointerBody.drehe(-90, 0, 0);
        pointerBody.setzePosition(px, py + length/2, pz + 1);
        pointerBody.setzeFarbe(r, g, b);
        pointerBody.skaliere(1);
        System.out.println("HI");

        vPointerPos = new GLVektor(px, py, pz);

        switch (setPointerType){
            case "seconds":
                pointerType = setPointerType;
                pointerBody.drehe(0, 0, -360/60*setSeconds, px, py, pz);
                delta1Time = setSeconds;
            case "minutes":
                pointerType = setPointerType;
                pointerBody.drehe(0, 0, -360/60*(setMinutes + 1/(60/setSeconds)), px, py, pz);
                delta1Time = setMinutes + 1/(60/setSeconds);
            case "hours":
                pointerType = setPointerType;
                pointerBody.drehe(0, 0, -360/12*(setHours + 1/(60/setMinutes)), px, py, pz);
                delta1Time = setHours + 1/(60/setMinutes);
        }
    }

    public void run(double seconds, double minutes, double hours){
        switch (pointerType){
            case "seconds":
                deltaTime = seconds - delta1Time;
                if(deltaTime == 1 || deltaTime == -59){
                    pointerBody.drehe(0, 0, -360/60*deltaTime, vPointerPos);
                    delta1Time = seconds;
                    System.out.println(deltaTime + " --- " + delta1Time);
                }
                System.out.println(deltaTime + " --- " + delta1Time);
            case "minutes":
                deltaTime = (minutes + 1/60/seconds) - delta1Time;
                pointerBody.drehe(0, 0, -360/60*deltaTime, vPointerPos);
                delta1Time = (minutes + 1/60/seconds);
            case "hours":
                deltaTime = (hours + 1/60/minutes) - delta1Time;
                pointerBody.drehe(0, 0, -360/12*deltaTime, vPointerPos);
                delta1Time = (hours + 1/60/minutes);

        }
    }
}
