import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "output.txt";
        System.out.println("请输入要写入文件的内容（输入 'END' 结束）：");
        StringBuilder sb1 = new StringBuilder();
        while (true) {
            String content = scanner.nextLine();
            if ("END".equals(content)) {
                break;
            }
            sb1.append(content).append("\n");
        }

        System.out.println("正在写入文件");
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            fw.write(sb1.toString());
            System.out.println("文件已成功写入：" + new File(filePath).getAbsolutePath());

        } catch (IOException e) {
            System.out.println("文件写入失败：" + e.getMessage());
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("正在读取文件");
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
            char[] buff = new char[1024];
            StringBuilder sb2 = new StringBuilder();
            int length;
            while ((length = fr.read(buff)) != -1) {
                sb2.append(buff, 0, length);
            }
            System.out.println("文件读取成功");
            System.out.println("文件内容为：" + sb2);
        } catch (IOException e) {
            System.out.println("文件读取失败：" + e.getMessage());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }
}