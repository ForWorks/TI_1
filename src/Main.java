import com.sun.org.apache.bcel.internal.generic.SWITCH;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void method_1_coder(int height, char[] string) {

        // создание матрицы длиной в ключ и шириной в строку, которую надо закодировать
        char[][] matrix = new char[height][string.length];

        int vector = 1;   // вектор для смещения символов по вертикали
        int row = -1;   // индекс строки, в которую надо поместить символ

        // заполнение матрицы символами
        for (int i = 0; i < string.length; i++) {
            // если мы на первой строке - идем вниз
            if (row == 0)
                vector = 1;
            // если мы на последней строке - идем вверх
            if (row == height - 1)
                vector = -1;
            // рассчитываем строку и помещаем в нее символ
            row += vector;
            matrix[row][i] = string[i];
        }

        // формируем закодированную строку
        int pos = -1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < string.length; j++) {
                if (matrix[i][j] != 0) {
                    pos++;
                    string[pos] = matrix[i][j];
                }
            }
        }

        // вывод закодированной строки
        System.out.println(string);
    }

    public static void method_1_decoder(int height, char[] string) {

        // создание матрицы длиной в ключ и шириной в строку, которую надо закодировать
        char[][] matrix = new char[height][string.length];

        int vector = 1;   // вектор для смещения символов по вертикали
        int row = -1;   // индекс строки, в которую надо поместить символ

        // в матрице помечаем места для вставки символов
        for (int i = 0; i < string.length; i++) {
            // если мы на последней строке - идем вверх
            if (row == 0)
                vector = 1;
            // если мы на последней строке - идем вверх
            if (row == height - 1)
                vector = -1;
            // рассчитываем ячейку и помечаем строку
            row += vector;
            matrix[row][i] = ' ';
        }

        // расставляем символы в матрице
        int pos = -1;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < string.length; j++) {
                if (matrix[i][j] == ' ') {
                    pos++;
                    matrix[i][j] = string[pos];
                }
            }
        }

        vector = 1;   // вектор для смещения символов по вертикали
        row = -1;   // индекс строки, в которую надо поместить символ

        // формируем раскодированную строку
        for (int i = 0; i < string.length; i++) {
            if (row == 0)
                vector = 1;
            if (row == height - 1)
                vector = -1;
            row += vector;
            string[i] = matrix[row][i];
        }

        // вывод раскодированной строки
        System.out.println(string);
    }

    public static void method_2_coder(char[] key, char[] string) {
        // определяем длину матрицы
        int height = (int)Math.ceil((double)string.length / key.length);
        char matrix[][] = new char[height][key.length];
        int row = 0;
        int column = -1;
        //заполняем матрицу символами строки
        for(int i = 0; i < string.length; i++){
            if(column == key.length - 1) {
                row++;
                column = -1;
            }
            column++;
            matrix[row][column] = string[i];
        }
        // попорядку выводим столбцы таблицы
        for(char symbol = 'А'; symbol <= 'Я'; symbol++) {
            for(int i = 0; i < key.length; i++) {
                if(key[i] == symbol) {
                    for(int j = 0; j < height; j++) {
                        System.out.print(matrix[j][i]);
                    }
                }
            }
        }
        System.out.println();
    }

    public static void method_2_decoder(char[] key, char[] string) {
        //определяем длину таблицы
        int height = (int)Math.ceil((double)string.length / key.length);
        char matrix[][] = new char[height][key.length];
        // количество букв на последней строке
        int remainder = string.length % key.length;
        int pos = -1;

        //заполняем таблицу в зависимости от количества букв в ее столбцах
        if(remainder == 0) {
            for(char symbol = 'A'; symbol <= 'Я'; symbol++) {
                for(int i = 0; i < key.length; i++) {
                    if(key[i] == symbol) {
                        for(int j = 0; j < height; j++) {
                            pos++;
                            matrix[j][i] = string[pos];
                        }
                    }
                }
            }
        }
        else {
            for(char symbol = 'A'; symbol <= 'Я'; symbol++) {
                for(int i = 0; i < key.length; i++) {
                    if(key[i] == symbol) {
                        if(i > remainder - 1) {
                            for (int j = 0; j < height - 1; j++) {
                                pos++;
                                matrix[j][i] = string[pos];
                            }
                        }
                        else {
                            for (int j = 0; j < height; j++) {
                                pos++;
                                matrix[j][i] = string[pos];
                            }
                        }
                    }
                }
            }
        }
        // выводим таблицу
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < key.length; j++) {
                System.out.print(matrix[i][j]);
            }
        }
        System.out.println();
    }

    public static void method_4_coder(char[] key, char[] string) {
        // создаем таблицу русских букв
        char matrix[][] = new char[32][32];
        for(int i = 0; i < 32; i++) {
            for(int j = 0; j < 32; j++) {
                matrix[i][j] = (char)(j + 1040 + i);
                if(matrix[i][j] > 'Я')
                    matrix[i][j] = (char)((int)matrix[i][j] - 32) ;
            }
        }
        int pos = -1;
        // кодируем фразу при помощи таблицы
        for(int i = 0; i < string.length; i++) {
            if(pos == key.length - 1)
                pos = -1;
            pos++;
            string[i] = matrix[(int)key[pos] - 1040][(int)string[i] - 1040];
        }
        System.out.println(string);
    }

    public static void method_4_decoder(char[] key, char[] string) {
        // создаем таблицу русских букв
        char matrix[][] = new char[32][32];
        for(int i = 0; i < 32; i++) {
            for(int j = 0; j < 32; j++) {
                matrix[i][j] = (char)(j + 1040 + i);
                if(matrix[i][j] > 'Я')
                    matrix[i][j] = (char)((int)matrix[i][j] - 32) ;
            }
        }
        int pos = 0;
        // декодируем фразу при помощи таблицы
        for(int i = 0; i < string.length; i++) {
            for(int j = 0; j < 32; j++) {
                if(pos == key.length)
                    pos = 0;
                if(matrix[j][0] == key[pos]) {
                    for(int h = 0; h < 32; h++) {
                        if(matrix[j][h] == string[i]) {
                            string[i] = matrix[0][h];
                            break;
                        }
                    }
                    pos++;
                    break;
                }
            }
        }
        System.out.println(string);
    }

    // процедура поворота матрицы на 90 градусов
    public static int[][] rotateClockwise(int[][] mas) {
        int SIDE = mas.length;
        int[][] rezult = new int[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                rezult[i][j] = mas[SIDE - j - 1][i];
            }
        }
        return rezult;
    }

    public static void method_3_coder(char[] string) {
        int size = 0;
        for(int i = 1; i <= 10; i++) {
            if(Math.pow((double)i, 2.0) * 4 >= string.length) {
                size = i;
                break;
            }
        }
        char[][] matrix = new char[size * 2][size * 2];
        int[][] key = new int[size * 2][size * 2];
        int number = 1;
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                matrix[i][j] = '@';
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[i][j] = number;
                number++;
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[j][size * 2 - 1 - i] = key[i][j];
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[size * 2 - 1 - i][size * 2 - 1 - j] = key[i][j];
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                key[size * 2 - 1 - j][i] = key[i][j];
            }
        }
        Random random = new Random();
        int row;
        int column;
        for(int i = 1; i <= size * size;) {
            column = random.nextInt(size * 2);
            row = random.nextInt(size * 2);
            if(key[row][column] == i) {
                key[row][column] = 0;
                i++;
            }
        }
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                if(key[i][j] != 0)
                    key[i][j] = 1;
            }
        }
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                System.out.print(key[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        int count = -1;
        do {
            for (int j = 0; j < size * 2; j++) {
                for (int h = 0; h < size * 2; h++) {
                    if (count == string.length - 1)
                        break;
                    if (key[j][h] == 0) {
                        count++;
                        matrix[j][h] = string[count];
                    }
                }
                if (count == string.length - 1)
                    break;
            }
            key = rotateClockwise(key);
        } while(count < string.length - 1) ;
        System.out.println();
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                System.out.print(matrix[i][j]);
            }
        }
        System.out.println();
    }

    public static void method_3_decoder(char[] string) {
        Scanner scan = new Scanner(System.in);
        int size = 0;
        for(int i = 1; i <= 10; i++) {
            if(Math.pow((double)i, 2.0) * 4 >= string.length) {
                size = i;
                break;
            }
        }
        char[][] matrix = new char[size * 2][size * 2];
        int[][] key = new int[size * 2][size * 2];
        System.out.println("Введите ключ для этой фразы:");
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                key[i][j] = scan.nextInt();
            }
        }
        int count = -1;
        for(int i = 0; i < size * 2; i++) {
            for(int j = 0; j < size * 2; j++) {
                count++;
                matrix[i][j] = string[count];
            }
        }
        count = 0;
        System.out.println();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < size * 2; j++) {
                for(int h = 0; h < size * 2; h++) {
                    if(key[j][h] == 0) {
                        if(matrix[j][h] != '@') {
                            count++;
                            System.out.print(matrix[j][h]);
                        }
                        if(count == string.length)
                            break;
                    }
                }
                if(count == string.length)
                    break;
            }
            key = rotateClockwise(key);
        }
        System.out.println();
    }

    public static void menu() {
        System.out.println("Метод железнодорожной изгороди(1)");
        System.out.println("Столбцовый метод(2)");
        System.out.println("Метод поворачивающейся решетки(3)");
        System.out.println("Метод Виженера(4)");
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
//        while(value < 1 || value > 4) {
//            System.out.print("Выберите правильное число: ");
//            value = scan.nextInt();
//        }
        System.out.println("Зашифровать(1)");
        System.out.println("Расшифровать(2)");
        int val = scan.nextInt();
        int height;
        char[] str;
        char[] key;
        switch (value){
            case 1: {
                System.out.print("Введите высоту изгороди: ");
                height = scan.nextInt();
                scan.nextLine();
                if(val == 1) {
                    System.out.print("Введите строку для шифрования: ");
                    str = scan.nextLine().toCharArray();
                    method_1_coder(height, str);
                }
                else if(val == 2) {
                    System.out.print("Введите строку для расшифровки: ");
                    str = scan.nextLine().toCharArray();
                    method_1_decoder(height, str);
                }

            }break;
            case 2: {
                System.out.print("Введите ключ: ");
                scan.nextLine();
                key = scan.nextLine().toCharArray();
                if(val == 1) {
                    System.out.print("Введите строку для шифрования: ");
                    str = scan.nextLine().toCharArray();
                    method_2_coder(key, str);
                }
                else if(val == 2) {
                    System.out.print("Введите строку для расшифровки: ");
                    str = scan.nextLine().toCharArray();
                    method_2_decoder(key, str);
                }

            }break;
            case 3: {
                if(val == 1) {
                    System.out.print("Введите строку для шифрования: ");
                    scan.nextLine();
                    str = scan.nextLine().toCharArray();
                    method_3_coder(str);
                }
                else if(val == 2) {
                    System.out.print("Введите строку для расшифровки: ");
                    scan.nextLine();
                    str = scan.nextLine().toCharArray();
                    method_3_decoder(str);
                }
            }break;
            case 4: {
                System.out.print("Введите ключ: ");
                scan.nextLine();
                key = scan.nextLine().toCharArray();
                if(val == 1) {
                    System.out.print("Введите строку для шифрования: ");
                    str = scan.nextLine().toCharArray();
                    method_4_coder(key, str);
                }
                else if(val == 2) {
                    System.out.print("Введите строку для расшифровки: ");
                    str = scan.nextLine().toCharArray();
                    method_4_decoder(key, str);
                }

            }break;
            default:
                System.out.println("Error");
        }
    }


    public static void main(String[] args) {
        while(true) {
            menu();
        }
    }
}
