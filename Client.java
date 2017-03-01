/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
	
        DataOutputStream outputstr = null;  //tartalom kiiratásához
		Socket clientSocket = null; //végpont létérehozása
		BufferedReader input = null; //beolvasáshoz
	
    try {
		clientSocket = new Socket("localhost", 1112); //új végpont létrehozása
		outputstr = new DataOutputStream(clientSocket.getOutputStream()); //tartalom csatorna
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  //beolvasási csatorna
    } catch (UnknownHostException e) {
            System.out.println("***Localhost error***"); 
    } catch (IOException e) {
            System.out.println("***Cannot connect***");
    }
	
		if (clientSocket==null || outputstr ==null || input == null){ //ha nincs végpont vagy tartalom vagy bemenet
			System.out.println("***Error occured***" );
			return;
		}

	try {
	    while (true) {
			
                        System.out.println("***STOP: Makes server to stop***");
                        System.out.println("***EXIT: Client leaves***");
                        System.out.print("Message: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //beolvasáshoz
			String keyboardInput = br.readLine();
			outputstr.writeBytes( keyboardInput + "\n" ); //kiiratás

			if ( keyboardInput.equals("EXIT") || keyboardInput.equals("STOP")) { 
				break;
			}
	    }
	    
	    outputstr.close(); 
	    input.close();
	    clientSocket.close();  
	} catch (UnknownHostException e) {
	    System.out.println("Unknown host: " + e);
	} catch (IOException e) {
	    System.out.println("IOException:  " + e);
	}
    }           
}