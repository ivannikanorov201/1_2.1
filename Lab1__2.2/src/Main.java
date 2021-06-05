import java.util.Scanner;

public class Main {

    public static River [] setRiverArr(int k) { // Ввод массива рек
        Scanner sc=new Scanner(System.in,"cp1251");
        River[] rivers = new River[k];
        System.out.println("Введите информацию о каждой реке: ");
        for (int i = 0; i < rivers.length; i++) {
            rivers[i] = new River(); // получена ссылка на i-тый элемент
            // Присвоение значений полям
            System.out.print("Введите название " + (i + 1) + " реки => ");
            rivers[i].name = sc.nextLine();
            System.out.print("Введите её место расположения => ");
            rivers[i].location = sc.nextLine();
            System.out.print("Введите её длину => ");
            rivers[i].length = sc.nextInt();
            sc.nextLine(); //очистка буфера
        } // end for
        return rivers;
    }

    public static void showTitle() { // Шапка таблицы
        System.out.format("%15s%25s%10s\n", "Название", "Место расположения", "Длина");
    }

    public static void showRiver(River r) { // Информаия об одной реке
        System.out.format("%15s%25s%10d\n", r.name, r.location, r.length);
    }

    public static void showArray(River [] rivers) { // Вывод массива
        for (River r : rivers) {
            showRiver(r);
        }
    }

    public static int numMin(River [] rivers) { // Номер самой короткой реки
        int p = 0;
        for (int i = 1; i < rivers.length; i++) {
            if (rivers[i].length < rivers[p].length) {
                p = i;
            }
        }
        return p;
    }

    public static double avgLength(River [] rivers) { //Средняя длина
        double s = 0;
        for (int i = 0; i < rivers.length; i++) {
            s += rivers[i].length;
        }
        s /= rivers.length;
        return s;
    }

    public static River [] Bigger(River [] rivers) { //Реки с длиной больше средней
        double s = avgLength(rivers);
        int k = 0;
        for (int i = 0; i < rivers.length; i++) {
            if (rivers[i].length > s) {
                k++;
            }
        }
        if (k != 0) {
            River [] rs = new River[k];
            int n = -1;
            for (int i = 0; i < rivers.length; i++) {
                if (rivers[i].length > s) {
                    rs[++n] = rivers[i];
                }
            }
            return rs;
        } else return null;
    }

    public static void sortName(River [] rivers) { //Сортировка по названию
        for (int i = 0; i < rivers.length - 1; i++) {
            for (int j = i + 1; j < rivers.length; j++) {
                if (rivers[i].name.compareTo(rivers[j].name) > 0) {
                    River r = rivers[i];
                    rivers[i] = rivers[j];
                    rivers[j] = r;
                }
            }
        }
    }

    public static River findForName(River rivers[], String name) { //Поиск по названию реки
        int n = -1;  // -1 – река с искомым названием отсутствует
        for (int i = 0; i < rivers.length; i++) {
            if (name.equals(rivers[i].name)) {
                n = i;
            }
        }
        if (n != -1) {
            return rivers[n];
        } else return null;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in,"cp1251");
        System.out.print("Введите количество рек => ");
        int n = sc.nextInt();
        sc.nextLine(); // очистка буфера после ввода числа
        River[] rivers = setRiverArr(n); //вводим информаию о реках

        // Вывод информации о реках
        System.out.println("\nРеки:");
        showTitle();
        showArray(rivers);

        //Определяем самую короткую реку
        System.out.println("\nСамая короткая река:");
        showTitle();
        showRiver(rivers[numMin(rivers)]);

        //Информация о реках, с длиной больше средней
        System.out.println("\nСредняя длина рек: " + avgLength(rivers));
        System.out.println("\nРеки с длиной больше средней:");
        showTitle();
        showArray(Bigger(rivers));

        //Упорядочивание списка рек по названиям в алфавитном порядке
        sortName(rivers);
        System.out.println("\nРеки упорядоченные по названиям:");
        showTitle();
        showArray(rivers);

        //Поиск по названию реки
        System.out.print("Введите название искомой реки => ");
        String name = sc.nextLine();
        River r = findForName(rivers, name);
        if (r != null) {
            System.out.println("\nТакая река есть в списке:");
            showTitle();
            showRiver(r);
        }
        else {
            System.out.println("Такой реки нет в списке");
        }
    }
}
