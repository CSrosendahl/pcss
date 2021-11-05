import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client{



    public static void main(String[] args) {

        Client.ClientEx();


    }

    public static void ClientEx(){
        Scanner input = new Scanner(System.in);
        boolean connect = true;

        try {
            Socket connectToServer = new Socket("192.168.0.36", 6969);


            DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
            DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
            System.out.println("Connected to the chat server");
            while(connect){

                System.out.print("Enter you Username: ");
                String alias = input.nextLine();

                osToServer.writeUTF(alias);


                String username = isFromServer.readUTF();

                System.out.println("Username: " + username);

                String message = "a";
                System.out.print("["+ username+"]: ");

                while (!message.equals("/quit")) {
                    message = input.nextLine();
                    osToServer.writeUTF(message);
                    String messageR = isFromServer.readUTF();
                    System.out.println(messageR);
                    System.out.print("["+ username+"]: ");



                }





            }

        } catch (IOException ex) {
            System.out.println(ex.toString() + '\n');
        }
    }


}


