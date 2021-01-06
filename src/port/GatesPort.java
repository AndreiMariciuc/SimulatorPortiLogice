package port;

import processing.core.PApplet;
import processing.core.PVector;


public class GatesPort extends Port {
    final float RADIX = 20;

    public GatesPort(PVector portLocation) {
        super(portLocation);
    }

    @Override
    public void show(PApplet sketch) {
        if(super.getValue() == false)
            sketch.fill(color.getRGB());
        else
            sketch.fill(color.getRGB());
        sketch.noStroke();
        sketch.circle(portLocation.x, portLocation.y, RADIX);
    }

    @Override
    public boolean contains(int x, int y) {
        return (x - portLocation.x) * (x - portLocation.x) + (y - portLocation.y) * (y - portLocation.y) <= RADIX * RADIX;
    }
}
