import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    static int port = 6969;

    public static void main(String[] args) {



        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(6969);
                System.out.println("Chat Server started at " + new Date() + '\n');

                int clientNo = 0;
                while (true) {

                    Socket connectToClient = serverSocket.accept();
                    clientNo++;

                    System.out.println("Starting thread for client " + clientNo + " at " + new Date() + '\n');

                    InetAddress inetAddress = connectToClient.getInetAddress();
                    System.out.println("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
                    System.out.println("Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress() + '\n');
                    new Thread(
                            new WorkerRunnable(
                                    connectToClient, "Multithreaded Server")
                    ).start();
                }
            }
            catch (IOException e) {
                System.err.println(e);
            }
        }).start();


    }
}

