package org.example;

import java.io.IOException;

public class RunCommand {
    Runtime runtime;
    public RunCommand() {
        this.runtime =  Runtime.getRuntime();
    }

    public void RunCommand(String command) throws IOException {
        String[] cmdCommand = new String[]{"cmd", "/c", command};
        this.runtime.exec(cmdCommand);
    }
}
