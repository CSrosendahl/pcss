
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Server {


    private Set<String> newClientNames = new HashSet<>();

    private Set<WorkerRunnable> workerRunnable = new HashSet<>();





    public static void main(String[] args) {



        Server server = new Server();
        server.serverthread();


    }
    void addClientName(String clientName) {
        newClientNames.add(clientName);
    }

    public void serverthread() {

            try ( ServerSocket serverSocket = new ServerSocket(6969)) {

                System.out.println("Chat Server started at " + new Date() + '\n');


                while (true) {

                    Socket connectToClient = serverSocket.accept();
                    WorkerRunnable newClient = new WorkerRunnable(connectToClient,this);
                    workerRunnable.add(newClient);
                    newClient.start();


                }
            }
            catch (IOException e) {
                System.out.println("Doesnt work");
                e.printStackTrace();
            }

    }
    void broadcast(String message, WorkerRunnable excludeUser) {
        for(WorkerRunnable aUser : workerRunnable) {
            if(aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
}

