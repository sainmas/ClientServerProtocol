import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 A server that executes the Simple Database Access Protocol.
 */
public class ScoreServer
{
    public static void main(String[] args) throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        ScoreDatabase scoreDatabase = new ScoreDatabase(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for users to connect...");

        while (true)
        {
            Socket s = server.accept();
            System.out.println("User connected.");
            ScoreService service = new ScoreService(s, scoreDatabase);
            Thread t = new Thread(service);
            t.start();
        }
    }
}