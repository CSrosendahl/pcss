import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Date;

public class WorkerRunnable implements Runnable {

    private Socket clientSocket = null;
    private String serverText = "";

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            System.out.println("Connected to a client " + " at " + new Date() + '\n');
            boolean connected = true;

            DataInputStream isFromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream osToClient = new DataOutputStream(clientSocket.getOutputStream());

            while (connected) {



                String username = isFromClient.readUTF();




                osToClient.writeUTF(username);
                System.out.println("Username: " + username);

            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}

