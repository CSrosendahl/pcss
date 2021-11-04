import java.io.*;
import java.net.*;
import java.util.*;



public class WorkerRunnable extends Thread {

    private Socket clientSocket = null;
    private Server server;
    private PrintWriter printWriter;

    public WorkerRunnable(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {

        try {
          String serverMessage =  "Connected to a client " + " at " + new Date() + '\n';
            boolean connected = true;


            DataInputStream isFromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream osToClient = new DataOutputStream(clientSocket.getOutputStream());



            while(connected){
                String clientName = isFromClient.readUTF();
                server.addClientName(clientName);
                osToClient.writeUTF(clientName);
                String clientMessage;

                clientMessage = isFromClient.readUTF();
                serverMessage = "[" + clientName + "]" + clientMessage;
                server.broadcast(serverMessage,this);

                if(clientMessage.equals("/rooms")) {

                    // Enter a room
                    System.out.println("You are about to enter a room");
                }
            }







        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    void sendMessage(String message) {
        printWriter.println(message);
    }
}

