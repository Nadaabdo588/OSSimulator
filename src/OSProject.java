import com.sun.source.tree.WhileLoopTree;

import java.io.*;
import java.util.*;


public class OSProject {
    Queue<Integer> processes;
    OSSimulator os;

    public OSProject() throws IOException {
        os = new OSSimulator();
        processes = new LinkedList<>();
        for(int i=1;i<=3;i++)
        {
            parseProcess("Program "+i);
            processes.add(i);
        }



    }

    public int scheduler(int running)
    {
        if(running== -1)
        {
            os.memory[1].setValue("running");

            return processes.poll();
        }
        int nxt = -1;
        if(!processes.isEmpty())
            nxt = processes.poll();
        if(nxt == -1)
        {
            int pcRunning = (int) os.memory[(running-1)*100+2].getValue();
            if(os.memory[pcRunning]==null)
            {
                os.memory[(running-1)*100+1].setValue("terminated");
                return -1;
            }
            return running;
        }


        int pcRunning = (int) os.memory[(running-1)*100+2].getValue();
        if(os.memory[pcRunning]==null)
        {
            os.memory[(running-1)*100+1].setValue("terminated");
        }
        else
        {
            os.memory[(running-1)*100+1].setValue("ready");
            processes.add(running);
        }
        os.memory[(nxt-1)*100+1].setValue("running");
        return nxt;

    }


    public void parseProcess(String programName) throws IOException {
        String file= os.readPrograms(programName);
        int programNum = Integer.parseInt(programName.charAt(programName.length()-1)+"");
        int memidx = (programNum-1)*100;
        os.memory[memidx++] = new MemoryWord("Process ID", programNum);
        os.memory[memidx++] = new MemoryWord("Process State", "ready");
        os.memory[memidx++] = new MemoryWord("PC", memidx+2);
        os.memory[memidx++] = new MemoryWord("start", memidx-4);
        os.memory[memidx++] = new MemoryWord("end", programNum*100-1);
        String [] lines=file.split("\n");
        for(int i=0; i<lines.length; i++)
        {
            // check if we exceeded instructions boundary
            os.memory[memidx++] = new MemoryWord("instruction "+i, lines[i]);
        }
    }

    public void executeProcesses() throws IOException {

        for(int running = scheduler(-1);running != -1; running = scheduler(running))
        {
            int pcRunning = (int)os.memory[(running-1)*100 + 2].getValue();
            os.processID = running;
            for(int i=0;i<2;i++)
            {
                if(os.memory[pcRunning]== null)
                    break;
                execute((String)os.memory[pcRunning++].getValue());
            }
            os.memory[(running-1)*100 + 2].setValue(pcRunning);
        }
    }


    public void execute(String instruction) throws IOException {
        String[] line = instruction.split(" ");

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

    public void add(String x, String y) {

        int xVal = Integer.parseInt((String)os.readFromMemo(x));
        int yVal = Integer.parseInt((String)os.readFromMemo(y));
        os.writeToMemo(x, "" + (xVal + yVal));


    }

    public void assign(String x, String y) {

        os.writeToMemo(x, (String)os.readFromMemo(y));
    }


    public static void main(String[] args) throws IOException
    {
        OSProject tmp = new OSProject();
        tmp.executeProcesses();
//        for(int i=0;i<300;i++)
//        {
//            if(tmp.os.memory[i] != null)
//                System.out.println(tmp.os.memory[i]);
//        }






    }
}
