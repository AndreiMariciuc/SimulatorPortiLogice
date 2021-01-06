import gates.*;
import gui.ModelMenu;
import gui.MenuBar;
import port.BreadBoard;
import port.Port;
import port.Wires;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;

public class Simulator extends PApplet {
    // constante legate de ecran
    final int WIDTH = 1280;
    final int HEIGHT = 720;
    // componentele afisate
    ArrayList<Gate> gates;
    Wires wires;
    BreadBoard breadBoard;
    MenuBar menuBar;
    // model sub forma unei enumeratii pentru GUI
    ModelMenu menu;
    // portul care este selectat spre a fi legat
    private Port toBeWired;

    // startul aplicatiei
    public static void main(String[] args) {
        String[] processingArgs = {"Simulator"};
        Simulator simulator = new Simulator();
        PApplet.runSketch(processingArgs, simulator);
    }
    // pregatirea scenei!
    @Override
    public void settings() {
        size(WIDTH, HEIGHT, P2D);
    }

    // initializarea atributelor
    @Override
    public void setup() {
        menu = ModelMenu.NULL;
        breadBoard = new BreadBoard(this);
        menuBar = new MenuBar(new Rectangle(0, breadBoard.getBounds().y + breadBoard.getBounds().height,
                width / 10, height - breadBoard.getBounds().y - breadBoard.getBounds().height), this);
        gates = new ArrayList<>();
        wires = new Wires(this);
    }

    // functie care se ocupa de afisarea componenetelor in scena
    @Override
    public void draw() {
        background(new Color(120, 117, 117).getRGB());
        // afisarea meniului
        menuBar.show();
        // afisarea breadBord-ului impreuna cu porturile de intrare si iesire aferente
        breadBoard.show();
        // afisarea tuturor portilor existente
        for (var gate : gates)
            gate.show();
        // necesar pt wired!
        noFill();
        stroke(0);
        brezierToBeWired();
        // afisarea legaturilor
        wires.show();
    }
    // deseneaza o curba bezier de la susa selectata la pozitia mouse-ului actuala, daca nu s a selectat inca o destinatie
    public void brezierToBeWired() {
        if (toBeWired != null) {
            if (menu == ModelMenu.WIRE) {
                PVector sloc;
                PVector dloc;
                float panta = 0f;
                sloc = toBeWired.getPortLocation();
                dloc = new PVector(mouseX, mouseY);
                if (sloc.x - dloc.x != 0)
                    panta = (dloc.y - sloc.y) * 1.0f / (dloc.x - sloc.x);
                float p1 = map(panta, -10, 10, 20, -20);
                float p2 = map(panta, -10, 10, -20, 20);
                bezier(sloc.x, sloc.y, (sloc.x + dloc.x) / 2.f, sloc.y + p1,
                        (sloc.x + dloc.x) / 2.f, dloc.y + p2, dloc.x, dloc.y);
            } else toBeWired = null;
        }
    }

    // asigura proprietatea de draggable a portilor
    @Override
    public void mouseDragged() {
        if (menu == ModelMenu.NULL) {
            for (var gate : gates)
                if (gate.isDragged() && breadBoard.getBounds().contains(new Point(mouseX, mouseY)))
                    gate.setGateLocation(mouseX - gate.getWIDTH() / 2, mouseY - gate.getHEIGHT() / 2);
        }
    }

    // legatura dintre meniul gui si model!
    @Override
    public void mouseClicked() {
        if (mouseY > breadBoard.getBounds().y + breadBoard.getBounds().height) {
            String result = menuBar.selectButton(mouseX, mouseY);
            if (result != null) {
                switch (result) {
                    case "WIRE":
                        menu = ModelMenu.WIRE;
                        break;
                    case "I_O":
                        menu = ModelMenu.I_O;
                        break;
                    case "NOT":
                        menu = ModelMenu.NOT;
                        break;
                    case "AND":
                        menu = ModelMenu.AND;
                        break;
                    case "NAND":
                        menu = ModelMenu.NAND;
                        break;
                    case "OR":
                        menu = ModelMenu.OR;
                        break;
                    case "NOR":
                        menu = ModelMenu.NOR;
                        break;
                    case "XOR":
                        menu = ModelMenu.XOR;
                        break;
                    case "NXOR":
                        menu = ModelMenu.NXOR;
                        break;
                    case "DELETE":
                        gates = new ArrayList<>();
                        wires.deleteWires();
                        breadBoard.deletePins();
                        break;
                    default:
                        menu = ModelMenu.NULL;
                }
            } else menu = ModelMenu.NULL;
        } else {
            if (menu == ModelMenu.WIRE) {

            } else if (menu == ModelMenu.I_O) {
                breadBoard.addIfContains(mouseX, mouseY);
            } else if (menu == ModelMenu.NOT) {
                gates.add(new Not(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.AND) {
                gates.add(new And(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.NAND) {
                gates.add(new NAnd(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.OR) {
                gates.add(new Or(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.NOR) {
                gates.add(new NOr(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.XOR) {
                gates.add(new Xor(this, new PVector(mouseX, mouseY)));
            } else if (menu == ModelMenu.NXOR) {
                gates.add(new NXor(this, new PVector(mouseX, mouseY)));
            }
        }
    }

    // fac legaturile! si marchez starea de drag pentru porti
    @Override
    public void mousePressed() {
        if (menu == ModelMenu.NULL) {
            for (var gate : gates)
                if (gate.contains(mouseX, mouseY)) {
                    gate.setDragged(true);
                    break;
                }
            breadBoard.switchIfContains(mouseX, mouseY);
        } else if (menu == ModelMenu.WIRE) {
            if (toBeWired == null) {
                // e un switch?
                toBeWired = breadBoard.containsInput(mouseX, mouseY);
                // e un output al unei porti?
                if (toBeWired == null) {
                    for (var gate : gates) {
                        toBeWired = gate.containsOutputPort(mouseX, mouseY);
                        if (toBeWired != null)
                            break;
                    }
                }
            } else {
                // output de pe bread?
                Port dest = null;
                dest = breadBoard.containsOutput(mouseX, mouseY);
                if (dest != null) {
                    //am legat
                    wires.addSursa(toBeWired, dest);
                    toBeWired = null;
                } else {
                    for (var gate : gates) {
                        dest = gate.containsInputPort(mouseX, mouseY);
                        if (dest != null)
                            break;
                    }
                    if (dest != null) {
                        wires.addSursa(toBeWired, dest);
                        toBeWired = null;
                    }
                }
            }
        }
    }
    // demarchez starea de drag pentru porti!
    @Override
    public void mouseReleased() {
        if (menu == ModelMenu.NULL) {
            for (var gate : gates)
                if (gate.isDragged())
                    gate.setDragged(false);
        }
    }
}


