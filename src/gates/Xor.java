package gates;

import port.GatesPort;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Xor extends Gate {
    public Xor(PApplet sketch, PVector gateLocation) {
        super(sketch, gateLocation, "XOR");
        input1 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + HEIGHT / 4));
        input2 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + 3 * HEIGHT / 4));
        output = new GatesPort(new PVector(gateLocation.x + WIDTH, gateLocation.y + HEIGHT / 2));
        fillColor = new Color(125, 220, 190);
    }

    public void update() {
        if(input1.getValue() == input2.getValue())
            output.setValue(false);
        else
            output.setValue(true);
    }
}
