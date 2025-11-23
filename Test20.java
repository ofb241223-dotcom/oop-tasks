import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test20 {
    public static void main(String[] args) {
        String fileName = "output.txt";
        int wordCount = 0;

        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] words = line.split("\\s+");
                    wordCount += words.length;
                }
            }
            System.out.println("文件 \"" + fileName + "\" 中的单词数量为：" + wordCount);
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件: " + fileName);
        }
    }
}