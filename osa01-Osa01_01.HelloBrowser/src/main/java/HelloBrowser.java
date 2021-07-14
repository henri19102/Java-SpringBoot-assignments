
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.print("Where to?");
        String url = input.nextLine();
        int port = 80;
 
        
        Socket socket = new Socket(url, port);
        System.out.print(socket);

        PrintWriter writer = new PrintWriter(socket.getOutputStream());

        writer.println("GET / HTTP/1.1");
        writer.println("Host: " + url);
        writer.println();
        writer.flush();
        Scanner reader = new Scanner(socket.getInputStream());

        while (reader.hasNextLine()) {
            System.out.println(reader.nextLine());
        }
        throw new Exception("invalid url");
    }
}
