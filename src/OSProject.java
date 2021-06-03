import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;


public class OSProject {
    static HashMap<String, String> memo;

    public static void parseProcess(String program) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(program));
        Scanner sc = new Scanner(System.in);
        while (br.ready()) {
            String line[] = br.readLine().split(" ");
            if (line.length == 2) {
                if (line[0].equals("print")) {
                    print(line[1]);
                } else if (line[0].equals("readFile")) {
                    String data = readFile(line[1]);
                }
            } else if (line.length == 3) {
                if (line[0].equals("add")) {
                    add(line[1], line[2]);
                } else if (line[0].equals("assign")) {
                    String passed;
                    if (line[2].equals("input")) {
                        passed = sc.next();
                    } else {
                        passed = line[2];
                    }
                    assign(line[1], passed);

                } else if (line[0].equals("writeFile")) {
                    writeFile(line[1], line[2]);
                }
            } else if (line.length == 4) {
                if (line[0].equals("assign")) {
                    if (line[2].equals("readFile")) {
                        String passed = readFile(line[3]);
                        assign(line[1], passed);
                    }
                }
            }
        }
    }

    public static void print(String x) {
        System.out.println(memo.getOrDefault(x, x));
    }

    public static void add(String x, String y) {

    }

    public static void assign(String x, String y) {

    }

    public static void writeFile(String a, String b) {

    }

    public static String readFile(String a) {
        return "";
    }


    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Program 1");
        BufferedReader br = new BufferedReader(fr);
        System.out.println(br.readLine());
    }
}
