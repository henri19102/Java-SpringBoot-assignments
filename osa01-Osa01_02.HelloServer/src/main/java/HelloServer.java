
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        // luodaan palvelin porttiin 8080
        ServerSocket server = new ServerSocket(8080);

        while (true) {

            Socket socket = server.accept();
            Scanner lukija = new Scanner(socket.getInputStream());
            if (lukija.nextLine().contains("/quit")) {
                lukija.close();
                socket.close();
                server.close();
                break;
            }

            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("GET / HTTP/1.1 200 ok");
            Files.readAllLines(Paths.get("index.html")).forEach(x -> kirjoittaja.println(x));
            kirjoittaja.println();
            kirjoittaja.flush();

            lukija.close();
            kirjoittaja.close();
            socket.close();
        }

    }
}
