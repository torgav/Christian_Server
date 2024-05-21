import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ChristianServer{

    public static void main(String[] args) {
        int port = 4446; 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("\n\nServer started. Listening on port " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        String response = mathSolver(inputLine);
                        out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("\n\nException caught when trying to listen on port "
                            + port + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("\n\nCould not listen on port " + port);
            System.out.println(e.getMessage());
        }
        }
    public static String mathSolver(String msg){
        String[] elements = msg.split(" ");
        if(elements.length != 3){
                return "Invalid Input";
        }
        try{ 
                int numb1 = Integer.parseInt(elements[0]);
                int numb2 = Integer.parseInt(elements[2]);
                String op = elements[1];
                int answear;

                if(op.equals("+")){
                        answear = numb1+numb2;
                        return "The answear for: "+ numb1 +" + "+ numb2 +" = "+answear;  
                }else if(op.equals("/")){
                        answear = numb1/numb2;
                        return "The answear for: "+ numb1 +" / "+ numb2 +" = "+answear; 
                }else if(op.equals("-")){
                        answear = numb1-numb2;
                        return "The answear for: "+ numb1 +" - "+ numb2 +" = "+answear; 
                }else if(op.equals("*")){
                        answear = numb1*numb2;
                        return "The answear for: "+ numb1 +" * "+ numb2 +" = "+answear; 
                }else{
                        return "Unvalid use of op";
                }
        }catch (NumberFormatException e) {
            return "Invalid number format";
        }
    }
}