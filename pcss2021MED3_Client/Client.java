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
            while(connect){
                System.out.print("Enter you alias: ");
                String alias = input.nextLine();

                osToServer.writeUTF(alias);
//                isFromServer.readUTF();

                String username = isFromServer.readUTF();

                System.out.println("Username: " + username);

                System.out.print("Type yes to continue with a new set of value or no to stop: ");
                if (input.next().equals("no")) {
                    connect = false;
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }
}