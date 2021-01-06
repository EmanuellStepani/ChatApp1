import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class LexoThredat extends Thread {
    private BufferedReader lexuesi;
    private Socket socket;
    private KlientBiseda klienti;

    public LexoThredat(Socket socket, KlientBiseda klienti) {
        this.socket = socket;
        this.klienti = klienti;

        try {
            InputStream input = socket.getInputStream();
            lexuesi = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Gabim në marrjen e transmetimit të hyrjes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String response = lexuesi.readLine();
                System.out.println("\n" + response);

                // prints the username after displaying the server's message
                if (klienti.getEmriPerdorusit() != null) {
                    System.out.print("[" + klienti.getEmriPerdorusit() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error ne leximin ne server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}