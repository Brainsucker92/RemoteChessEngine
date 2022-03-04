package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.function.BooleanSupplier;

public class CharacterStreamConnector extends Thread {

    private final BooleanSupplier keepAlive;
    private final PrintStream printStream;
    private final BufferedReader bufferedReader;

    public CharacterStreamConnector(BooleanSupplier keepAlive, PrintStream printStream, BufferedReader bufferedReader) {
        this.keepAlive = keepAlive;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        while (keepAlive.getAsBoolean()) {
            try {
                String line = bufferedReader.readLine();
                printStream.println(line);
                if (line == null) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

