import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean connect = true;

        try {
            Socket connectToServer = new Socket("localhost", 6969);
            DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
            DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
            while(connect){
                System.out.print("Enter you alias: ");
                String alias = input.nextLine();

                osToServer.writeUTF(alias);


                String username = isFromServer.readUTF();

                System.out.print("Welcome " + username + "! You have arrived at the chat lobby!");
                System.out.print("From her you have the options to: Join, Create or view Online users.");
                if (input.next().equals("no")) {
                    connect = false;
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }
}