package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                try (Socket socket = server.accept();) {
                    try (OutputStream output = socket.getOutputStream();
                         BufferedReader input = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                        output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                            if (string.contains("msg=Bye")) {
                                server.close();
                                break;
                            }
                            System.out.println(string);
                        }
                        output.flush();
                    }
                } catch (IOException e) {
                    if (server.isClosed()) {
                        System.out.println("Сервер планово закрыт");
                        break;
                    }
                    e.printStackTrace();
                }
            }
        }
    }
}