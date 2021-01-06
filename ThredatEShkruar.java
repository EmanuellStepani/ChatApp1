import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ThredatEShkruar extends Thread {
    private PrintWriter shkruesi;
    private Socket socket;
    private KlientBiseda klienti;

    public ThredatEShkruar(Socket socket, KlientBiseda klienti) {
        this.socket = socket;
        this.klienti = klienti;

        try {
            OutputStream output = socket.getOutputStream();
            shkruesi = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Gabim në marrjen e transmetimit të daljes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Console console = System.console();

        String perdoruesi = console.readLine("\nShkruaj emrin: ");
        klienti.setEmriPerdorusit(perdoruesi);
        shkruesi.println(perdoruesi);

        String text;

        do {
            text = console.readLine("[" + perdoruesi + "]: ");
            shkruesi.println(text);

        } while (!text.equals("Mirupafshim"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error gjat shkrimit ne server: " + ex.getMessage());
        }
    }
}