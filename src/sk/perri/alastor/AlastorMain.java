package sk.perri.alastor;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.TextField;

import java.awt.Font;


public class AlastorMain extends BasicGame
{
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private Rectangle rect = new Rectangle(100, 100, 50, 50);
    private TextField tf;
    private String text = "";
    private Renderer rend;
    private Interpreter inter;
    private Console console;

    @Override
    public void init(GameContainer container) throws SlickException
    {
        UnicodeFont font = new UnicodeFont(new Font("Arial", Font.PLAIN, 16));
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect(java.awt.Color.white));
        font.loadGlyphs();
        tf = new TextField(container, font, -1, HEIGHT - 22, WIDTH+1, 22);
        inter = new Interpreter();
        rend = new Renderer(inter.getPlocha());
        console = new Console(inter);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        if(container.getInput().isKeyPressed(Input.KEY_ENTER) && !tf.getText().equals(""))
        {
            System.out.println("New text: "+tf.getText());
            console.prikaz(tf.getText());
            tf.setText("");
        }

        if(container.getInput().isKeyPressed(Input.KEY_UP))
            tf.setText(console.cmdUP());

        if(container.getInput().isKeyPressed(Input.KEY_DOWN))
            tf.setText(console.cmdDown());
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        g.setColor(Color.white);
        g.setBackground(Color.white);
        tf.render(container, g);
        g.setColor(Color.black);
        for(int i = 0; i < 3; i++)
            g.drawString(console.lastCmd(i), 5, HEIGHT - (i+2)*22);
        rend.render(g);
    }

    public AlastorMain(String title)
    {
        super(title);
    }

    public static void main(String[] args) throws SlickException
    {

        AppGameContainer app = new AppGameContainer(new AlastorMain("Alastor"));
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.start();
    }
}
