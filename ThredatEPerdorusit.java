import java.io.*;
import java.net.*;

public class ThredatEPerdorusit extends Thread {
    private Socket socket;
    private BisedaServer serveri;
    private PrintWriter shkruesi;

    public ThredatEPerdorusit(Socket socket, BisedaServer serveri) {
        this.socket = socket;
        this.serveri = serveri;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            shkruesi = new PrintWriter(output, true);

            printoPerdorusin();

            String userName = reader.readLine();
            serveri.shtoPerdorusit(userName);

            String serverMessage = "New user connected: " + userName;
            serveri.transmetimi(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                serveri.transmetimi(serverMessage, this);

            } while (!clientMessage.equals("bye"));

            serveri.largoPerdorusin(userName, this);
            socket.close();

            serverMessage = userName + " has quitted.";
            serveri.transmetimi(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    void printoPerdorusin() {
        if (serveri.kaPerdorus()) {
            shkruesi.println("Connected users: " + serveri.getEmriPerdoruesve());
        } else {
            shkruesi.println("No other users connected");
        }
    }

    void dergoMesazh(String message) {
        shkruesi.println(message);
    }
}