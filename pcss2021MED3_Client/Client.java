import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client{



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

                String message;

                do {

                    message = input.nextLine();
                    osToServer.writeUTF(message);
                    String messageR = isFromServer.readUTF();
                    System.out.print(messageR);

                } while (!message.equals("/quit"));





            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }

    }



}