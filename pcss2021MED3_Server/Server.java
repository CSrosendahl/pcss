import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    static int port = 6969;

    public static void main(String[] args) {

        try{

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Loan Server started at " + new Date() + '\n');
            Socket connectToClient = serverSocket.accept();


            System.out.println("Connected to a client " + " at " + new Date() + '\n');


            DataInputStream isFromClient = new DataInputStream(
                    connectToClient.getInputStream());
            DataOutputStream osToClient = new DataOutputStream(
                    connectToClient.getOutputStream());


            String username = isFromClient.readUTF();



            osToClient.writeUTF(username);




            System.out.println("Username: " + username);
            isFromClient.readUTF();



        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server socket fail");
        }
    }
}

