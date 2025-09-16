package br.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final Integer PORTA = 8089;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(PORTA)){
            System.out.printf("Server Running on port %d%n", PORTA);

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // objeto request que vem do cliente || InputStreamReader converte bytes em characteres
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String mensagem = reader.readLine();
                System.out.println("Mensagem do cliente: " + mensagem);



                String clientRes = "message from server"; // string de resposta ao cliente
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true); // objeto response para enviar ao cliente
                writer.println(clientRes);  // resposta ao cliente


                clientSocket.close();
            }



        }catch (IOException e){
            e.printStackTrace();
        }



    }
}