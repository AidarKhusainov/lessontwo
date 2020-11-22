package part1.lesson10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputConsole {
    String input = "";

    InputConsole() {
    }

    public String getContentFromConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String tmp = "";
        do {
            tmp = reader.readLine();
            if ("".equals(tmp)) break;

            input = input + tmp + "\n";
        } while (true);

        return input;
    }
}
