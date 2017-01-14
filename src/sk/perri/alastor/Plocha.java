package sk.perri.alastor;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;

import java.util.Vector;

public class Plocha
{
    private Korytnacka korka;
    private Vector<Shape> shapes = new Vector<>();
    private boolean peroKresli = true;

    public Plocha()
    {
        korka = new Korytnacka(AlastorMain.WIDTH/2, AlastorMain.HEIGHT/2);
    }

    public void krok(String[] casti)
    {
        int pocet = 1;
        if(Interpreter.validateInt(casti))
            pocet = Integer.parseInt(casti[1]);

        int xp = korka.getCenterX();
        int yp = korka.getCenterY();

        korka.krok(pocet);

        if(peroKresli)
            shapes.add(new Line(xp, yp, korka.getCenterX(), korka.getCenterY()));
    }

    public void farbaPera(String[] casti)
    {

    }

    public void peroHore()
    {
        peroKresli = false;
    }

    public void peroDole()
    {
        peroKresli = true;
    }

    public Korytnacka getKorka()
    {
        return korka;
    }

    public Vector<Shape> getShapes()
    {
        return shapes;
    }
}
