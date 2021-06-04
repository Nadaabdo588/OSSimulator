
import java.io.*;
import java.util.HashMap;

public class OSSimulator {
    HashMap<String, String> memo;

    public OSSimulator() {
        memo = new HashMap<>();
    }

    public void print(String x) {
        System.out.println(memo.getOrDefault(x, x));
    }
    public  String readFromMemo(String x){
        return memo.getOrDefault(x,x);
    }
    public  void writeToMemo(String x,String y){
        memo.put(x,y);
    }
    public void writeFile(String a, String b) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(a+".txt"));
        String bVal = readFromMemo(b);
        pw.print(bVal);
        pw.close();
        pw.flush();
    }
    public String readFile(String a) throws IOException {
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

    public static void main(String[] args) {

    }




}
