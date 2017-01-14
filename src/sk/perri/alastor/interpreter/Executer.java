package sk.perri.alastor.interpreter;

import sk.perri.alastor.Plocha;

public class Executer
{
    private Plocha plocha;

    public Executer()
    {
        plocha = new Plocha();
    }

    public Plocha getPlocha()
    {
        return plocha;
    }

    public void krok(int kolko)
    {
        String[] s = {Integer.toString(kolko)};
        plocha.krok(s);
    }

    public void otoc(int kolko)
    {
        String[] s = {Integer.toString(kolko)};
        plocha.getKorka().vlavo(s);
    }

}
