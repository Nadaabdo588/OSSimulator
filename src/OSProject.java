import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;


public class OSProject {
    static HashMap<String, String> memo=new HashMap<>();

    public static void parseProcess(String program) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(program));
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
                        passed = readAnInput();
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

        int xVal = Integer.parseInt(readFromMemo(x));
        int yVal = Integer.parseInt(readFromMemo(y));
        writeToMemo(x, ""+(xVal+yVal));



    }
    public static void assign(String x, String y) {

        writeToMemo(x,readFromMemo(y));
    }
    public static void writeFile(String a, String b) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(a+".txt"));
        String bVal = readFromMemo(b);
        pw.print(bVal);
        pw.close();
        pw.flush();
    }
    public static String readFile(String a) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(a+".txt"));
        StringBuilder ans = new StringBuilder();
        if(br.ready())ans.append(br.readLine());
        while(br.ready())
        {
            ans.append("\n");
            ans.append(br.readLine());
        }
        return ans.toString();
    }
    public static String readAnInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    public static String readFromMemo(String x){
        return memo.getOrDefault(x,x);
    }
    public static void writeToMemo(String x,String y){
        memo.put(x,y);
    }
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("Program 1");
        BufferedReader br = new BufferedReader(fr);
        System.out.println(br.readLine());

    }
}
