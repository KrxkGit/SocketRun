package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    Socket clientSocket;
    public void Connect(String serverName ,int port) throws IOException {
        this.clientSocket = new Socket(serverName, port);
        System.out.println("远程主机地址： " + this.clientSocket.getRemoteSocketAddress());
    }
    public Boolean Send() throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入命令：");
        String next_command = scanner.nextLine();
        dataOutputStream.writeUTF(next_command);

        if(next_command.equals("exit")) {
            this.clientSocket.close();
            return false;
        }
        return true;
    }
}
