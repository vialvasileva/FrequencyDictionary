import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Counter {
    public void count(String source, String output) throws IOException {

        // создаем массивы для хранения количества букв
        int[] uppercaseCount = new int[26]; // массив для заглавных букв
        int[] lowercaseCount = new int[26]; // массив для строчных букв

        // читаем и считаем буквы в исходном файле
        try (FileReader reader = new FileReader(source)) {
            read(reader, uppercaseCount, lowercaseCount);
        }

        // записываем результаты в выходной файл
        try (FileWriter writer = new FileWriter(output)) {
            write(writer, uppercaseCount, 'A'); // заглавные буквы
            write(writer, lowercaseCount, 'a'); // строчные буквы
        }
    }


    // метод чтения и подсчета букв файле
    private void read(FileReader reader, int[] uppercaseCount, int[] lowercaseCount) throws IOException {
        int symbol = reader.read(); // числовой код считанного символа в файле

        // проходим по всем символам файла и записываем количества букв в массивы
        while (symbol != -1) {
            // считаем заглавные буквы
            if (symbol >= 'A' && symbol <= 'Z') {
                uppercaseCount[symbol - 'A']++;
            }
            // считаем строчные буквы
            else if (symbol >= 'a' && symbol <= 'z') {
                lowercaseCount[symbol - 'a']++;
            }

            symbol = reader.read(); // переход на следующий символ
        }
    }


    // метод записи количества букв файл
    private void write(FileWriter writer, int[] count, char register) throws IOException {
        for (int i = 0; i < count.length; i++) {
            char letter = (char) (register + i); // преобразовываем индекс в символ
            writer.write(letter + ": " + count[i] + System.lineSeparator());
        }
    }
}
