import java.net.*;
import java.io.*;
import java.util.Set;

public class KlientBiseda {
    private String emriHostit;
    private int porti;
    private String emriPerdorusit;

    public KlientBiseda(String emriHostit, int porti) {
        this.emriHostit = emriHostit;
        this.porti = porti;
    }

    public void ekzekutimi() {
        try {
            Socket socket = new Socket(emriHostit, porti);

            System.out.println("I lidhur ne serverin e bisedes");

            new LexoThredat(socket, this).start();
            new ThredatEShkruar(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Serveri nuk eshte gjetur: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("input/output Error: " + ex.getMessage());
        }

    }

    void setEmriPerdorusit(String emriPerdorusit) {
        this.emriPerdorusit = emriPerdorusit;
    }

    String getEmriPerdorusit() {
        return this.emriPerdorusit;
    }
}

