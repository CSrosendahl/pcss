import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    static int port = 6969;

    public static void main(String[] args) {

        try{

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Loan Server started at " + new Date() + '\n');
            Socket connectToClient = serverSocket.accept();


            System.out.println("Connected to a client " + " at " + new Date() + '\n');


            DataInputStream isFromClient = new DataInputStream(
                    connectToClient.getInputStream());
            DataOutputStream osToClient = new DataOutputStream(
                    connectToClient.getOutputStream());


            double annualInterestRate = isFromClient.readDouble();

            int numOfYears = isFromClient.readInt();

            double loanAmount = isFromClient.readDouble();

            double monthlyInterestRate = annualInterestRate/1200;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1-(1/Math.pow(1 + monthlyInterestRate, numOfYears * 12)));

            double totalPayment = monthlyPayment * numOfYears * 12;

            osToClient.writeDouble(monthlyPayment);
            osToClient.writeDouble(totalPayment);

            System.out.println("Annual Interest Rate: " + annualInterestRate + "\nNumber of Years: " + numOfYears + "\nLoan Amount: " +loanAmount + "\n");


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server socket fail");
        }
    }
}

