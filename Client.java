/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neferet
 */
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
	
                DataOutputStream outputstr = null;
        
		Socket clientSocket = null;
		
		BufferedReader input = null;
	
    try {
		clientSocket = new Socket("localhost", 1112);
		outputstr = new DataOutputStream(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    } catch (UnknownHostException e) {
            System.err.println("***Localhost error***");
    } catch (IOException e) {
            System.err.println("***Cannot connect***");
    }
	


	try {
	    while (true) {
			
                        System.out.println("***STOP: Makes server to stop***");
                        System.out.println("***EXIT: Client leaves***");
                        System.out.print("Message: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String keyboardInput = br.readLine();
			outputstr.writeBytes( keyboardInput + "\n" );

			if ( keyboardInput.equals("EXIT") || keyboardInput.equals("STOP")) {
				break;
			}
	    }
	    
	    outputstr.close();
	    input.close();
	    clientSocket.close();  
	} catch (UnknownHostException e) {
	    System.err.println("Unknown host: " + e);
	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }           
}