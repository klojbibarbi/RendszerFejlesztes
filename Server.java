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

public class Server {
    public static void main(String args[]) {
		int port = 1112; 
		Server server = new Server(port); 
		server.startServer();
    }

    ServerSocket serverSock = null;
    Socket clientSocket = null;
    int numOfConnections = 0;
	
    int port;
	
    public Server(int port) {
		this.port = port;
    }

    public void stopServer() {
		System.out.println( "***Server stoped***" );
		System.exit(0);
    }

    public void startServer() {	
        try {
			serverSock = new ServerSocket(port);
        }
        catch (IOException e) {
			System.out.println(e);
        }   
	
		System.out.println("***Server is running***");
	
		while (true) {
			try {
				clientSocket = serverSock.accept();
				numOfConnections ++;
				ServerConnection oneconnection = new ServerConnection(clientSocket, numOfConnections, this);
				new Thread(oneconnection).start();
			}   
			catch (IOException e) {
				System.out.println(e);
			}
		}
    }	
}

class ServerConnection implements Runnable {
    BufferedReader input;
    PrintStream os;
    Socket clientSocket;
    int id;
    Server server;
	static int connectionCounter = 0;
	
    public ServerConnection(Socket clientSocket, int id, Server server) {
		this.clientSocket = clientSocket;
		this.id = id;
		this.server = server;
		connectionCounter++;
		System.out.println("Number of connections: " + connectionCounter);
		System.out.println("Connection with  " + id + ". client: " + clientSocket + " established");
		try {
			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
    }

    public void run() {
        String line;
		try {
			boolean serverStop = false;

			while (true) {
                line = input.readLine();
				System.out.println("Message from " + id + ". client is: " + line);
				if (line.equals("STOP") ) {
					serverStop = true;
					break;
				}
				if ( line.equals("quit")){
					connectionCounter--;
					System.out.println("Kapcsolatok szama: " + connectionCounter);
					break;
				}	
								
			}
			
			System.out.println("***Connection closed for client " + id + "***");
            input.close();
            os.close();
            clientSocket.close();

			if ( serverStop ) server.stopServer();
		} catch (IOException e) {
			System.out.println(e);
		}
    }
}
