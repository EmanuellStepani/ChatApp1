public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer 8989");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        BisedaServer server = new BisedaServer(port);
        server.execute();
    }
}
