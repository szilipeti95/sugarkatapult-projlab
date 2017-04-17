package tester;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    /**
     * Teszter
     *
     * @param args the command line arguments
     */
    private static InputStream is;
    protected static OutputStream os;
    public static PrintWriter prw;
    public static BufferedReader br;
    public static StringBuilder output = new StringBuilder();
    public static ArrayList<StringBuilder> commands = new ArrayList<StringBuilder>();
    public static int index = -1;

    public Tester(InputStream is) {
        this.is = is;
    }

    public static void readOutput() throws IOException {

        String line = null;

        while ((line = br.readLine()) != null) {
            output.append(line + '\n');
        }
    }

    public static void writeInput(int i) throws IOException {
        prw.print(commands.get(i).toString());
        prw.flush();
        prw.close();

    }

    public static int runProgram() throws InterruptedException, IOException {
        ProcessBuilder procb = new ProcessBuilder("java", "App");
        procb.redirectErrorStream(true);
        procb.directory(new File("src"));
        int result;
        Process p = procb.start();
        os = p.getOutputStream();
        is = p.getInputStream();
        prw = new PrintWriter(os);
        br = new BufferedReader(new InputStreamReader(is));

        if (index == -1) {
            for (int i = 0; i < commands.size(); i++) {

                writeInput(i);
                readOutput();
            }
        } else {
            writeInput(index);
            readOutput();
        }

        prw.close();
        br.close();
        result = p.waitFor();

        return result;
    }

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        String line = null;
        System.out.println("Which test do you want to run? (Number 1-14)");
        ArrayList<StringBuilder> sb = new ArrayList<StringBuilder>();
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line2 = null;
            int ind = 0;
            commands.add(new StringBuilder());
            while ((line2 = br.readLine()) != null) {

                if (line2.equals(".")) {
                    ind++;
                    commands.add(new StringBuilder());
                } else {
                    commands.get(ind).append(line2 + '\n');
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("output.txt"))) {
            String line2 = null;

            int kimenetID = 0;
            sb.add(new StringBuilder());
            while ((line2 = br.readLine()) != null) {
                if (line2.equals(".")) {
                    kimenetID++;
                    sb.add(new StringBuilder());
                } else {
                    sb.get(kimenetID).append(line2 + '\n');
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int res = -1;
        if ((line = reader.nextLine()) != null) {
            String[] split = line.split(" ");

            try {
                int i = Integer.parseInt(split[0]);
                if (i > 14 || i < 1) {
                    System.out.println("Rossz az index, ilyen teszteset nincs!");
                    return;
                }
                index = i - 1;

                try {
                    res = runProgram();
                } catch (InterruptedException ex) {

                } catch (IOException ex) {

                }
                if (output.toString().toLowerCase().equals(sb.get(index).toString().toLowerCase())) {
                    System.out.println("Teszt: " + (i) + ". OK");

                } else {
                    System.out.println("Teszt: " + (i) + ". NEM OK");
                    System.out.println("KAPOTT: ");
                    System.out.println(output.toString());
                    System.out.println("ELVART: ");
                    System.out.println(sb.get(index).toString());
                }

            } catch (NumberFormatException ne) {
                System.out.println("A megadott input nem szam!");

            }
        }
    }

}
