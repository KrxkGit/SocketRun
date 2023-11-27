package org.example;
import org.apache.commons.cli.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Option opt = new Option("p", "patten", true, "Running Pattern");
        Option opt1 = new Option("P","Port", true, "Remote Port");
        Option opt3 = new Option("a","Address", true, "Server Address");
        System.out.println("Welcome to Use SocketRun!");

        Options options = new Options();
        options.addOption(opt);
        options.addOption(opt1);
        options.addOption(opt3);

        CommandLine commandLine = null;
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String pat = null;

        pat = commandLine.getOptionValue("p", "client");
        System.out.printf("Running in %s pattern.\n", pat);

        if(pat.equals("server")) { // 服务模式
            System.out.println("服务器模式");
            Server server = new Server();
            server.Create();
            server.run();

        } else { // 客户端模式
            System.out.println("客户端模式");
            Client client = new Client();

            String serverName = commandLine.getOptionValue("a", "localhost");
            int port = Integer.parseInt(commandLine.getOptionValue("P","0"));

            client.Connect(serverName, port);
            while (client.Send()) ;
        }

    }
}