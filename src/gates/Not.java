package gates;

import port.GatesPort;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Not extends Gate {
    public Not(PApplet sketch, PVector gateLocation) {
        super(sketch, gateLocation, "NOT");
        input1 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + HEIGHT / 2));
        output = new GatesPort(new PVector(gateLocation.x + WIDTH, gateLocation.y + HEIGHT / 2));
        input2 = null;
        fillColor = new Color(203, 19, 232);
    }

    public void update() {
        output.setValue(!input1.getValue());
    }
}
