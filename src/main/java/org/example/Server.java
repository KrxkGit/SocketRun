package org.example;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server extends Thread{
    ServerSocket server;

    int port;
    public void Create() throws IOException {
        this.server = new ServerSocket(0);
        this.port =  this.server.getLocalPort();
        this.server.setSoTimeout(120 * 1000); // 等待两分钟
        System.out.println("本地端口：" + this.port);
    }

    public void run() {
        try {
            Socket s = server.accept();
            System.out.println("远程地址： " + s.getRemoteSocketAddress());
            RunCommand runCommand = new RunCommand();
            while (true) {
                DataInputStream dataInputStream = new DataInputStream(s.getInputStream());
                String content = dataInputStream.readUTF();
                System.out.println(content);
                if(content.equals("exit")) {
                    this.server.close();
                    return;
                }
                runCommand.RunCommand(content);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Socket Time out.");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
