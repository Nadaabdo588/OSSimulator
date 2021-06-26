
import java.io.*;
import java.util.HashMap;

public class OSSimulator {
    MemoryWord[] memory;
    int processID;

    public OSSimulator() {
        memory = new MemoryWord[300];
        processID = -1;
    }

    public void print(String x) {

        System.out.println(readFromMemo(x));
    }
    public Object readFromMemo(String x){
        for(int i = processID*100-50; i<processID*100;i++)
        {
            if(memory[i] == null)
                break;
            if(memory[i].getName().equals(x))
            {
                System.out.println("At index "+i+"Memory Word"+memory[i]+" has been read");
                return memory[i].getValue();
            }

        }
        System.out.println( x+" has been read from memory although it is not there");
        return x;
    }
    public  void writeToMemo(String x,String y){
        for(int i = processID*100-50; i<processID*100;i++) {

            if (memory[i] == null) {
                memory[i] = new MemoryWord(x, y);
                System.out.println("At index i " +"Memory word "+ memory[i]+" has been written");
                break;
            }
            if(memory[i].getName().equals(x))
            {
                System.out.println("At index "+i+" Memory word Value has been ovewritten with value "+ y);
                memory[i].setValue(y);
                break;
            }
        }
    }
    public void writeFile(String a, String b) throws IOException {
        String fileName = (String)readFromMemo(a);
        PrintWriter pw = new PrintWriter(new FileWriter(fileName+".txt"));
        String data =(String) readFromMemo(b);
        pw.print(data);
        pw.close();
        pw.flush();
    }
    public String readPrograms(String program) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(program+".txt"));
        StringBuilder ans = new StringBuilder();
        if(br.ready())ans.append(br.readLine());
        while(br.ready())
        {
            ans.append("\n");
            ans.append(br.readLine());
        }
        return ans.toString();
    }
    public String readFile(String a) throws IOException {
        String fileName = (String)readFromMemo(a);
        BufferedReader br = new BufferedReader(new FileReader(fileName+".txt"));
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

    public static void main(String[] args) {

        String x = "ad";
        x.hashCode();

        System.out.println(x.hashCode());
    }




}
