import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;


public class OSProject {
    OSSimulator os;

    public OSProject() {
        os = new OSSimulator();
    }

    public void parseProcess(String program) throws IOException {
        String file= os.readFile(program);
        String [] lines=file.split("\n");
        for(int i=0; i<lines.length; i++) {
            String line[] = lines[i].split(" ");
            if (line.length == 2) {
                if (line[0].equals("print")) {
                    os.print(line[1]);
                } else if (line[0].equals("readFile")) {
                    String data = os.readFile(line[1]);
                }
            } else if (line.length == 3) {
                if (line[0].equals("add")) {
                    add(line[1], line[2]);
                } else if (line[0].equals("assign")) {
                    String passed;
                    if (line[2].equals("input")) {
                        passed = os.readAnInput();
                    } else {
                        passed = line[2];
                    }
                    assign(line[1], passed);

                } else if (line[0].equals("writeFile")) {
                    os.writeFile(line[1], line[2]);
                }
            } else if (line.length == 4) {
                if (line[0].equals("assign")) {
                    if (line[2].equals("readFile")) {
                        String passed = os.readFile(line[3]);
                        assign(line[1], passed);
                    }
                }
            }
        }
    }

    public void add(String x, String y) {

        int xVal = Integer.parseInt(os.readFromMemo(x));
        int yVal = Integer.parseInt(os.readFromMemo(y));
        os.writeToMemo(x, "" + (xVal + yVal));


    }

    public void assign(String x, String y) {

        os.writeToMemo(x, os.readFromMemo(y));
    }


    public static void main(String[] args) throws IOException {


    }
}
