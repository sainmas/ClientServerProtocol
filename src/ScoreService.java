import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 Executes Simple Score Access Protocol commands
 from a socket.
 */
public class ScoreService implements Runnable
{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private ScoreDatabase scoreDatabase;

    /**
     Constructs a service object that processes commands
     from a socket for a bank.
     @param aSocket the socket
     @param aScoreDatabase the Database
     */
    public ScoreService(Socket aSocket, ScoreDatabase aScoreDatabase)
    {
        s = aSocket;
        scoreDatabase = aScoreDatabase;
    }

    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    /**
     Executes all commands until the QUIT command or the
     end of input.
     */
    public void doService() throws IOException
    {
        while (true)
        {
            if (!in.hasNext()) { return; }
            String command = in.next();
            if (command.equals("QUIT")) { return; }
            else { executeCommand(command); }
        }
    }

    /**
     Executes a single command.
     @param command the command to execute
     */
    public void executeCommand(String command)
    {
        int account = in.nextInt();
        if (command.equals("ENTER")) {
            double score = in.nextDouble();
            scoreDatabase.enter(account, score);
        }

        else if (command.equals("DELETE")) {
            int attempt = in.nextInt();
            scoreDatabase.delete(account, attempt);
        }

        else if (command.equals("HIGHEST")) {
            out.println(account + " " + scoreDatabase.highest(account));
            out.flush();
            return;
        }

        else if (!command.equals("ATTEMPTS"))
        {
            out.println("Invalid command");
            out.flush();
            return;
        }
        out.println(account + " " + scoreDatabase.getAttempts(account));
        out.flush();
    }
}