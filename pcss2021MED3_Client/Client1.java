import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Type the IP or name of the server, if on the same machine type: localhost ");
            String ip = scan.nextLine();
            System.out.println("Type the port of the server, the default should be 8000 ");
            int port = Integer.parseInt(scan.nextLine());

            Socket socket = new Socket(ip, port);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            System.out.println("Successfully joined the server");

            System.out.print("Enter you Username: ");
            String alias = scan.nextLine();

            output.writeUTF(alias);
            String username = input.readUTF();

            System.out.println("Username: " + username);
            output.flush();


            System.out.println("Type Join to 'join' room or 'quit' to exit lobby");

            Thread write = new Thread(() -> {
                boolean connect = true;
                while (connect) {
                    try {
                        String message = scan.nextLine();
                        output.writeUTF(message);
                        output.flush();

                        if (message.equalsIgnoreCase("quit")) {
                            socket.close();
                            connect = false;
                            scan.close();
                        }

                        /*if (message.equalsIgnoreCase("join")) {
                            connect = false;
//							scan.close();
                        }*/

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            });
            write.start();

            Thread read = new Thread(() -> {
                boolean connect = true;
                boolean chat = true;
                while (connect) {
                    try {
                        String message = input.readUTF();
                        System.out.println(message);
                        if (message.equalsIgnoreCase("Joined Chat")) {
                            chat = false;
                            while (!chat){
                                message = input.readUTF();
                                output.writeUTF(message);
                                String messageR = input.readUTF();
                                System.out.println(messageR);
                                System.out.print("["+ username+"]: ");
                            }
                        }
                    } catch (IOException e) {
                        System.out.println(e + " SocketException expected, do not worry");
                        break;
                    }
                }


            });
            read.start();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}