package sk.perri.alastor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class FileReader
{
    private Interpreter interpreter;
    private File currentFile = null;
    private BufferedReader reader = null;

    public FileReader(Interpreter interpreter)
    {
        this.interpreter = interpreter;
    }

    public String readFile(String fileName)
    {
        Vector<String> prikazy = new Vector<>();

        try
        {
            currentFile = new File(fileName+".txt");
            reader = new BufferedReader(new java.io.FileReader(currentFile));
            String text = null;
            while((text = reader.readLine()) != null)
            {
                prikazy.add(text);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
        finally
        {
            if(reader != null)
            {
                try
                {
                    reader.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        int riadok = 1;
        byte pocetVnoreni = 0;
        Vector<int[]> opakuj = new Vector<>();

        while(riadok <= prikazy.size())
        {
            String prikaz = prikazy.get(riadok-1);
            String[] prik = prikaz.split(" ");

            if(prik.length == 2 && prik[0].equalsIgnoreCase("opakuj"))
            {
                if(Interpreter.validateInt(prik))
                {
                    opakuj.add(new int[]{Integer.parseInt(prik[1])-1, riadok+1});
                    System.out.print("Vidim opakuj, zapisujem: ");
                    System.out.print(Integer.parseInt(prik[1])-1);
                    System.out.print(" ");
                    System.out.println(riadok+1);
                    riadok++;
                    continue;
                }
            }

            if(prik.length == 1 && prik[0].equalsIgnoreCase("koniec"))
            {
                if(opakuj.size() > 0)
                {
                    if(opakuj.lastElement()[0] < 1)
                    {
                        opakuj.remove(opakuj.lastElement());
                        riadok++;
                        System.out.println("Koniec opakovania");
                        continue;
                    }
                    else
                    {
                        riadok = opakuj.lastElement()[1];
                        opakuj.lastElement()[0] -= 1;
                        System.out.print("Opakujem: ");
                        System.out.println(opakuj.lastElement()[0]);
                        continue;
                    }
                }
            }

            String rozsudok = interpreter.prikaz(prikaz);
            if(!rozsudok.equalsIgnoreCase(""))
            {
                return rozsudok+" v subore: \""+fileName+"\" na riadku cislo: "+Long.toString(riadok);
            }

            riadok++;
        }

        return "";
    }
}
