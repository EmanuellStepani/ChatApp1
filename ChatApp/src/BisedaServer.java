import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class BisedaServer {
    private int porti;
    private Set<String> emratPerdorusve = new HashSet<>();
    private Set<ThredatEPerdorusit> perdorusiThredave = new HashSet<>();

    public BisedaServer(int porti) {
        this.porti = porti;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(porti)) {

            System.out.println("Chat Serveri eshte duke kerkuar portin " + porti);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Perdoruesi i ri eshte lidhur");

                ThredatEPerdorusit newUser = new ThredatEPerdorusit(socket, this);
                perdorusiThredave.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error ne server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public void transmetimi(String message, ThredatEPerdorusit excludeUser) {
        for (ThredatEPerdorusit aPerdorusit : perdorusiThredave) {
            if (aPerdorusit != excludeUser) {
                aPerdorusit.dergoMesazh(message);
            }
        }
    }


    public void shtoPerdorusit(String emriPerdorusit) {
        emratPerdorusve.add(emriPerdorusit);
    }


    void largoPerdorusin(String emriPerdorusit, ThredatEPerdorusit aPerdorusi) {
        boolean largimi = emratPerdorusve.remove(emriPerdorusit);
        if (largimi) {
            perdorusiThredave.remove(aPerdorusi);
            System.out.println("perdorusi " + emriPerdorusit + " u largua");
        }
    }




    Set<String> getEmriPerdoruesve() {
        return this.emratPerdorusve;
    }


    boolean kaPerdorus() {
        return !this.emratPerdorusve.isEmpty();
    }
}

