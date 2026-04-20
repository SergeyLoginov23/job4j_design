package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final String MSG = "msg=";
    private static final String EXIT = "Exit";
    private static final String HELLO = "Hello";
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                try (Socket socket = server.accept();) {
                    try (OutputStream output = socket.getOutputStream();
                         BufferedReader input = new BufferedReader(
                                 new InputStreamReader(socket.getInputStream()))) {
                                     output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                     String string = input.readLine();
                                     if (string.contains(MSG)) {
                                         int startIndex = string.indexOf(MSG) + MSG.length();
                                         string = string.substring(startIndex);
                                         string = string.substring(0, string.indexOf(" "));
                                         if (EXIT.equals(string)) {
                                             server.close();
                                         } else if (HELLO.equals(string)) {
                                             output.write(HELLO.getBytes());
                                         } else {
                                             output.write(string.getBytes());
                                         }
                                     }
                                     output.flush();
                                }
                } catch (IOException e) {
                    if (server.isClosed()) {
                        LOG.info("Сервер планово закрыт");
                        break;
                    }
                    LOG.error("Server error", e);
                }
            }
        } catch (IOException e) {
            LOG.error("Server error", e);
        }
    }
}