package sk.perri.alastor;

import java.util.Vector;

public class Console
{
    private Vector<String> lastCmds = new Vector<>();
    private int cmdPointer = -1;
    private Interpreter interpreter;

    public Console(Interpreter interpreter)
    {
        this.interpreter = interpreter;
    }

    public void prikaz(String prikaz)
    {
        addCmd(prikaz);
        String v = interpreter.prikaz(prikaz);
        if(!v.equalsIgnoreCase(""))
        {
            nepoznamPrikaz(v);
        }
    }

    public void nepoznamPrikaz(String v)
    {
        String prik = lastCmd(0);
        lastCmds.set(lastCmds.size()-1, v+" Chyba: "+prik);
    }

    public void addCmd(String prikaz)
    {
        if(!prikaz.equals(""))
        {
            lastCmds.add(prikaz);
            cmdPointer = lastCmds.size();
        }
    }

    public String cmdUP()
    {
        if(cmdPointer > 0)
        {
            cmdPointer--;
        }

        String vys = lastCmds.get(cmdPointer);

        if(vys.contains("Chyba: "))
        {
            vys = vys.split("Chyba: ")[1];
        }

        return vys;
    }

    public String cmdDown()
    {
        if(cmdPointer < lastCmds.size() - 1)
        {
            cmdPointer++;
            return lastCmds.get(cmdPointer);
        }
        else
            return "";
    }

    public String lastCmd()
    {
        return lastCmd(0);
    }

    public String lastCmd(int back)
    {
        String vys = "";
        if(lastCmds.size()-1-back >= 0)
            vys = lastCmds.get(lastCmds.size()-1-back);
        return vys;
    }
}
