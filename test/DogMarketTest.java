import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DogMarketTest {

    public boolean test(String command) {
        String baseBath = "test/" + command;
        String actualInputPath = String.format("%s.txt", baseBath);
        String mergedOutputPath = String.format("%s output.txt", baseBath);
        String expectedOutputPath = String.format("%s expected.txt", baseBath);

        PrintStream sout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            System.setIn(new FileInputStream(new File(actualInputPath)));
            System.setOut(new PrintStream(out));
            new DogMarket(new UserInterface()).run();
            System.setOut(sout);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            System.setOut(sout);
        }
        try {
            String mergedInputOutput = merge(actualInputPath, out.toString());
            Files.write(Paths.get(mergedOutputPath), mergedInputOutput.getBytes());
            return equals(mergedInputOutput, expectedOutputPath);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }

    private boolean equals(String actualOutput, String expectedOutputPath) throws IOException {
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputPath)), StandardCharsets.UTF_8)
                .trim();
        return actualOutput.trim().equals(expectedOutput);
    }

    private String merge(String actualInputPath, String actualOutput) throws IOException {
        String[] outputLines = actualOutput.split(">");
        int index = 0;
        String merged = "";
        for (String inputLine : Files.readAllLines(Paths.get(actualInputPath))) {
            String line = outputLines[index++] + ">\n" + inputLine;
            merged += line;
            System.out.print(line);
        }
        while (index < outputLines.length) {
            String line = outputLines[index++];
            merged += line;
            System.out.println(line);
        }
        return merged;
    }

    public static void main(String[] args) {
        PrintStream sout = System.out;
        DogMarketTest dogMarketTest = new DogMarketTest();
        // @formatter:off
        List<String> inputs = Arrays.asList("all");
        // @formatter:on

        List<String> results = new ArrayList<>();
        for (String input : inputs) {
            try {
                boolean success = dogMarketTest.test(input);
                results.add(input + ": " + success);
            } catch (Exception e) {
                System.setOut(sout);
                e.printStackTrace();
            }
        }

        System.out.println("#########################");
        for (String result : results) {
            System.out.println(result);
        }
        System.out.println("#########################");
    }
}
