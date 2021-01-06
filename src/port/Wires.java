package port;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

class PairPort {
    Port sursa, dest;

    PairPort(Port a, Port b) {
        this.sursa = a;
        this.dest = b;
    }

    Port getSursa() {
        return sursa;
    }

    Port getDest() {
        return dest;
    }
}

public class Wires {
    ArrayList<PairPort> wires;
    PApplet sketch;

    public Wires(PApplet sketch) {
        this.sketch = sketch;
        wires = new ArrayList<>();
    }

    public void show() {
        PVector sloc;
        PVector dloc;
        float panta = 0f;
        for(var wire: wires) {
            sloc = wire.sursa.getPortLocation();
            dloc = wire.dest.getPortLocation();

            if(sloc.x - dloc.x != 0)
                panta = (dloc.y - sloc.y) * 1.0f / (dloc.x - sloc.x);

            float p1 = sketch.map(panta, -10, 10, 20, -20);
            float p2 = sketch.map(panta, -10, 10, -20, 20);
            //sketch.strokeWeight(3);
            if(wire.sursa.getValue() == true) {
                wire.dest.setValue(true);
                sketch.stroke(wire.sursa.getColor().getRGB());
            } else {
                wire.dest.setValue(false);
                sketch.stroke(wire.sursa.getColor().getRGB());
            }
            sketch.bezier(sloc.x, sloc.y, (sloc.x+dloc.x)/2.f, sloc.y + p1,
                    (sloc.x + dloc.x) / 2.f, dloc.y + p2, dloc.x, dloc.y);
            //sketch.line(sloc.x, sloc.y, dloc.y, dloc.z);
        }
    }

    public void addSursa(Port sursa, Port dest) {
        wires.add(new PairPort(sursa, dest));
    }

    public void deleteWires() {
        wires = new ArrayList<>();
    }
}
