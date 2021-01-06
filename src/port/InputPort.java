package port;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class InputPort extends Port {
    final int MARGIN_BRODER = 40;
    final int RADIX1 = 20;
    final int RADIX2 = 40;

    public InputPort(PVector portLocation) {
        super(portLocation);
    }

    @Override
    public void show(PApplet sketch) {
        if(!super.getValue())
            sketch.fill(color.getRGB());
        else
            sketch.fill(color.getRGB());
        sketch.stroke(new Color(0, 0, 0).getRGB());
        sketch.line(portLocation.x, portLocation.y, portLocation.x + MARGIN_BRODER, portLocation.y);
        sketch.noStroke();
        sketch.circle(portLocation.x, portLocation.y, RADIX2);
        sketch.noStroke();
        sketch.fill(0);
        sketch.circle(portLocation.x + MARGIN_BRODER, portLocation.y, RADIX1);
    }

    @Override
    public boolean contains(int x, int y) {
        return (x - portLocation.x) * (x - portLocation.x)
                + (y - portLocation.y) * (y - portLocation.y) <= RADIX2 * RADIX2;
    }
}
