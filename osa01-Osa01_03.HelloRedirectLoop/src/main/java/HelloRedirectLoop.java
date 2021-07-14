
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
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
            kirjoittaja.println("GET / HTTP/1.1 302 Found");
            kirjoittaja.println("Location: http://localhost:8080");
            kirjoittaja.flush();

            lukija.close();
            kirjoittaja.close();
            socket.close();
        }

    }
}
