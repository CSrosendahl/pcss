import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
       boolean connect = true;

        try {
            Socket connectToServer = new Socket("localhost", 6969);
            DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
            DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
            System.out.println("Connected to the chat server");
            while(connect){
                System.out.print("Enter you Username: ");
                String alias = input.nextLine();

                osToServer.writeUTF(alias);


                String username = isFromServer.readUTF();

                System.out.println("Username: " + username);


                if (input.next().equals("no")) {
                    connect = false;
                }
                isFromServer.readUTF();
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }
}