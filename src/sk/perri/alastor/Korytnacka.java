package sk.perri.alastor;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Korytnacka
{
    public static int KROK = 50;
    public static float SCALE = 0.2f;

    private int x = 0;
    private int y = 0;
    private double angle = 0;
    private Image texture;

    public Korytnacka(int x, int y)
    {
        this.x = x;
        this.y = y;
        try
        {
            texture = new Image("/img/korka.png");
        } catch (SlickException e)
        {
            System.err.println("Neviem nacitat texturu korytnacky");
            e.printStackTrace();
        }
        updateTexture();
    }

    private void updateTexture()
    {
        texture.setRotation((float) angle);
        texture.getScaledCopy(SCALE).rotate((float) angle);
        angle %= 360;
    }

    public void krok(int pocet)
    {
        x += (int) (Math.cos(Math.toRadians(angle-90))*pocet);
        y += (int) (Math.sin(Math.toRadians(angle-90))*pocet);
        updateTexture();
    }

    public void krok()
    {
        krok(1);
    }

    public void vpravo(String[] casti)
    {
        int pocet = 90;
        if(Interpreter.validateInt(casti))
            pocet = Integer.parseInt(casti[1]);
        angle += pocet;
        updateTexture();
    }

    public void vlavo(String[] casti)
    {
        int pocet = 90;
        if(Interpreter.validateInt(casti))
            pocet = Integer.parseInt(casti[1]);
        angle -= pocet;
        updateTexture();
    }

    public Image getTexture()
    {
        return texture;
    }

    public int getX()
    {
        return x;
    }

    public int getCenterX()
    {
        return (int) (x + texture.getWidth()*SCALE / 2);
    }

    public int getCenterY()
    {
        return (int) (y + texture.getHeight()*SCALE / 2);
    }

    public int getY()
    {
        return y;
    }

    public double getAngle()
    {
        return angle;
    }
}
