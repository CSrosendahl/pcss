import java.io.*;
import java.net.*;
import java.util.*;



public class WorkerRunnable extends Thread {

    private Socket clientSocket = null;
    private Server server;
    private DataOutputStream output;
    private boolean connected = false;
    private boolean usernameSet = false;
    private boolean Chat = false;
    //private PrintWriter writer;

    public WorkerRunnable(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    public void run() {
        try {
            connected = true;
            String serverMessage;
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
            //BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            //writer = new PrintWriter(output,true);
            while(connected){
                String clientName = input.readUTF();
                server.addClientName(clientName);
                output.writeUTF(clientName);
                usernameSet = true;

                String Command = input.readUTF();
                if(Command.equals("join")){
                    Chat = true;
                    serverMessage = "Joined Chat";
                    server.broadcast(serverMessage,null);
                }



                while(Chat) {

                        String clientMessage;
                        clientMessage = input.readUTF();
                            serverMessage = "[" + clientName + "]" + clientMessage;
                            //output.writeUTF(serverMessage);
                            server.broadcast(serverMessage, this);

                        }


                }

                /*if(clientMessage.equals("/rooms")) {

                    // Enter a room
                    System.out.println("You are about to enter a room");
                } */








        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    public void sendMessage(String message){
            try {
                output.writeUTF(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

            




    }
}

