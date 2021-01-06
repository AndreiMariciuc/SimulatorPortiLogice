package gates;

import gates.Gate;
import port.GatesPort;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class NOr extends Gate {
    public NOr(PApplet sketch, PVector gateLocation) {
        super(sketch, gateLocation, "NOR");
        input1 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + HEIGHT / 4));
        input2 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + 3 * HEIGHT / 4));
        output = new GatesPort(new PVector(gateLocation.x + WIDTH, gateLocation.y + HEIGHT / 2));
        fillColor = new Color(234, 131, 131);
    }

    public void update() {
        output.setValue(!(input1.getValue() || input2.getValue()));
    }
}
