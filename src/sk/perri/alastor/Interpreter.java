package sk.perri.alastor;

import org.lwjgl.Sys;

import java.util.Vector;

public class Interpreter
{
    private Vector<String> lastCmds = new Vector<>();
    private int cmdPointer = -1;
    private Plocha plocha;
    private FileReader reader;

    public Interpreter()
    {
        plocha = new Plocha();
        reader = new FileReader(this);
    }

    public Plocha getPlocha()
    {
        return plocha;
    }

    public String prikaz(String prikaz)
    {
        String casti[] = prikaz.split(" ");
        if(casti.length < 1)
            return getErr(0);

        if(casti.length == 1)
        {
            switch (casti[0].toLowerCase())
            {
                case "ph": plocha.peroHore(); break;
                case "pd": plocha.peroDole(); break;
                case "vlavo": plocha.getKorka().vlavo(casti); break;
                case "vpravo": plocha.getKorka().vpravo(casti); break;

                default: return getErr(0, casti[0]);
            }
            return "";
        }

        if(casti.length == 2)
        {
            if(casti[0].equalsIgnoreCase("otvor"))
                return reader.readFile(casti[1]);

            switch (casti[0].toLowerCase())
            {
                case "krok": plocha.krok(casti); break;
                case "vlavo": plocha.getKorka().vlavo(casti); break;
                case "vpravo": plocha.getKorka().vpravo(casti); break;
                case "nechfp": plocha.farbaPera(casti); break;

                default: return getErr(0, casti[0]);
            }
            return "";
        }

        return getErr(0);
    }

    public static boolean validateInt(String casti[])
    {
        boolean vys = true;
        if(casti.length != 2)
            return false;

        try
        {
            int cislo = Integer.parseInt(casti[1]);
        }
        catch (Exception e)
        {
            vys = false;
        }

        return vys;
    }

    private String getErr(int code)
    {
        return getErr(code, "");
    }

    private String getErr(int code, String arg)
    {
        String err = "";
        switch (code)
        {
            case 0: err = "Nepoznam prikaz"+(arg.length() > 0 ? " "+arg : "!"); break;
            case 1: err = "Zly argument!"; break;
            case 2: err = "Subor \""+arg+"\" som nenasiel!"; break;
            case 3: err = "Nie je prikaz!"; break;

            default: err = "Neznama chyba!";
        }
        return err;
    }
}
