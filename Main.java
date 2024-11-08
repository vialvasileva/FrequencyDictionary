import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String readFile = getPath(scanner, true); // читаем и проверяем файл для чтения
        String writeFile = getPath(scanner, false); // читаем и проверяем файл для записи

        Counter counter = new Counter(); // создаем счетчик букв в файле

        // считаем английские буквы в файле с помощью метода count и обрабатываем ошибки
        try {
            counter.count(readFile, writeFile);
            System.out.println("Результаты записаны.");
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }


    // метод получения пути файла
    private static String getPath(Scanner scanner, boolean read) {

        // запрашиваем путь пока он не пройдет проверку
        while (true) {
            System.out.print("Введите путь файла для " + (read ? "чтения" : "записи") + ": ");
            String path = scanner.nextLine();

            try {
                checkFile(path, read);
                return path;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // метод проверки файла на ошибки
    private static void checkFile(String path, boolean read) throws FileNotFoundException, SecurityException {
        File file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException("Файл не существует.");
        }
        if (!file.canRead() & read) {
            throw new SecurityException("Нет разрешения на чтение файла.");
        }
        if (!file.canWrite()) {
            throw new SecurityException("Нет разрешения на запись в файл.");
        }
        if (file.isHidden()) {
            throw new SecurityException("Файл скрыт.");
        }
        if (!file.isFile()) {
            if (file.isDirectory()) {
                throw new IllegalArgumentException("Указанный путь является каталогом, а не файлом.");
            }
            throw new IllegalArgumentException("Указанный путь не является ни файлом, ни катологом.");
        }
    }
}