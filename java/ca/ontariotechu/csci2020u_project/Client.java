package ca.ontariotechu.csci2020u_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    /**
     *
     * Connect to server, then start a new thread to listen to the message sent from server
     * Then user scanner to get the message from the command line, send to server
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Connecting to server");
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            new Thread(()->{
                String message = "";
                while (true){
                    try {
                        message = reader.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            String message = "";
            while (!message.equals("done")){
                message = sc.nextLine();
                printWriter.println(message);
            }

            sc.close();


        }catch (Exception e){
            e.printStackTrace();
        }


    }


}