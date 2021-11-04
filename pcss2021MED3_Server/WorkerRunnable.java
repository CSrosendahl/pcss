import java.io.*;
import java.net.*;
import java.util.*;



public class WorkerRunnable extends Thread {

    private Socket clientSocket = null;
    private Server server;
    private DataOutputStream output;
    boolean connected = true;

    public WorkerRunnable(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {

        try {
          String serverMessage;



            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());



            while(connected){
                String clientName = input.readUTF();
                server.addClientName(clientName);
                output.writeUTF(clientName);
                boolean usernameSet = true;
                while(usernameSet) {
                    String clientMessage;

                    clientMessage = input.readUTF();
                    serverMessage = "[" + clientName + "]" + clientMessage;
                    output.writeUTF(serverMessage);
                    server.broadcast(serverMessage, this);
                }

                /*if(clientMessage.equals("/rooms")) {

                    // Enter a room
                    System.out.println("You are about to enter a room");
                } */
            }







        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    public void sendMessage(String message){
        try{
            output.writeUTF(message);
            

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

