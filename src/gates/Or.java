package gates;

import gates.Gate;
import port.GatesPort;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Or extends Gate {
    public Or(PApplet sketch, PVector gateLocation) {
        super(sketch, gateLocation, "OR");
        input1 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + HEIGHT / 4));
        input2 = new GatesPort(new PVector(gateLocation.x, gateLocation.y + 3 * HEIGHT / 4));
        output = new GatesPort(new PVector(gateLocation.x + WIDTH, gateLocation.y + HEIGHT / 2));
        fillColor = new Color(227, 159, 108);
    }

    public void update() {
        output.setValue(input1.getValue() || input2.getValue());
    }
}
