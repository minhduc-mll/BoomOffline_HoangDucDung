package hoang.duc.dung.boomoffline.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOClass {
    public static String path = "res\\highscore.txt";

    public static String Read() {
        // init
        String str = null;
        File file = new File(path);

        try {
            // Input class
            Scanner sc = new Scanner(file);
            // loop read whole file
            while (sc.hasNext())  {
                str = sc.nextLine(); // real all file
            }
        } catch (Exception e) {
            e.getMessage(); // get error mess
        }

        return str;
    }

    public static void write(int point) {
        File file =new File (path);

        try (FileWriter fw = new FileWriter (file,false);
             BufferedWriter bf = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bf) ) {
            pw.println(point);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
