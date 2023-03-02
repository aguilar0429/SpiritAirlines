/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eduardo Aguilar
 */
public class Server1 {

    private static List<Socket> clients = new ArrayList<>();
    static String url = "jdbc:sqlserver://EDUARDO_AGUILAR\\SQLEXPRESS;databaseName=SpiritAirlines;encrypt=true;trustServerCertificate=true";
    static public String username = "edu";
    static public String password = "123";

    public static void connectSQL() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to SQL Server.");
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en Finalizar");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        connectSQL();
        ServerSocket serverSocket = new ServerSocket(3333);
        System.out.println("Server is listening on port 3333");

        while (true) {
            RSeats ="";
            RUsers = "";
            
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String question = in.readLine();
            System.out.println(question);
            System.out.println("Client's question: " + question);
            
            
            try {
                    System.out.println("hi2");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    System.out.println("Connected to SQL Server.");
                    Statement statement = connection.createStatement();

                    ResultSet rs2;
                    // String sql2 = "select * from Seats where FlightNum = '"+vuelo+"'";
                    rs2 = statement.executeQuery(question);
                    while (rs2.next()) {
                        String seat = "";
                        String user = "";
                        String YoN = rs2.getString("Reserved");
                        System.out.println(YoN);
                        if (YoN.equals("1")) {
                            seat = rs2.getString("SeatNum");
                            user = rs2.getString("UserReserved");
                            RUsers+=user+" ";
                            RSeats+=seat+" ";
                        }
                    }
                    System.out.println(RSeats);
                    System.out.println(RUsers);

                    connection.close();

                } catch (SQLException ex) {
                    //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error al Updatear Tabla Proyectos");
                }

            String answer = "" ; // replace with your own logic to generate an answer
            
            
            out.println(RSeats);
            out.println(RUsers);
            

            socket.close();
            System.out.println("Connection with client closed");
        }
    }

    public static String RSeats = "";
    public static String RUsers = "";
}
