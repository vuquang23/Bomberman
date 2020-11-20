
import java.io.*;
import java.util.Scanner;

public class test {
    static String path = System.getProperty("user.dir") + "/res/levels/";
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "Level1.txt";
        Scanner objReader = new Scanner(new File(path + fileName));
        int stage = objReader.nextInt();
        System.out.println(stage);


    }
}
