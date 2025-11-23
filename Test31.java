import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Test31 {
    public static void main(String[] args) throws Exception {
        BookManagement bm = new BookManagement();
        Scanner sc = new Scanner(System.in);
        String ISBN;
        String bookName;
        String author;
        while (true) {
            System.out.println("======欢迎来到图书管理系统======");
            System.out.println("请输入选项：");
            System.out.println(" 1. 添加图书 \n 2. 查看所有图书 \n 3. 借书 \n 4. 还书 \n 0. 退出系统");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("请输入IBSN编号：");
                    ISBN = sc.next();
                    System.out.println("请输入书名：");
                    bookName = sc.next();
                    System.out.println("请输入作者：");
                    author = sc.next();
                    bm.addBook(new Book(ISBN, bookName, author, 1));
                    break;
                case 2:
                    bm.displayBooks();
                    break;
                case 3:
                    System.out.println("请输入IBSN编号：");
                    ISBN = sc.next();
                    bm.borrowBook(ISBN);
                    break;
                case 4:
                    System.out.println("请输入IBSN编号：");
                    ISBN = sc.next();
                    bm.returnBook(ISBN);
                    break;
                case 0:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("输入不合法，请重新输入");
                    break;
            }
        }

    }
}

class Book {
    String ISBN;
    String bookName;
    String author;
    int count;

    public Book(String ISBN, String bookName, String author, int count) {
        this.ISBN = ISBN;
        this.bookName = bookName;
        this.author = author;
        this.count = count;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void getInfo() {
        System.out.println("ISBN编号: " + ISBN);
        System.out.println("书名:     " + bookName);
        System.out.println("作者:     " + author);
        System.out.println("库存:     " + count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return this.getISBN().equals(book.getISBN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN);
    }
}

class BookManagement {
    List<Book> books;
    String FILE_NAME = "books.txt";

    public BookManagement() {
        books = new ArrayList<>();
        loadFromFile();
    }

    public void addBook(Book book) throws IOException {
        if (!books.contains(book)) {
            books.add(book);
            System.out.println("添加成功");

        } else {
            System.out.println("图书已存在");
            return;
        }
        saveToFile();
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("暂无图书数据");
            return;
        }
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getISBN().compareTo(o2.getISBN());
            }
        });
        System.out.println("\n======所有图书信息 ======");
        System.out.println("共有 " + books.size() + " 本图书\n");

        for (int i = 0; i < books.size(); i++) {
            System.out.println("【图书 " + (i + 1) + "】");
            books.get(i).getInfo();
            System.out.println("─".repeat(30));
        }
    }

    public void borrowBook(String ISBN) throws IOException {
        if (books.isEmpty()) {
            System.out.println("暂无图书数据");
            return;
        }
        boolean find = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(ISBN)) {
                if (books.get(i).count > 0) {
                    books.get(i).count--;
                    System.out.println("借阅成功");
                    find = true;
                    saveToFile();
                } else {
                    System.out.println("库存不足");
                }
                break;
            }
        }
        if (!find) {
            System.out.println("找不到该图书");
        }
    }

    public void returnBook(String ISBN) throws IOException {
        boolean findFlag = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(ISBN)) {
                findFlag = true;
                books.get(i).count++;
                break;
            }
        }
        if (findFlag == false) {
            System.out.println("找不到该图书");
        } else {
            System.out.println("已归还，欢迎下次光临");
            saveToFile();
        }
    }

    public void saveToFile() throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(FILE_NAME);
            for (Book book : books) {
                String line = book.getISBN() + "," + book.getBookName() + "," + book.getAuthor() + "," + book.getCount()
                        + "\n";
                fw.write(line);
            }
            File file = new File(FILE_NAME);
            System.out.println("数据已保存到" + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("保存失败" + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        FileReader fr = null;
        try {
            fr = new FileReader(FILE_NAME);
            char[] buffer = new char[1024];
            int length;

            StringBuilder content = new StringBuilder();

            while ((length = fr.read(buffer)) != -1) {
                content.append(buffer, 0, length);
            }

            String allContent = content.toString();

            String[] lines = allContent.split("\n");

            books.clear();

            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length == 4) {
                    String isbn = parts[0];
                    String bookName = parts[1];
                    String author = parts[2];
                    int count = Integer.parseInt(parts[3]);

                    books.add(new Book(isbn, bookName, author, count));
                }
            }

            System.out.println("成功加载 " + books.size() + " 本图书");

        } catch (IOException e) {
            System.out.println("读取失败：" + e.getMessage());

        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}