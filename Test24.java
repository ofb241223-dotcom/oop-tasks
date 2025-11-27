import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Test24 {
    public static void main(String[] args) {
        Properties prop = new Properties();

        try {
            FileInputStream input = new FileInputStream("config.properties");
            prop.load(input);
            input.close();

            System.out.println("数据库主机: " + prop.getProperty("database.host"));
            System.out.println("数据库端口: " + prop.getProperty("database.port"));
            System.out.println("数据库用户名: " + prop.getProperty("database.username"));

        } catch (IOException e) {
            System.out.println("读取配置文件失败: " + e.getMessage());
        }
    }
}
