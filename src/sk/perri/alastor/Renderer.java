package sk.perri.alastor;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.Vector;

public class Renderer
{
    private Plocha plocha;

    public Renderer(Plocha plocha)
    {
        this.plocha = plocha;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.black);
        Vector<Shape> sh = plocha.getShapes();

        for(Shape s : sh)
        {
            g.fill(s);
            g.draw(s);
        }

        Image kko = plocha.getKorka().getTexture().getScaledCopy(Korytnacka.SCALE);
        kko.rotate((float) plocha.getKorka().getAngle());
        kko.draw(plocha.getKorka().getX(), plocha.getKorka().getY());
    }
}
