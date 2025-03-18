package utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;


public class MyArrayList <T> implements MyList<T> {
    private T[] array; // null
    private int cursor; // по умолчанию он получит значение = 0

    // методы, расширяющие функционал массива
    @SuppressWarnings("unchecked") // Подавляю предупреждения компилятора о непроверяемом приведении типа
    public MyArrayList() {
        // Стирание типов. Невозможно создать объект типа Т
        // this.array = new T[10];
        this.array = (T[]) new Object[10];

    }

    @SuppressWarnings("unchecked")
    public MyArrayList(T[] array) {
        if (array == null || array.length == 0) {
            this.array = (T[]) new Object[10];
        } else {
            this.array = (T[]) new Object[array.length * 2];
            // int numbers может принять ссылку на массив int []

            addAll(array);
        }
    }

    // Метод динамического расширения массива
    private void expandArray() {
        System.out.println("Расширяем внутренний массив! Курсор равен " + cursor);
        /*
        1. Создать новый массив бОльшего размера (в 2 раза больше)
        2. Переписать в новый массив все значения до курсора из старого
        3. Перебросить ссылку
         */

        // 1.
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length * 2];
        // 2.
        for (int i = 0; i < cursor; i++) {
            newArray[i] = array[i];
        }
        // 3. Перебрасываем ссылку. Переменная array  хранит ссылку на новый массив
        array = newArray;

    }

    // Добавление в массив нескольких элементов


    // Возвращает строковое представление массива
    // [5, 20, 45]
    public String toString() {

        if (cursor == 0) return "[]";

        String result = "[";
        for (int i = 0; i < cursor; i++) {
            result += array[i] + (i < cursor - 1 ? ", " : "]");
        }

        return result;

    }

    @Override
    public void add(T value) {
        // Проверка! Есть ли свобоное место во внутреннем массиве
        // Если нет - нужно добавить место
        if (cursor == array.length) {
            // Расширить внутренний массив перед добавлением нового значения
            expandArray();
        }
        array[cursor] = value;
        cursor++;
    }

    @Override
    public void addAll(T... values) {
//        System.out.println("Принял несколько int: " + values.length);
//        System.out.println(Arrays.toString(values));
//        System.out.println("У каждого инта есть индекс как в массиве: " + values[0]);

        // Перебираю все значения. ДЛя каждого вызываю метод add()
        for (int i = 0; i < values.length; i++) {
            add(values[i]);
        }
    }

    // Текущее кол-во элементов в массиве
    public int size() {
        return cursor;
    }

    // Возвращает значение по индексу
    public T get(int index) {
        // Проконтролировать входящий индекс

        if (index >= 0 && index < cursor) {
            return array[index];
        }
        return null;
    }

    @Override
    public void set(int index, T value) {
        if (index >= 0 && index < cursor) {
            //если индекс корректный присваиваем новое значение
            array[index] = value;
        }
       // если нет, действий не требуется

    }

    // Удалить элемент по индексу. Вернуть старое значение
    public T remove(int index) {
        /*
        1.Проверка индекса на валидность
        2. Удалить значение по индексу
        3. Передвинуть курсор, так как кол-во элементов уменьшилось
        4. Вернуть старое значение
         */
        if (index >= 0 && index < cursor) {
            // Логика удаления
            T value = array[index]; // Запомнить старое значение

            // Перебираю элементы начиная с индекса и перезаписываю значение из ячейки справа
            for (int i = index; i < cursor - 1; i++) { // Граница перебора индексов
                array[i] = array[i + 1];
            }
            cursor--;
            return value; // Вернуть старое значение
        } else {
            // Индекс невалидный
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return cursor == 0;
    }

    // Поиск по значению. Возвращать индекс.
    // [1, 100, 5, 24, 0] -> Найди число indexOf(5) = 2, indexOf(50) = -1; (несуществующий индекс)

    public int indexOf(T value) {
        // Перебираю все значимые элементы
        // Если элемент равен искомому - вернтуь индекс такого элемента
        // Если перебрал все элементы - не нашел совпадений - вернуть -1

        for (int i = 0; i < cursor; i++) {

            // null - безопасное сравнение
            if (Objects.equals(array[i], value)) {
            // if (array[i].equals(value)) {
                // Значения совпали. Возвращаю индекс
                return i;
            }
        }
        // Если сюда мы попадем, если ни одно значение в массиве не совпало
        return -1;
    }
    // Удаление элемента по значению. Возвращает boolean

    // Индекс последнего вхождения
    // [1, 100. 5, 100, 24, 0, 100] -> lastIndexOf(100) -> 6 (запоминаем значение, перезапоминаем, если нашли/ не нашли)

    public int lastIndexOf(T value) {
        for (int i = cursor - 1; i >= 0; i--) {
        if (Objects.equals(array[i], value)) return i;
//          if (array[i].equals(value)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;

    }
        /*int index = -1;
        for (int i = 0; i < cursor; i--) {
            if (array[i] == value) {
                //значения совпали, обновляю переменную index
                index = i;
            }
        }
        return index;
    }*/


    // Удаление по значению
    /*
    1. Есть ли элемент с таким значением - indexOf
    2. Если элемента нет, ничего не делаем,
    не пытаемся удалить, возвращаем false
    3. Если элемент найден - удалить - вернуть true
     */

    @Override
    // Удаление элемента по значению
    public boolean remove(T value) {
        /*
        1. Есть ли элемент с таким значением - indexOf
        2. Если элемента нет - ничего не  пытаемся удалить - возвращаем false
        3. Если найден - удалить и адем вернуть true
         */
        int index = indexOf(value);
        if (index < 0) return false;
        // В эту строчку кода попадем, только при index => 0
        remove(index);
        return true;
    }


    // int[] findAllValues(int value) {
    // todo homework optional***


// массив, состоящий из элементов магического массива


    @Override
    // Массив, состоящий из элементов магического массива
    public T[] toArray() {
        /*
        1. Создать массив размерность cursor (кол-во значимых элементов)
        2. Пройтись по внутреннему массиву и скопировать все элементы в новый до cursor
        3. Вернуть ссылку на новый массив
         */

//        // Todo здесь будет ошибка
//       T[] result = (T[]) new Object[cursor];
//       T[] res = new T[11]; // нельзя создать объект
//      T obj = new T();

    // Взять какой-то объект из моего массив
    // и узнать с помощью рефлексии тип этого объекта.
    // и потом создать массив этого типа

        if (cursor == 0) return null;

        Class<T> clazz = (Class<T>) array[0].getClass();
        System.out.println("clazz: " + clazz);

        // Создаю массив того же типа, что и 0-ой элемент
        T[] result = (T[]) Array.newInstance(clazz, cursor);

        for (int i = 0; i < cursor; i++) {
            result[i] = array[i];
        }
        return result;
    }
// Невозможно вернуть объект типа интерфейса. Если тип возвращаемого значения (или параметра метода) имеет тип интерфейс,
// это значит, что я должен вернуть объект класса, который имплементировал этот интерфейс
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        int currentIndex = 0; // типа курсор

        @Override
        public boolean hasNext() {
            return currentIndex < cursor;
        }
        @Override
        public T next() {
            return array[currentIndex++];
//            T value = array[currentIndex];
//            cursor++;
//            return value;
        }

    } // End class MyIterator

    public void test() {
        System.out.println(Arrays.toString(array));
    }
}




// [5, 20] без ноликов
/*
1. Добавлять в массив элемент (не думаю об индексах, размере массива)
2. Динамическое изменение размера внутреннего массива
3. Возвратить строковое представление массива
(все элементы массива в одной строке)
4. Добавить в массив сразу несколько значений ++
5. Текущее кол-во элементов в массиве
6. Возвращает значение по индексу
7. Удалить элемент по индексу (есть индекс - удалить элемент из массива) Возвращает старое значение.
8. Удаление по значению из массива. Возвращал boolean. Если удалил - true, если нет - false
9. Поиск по значению. Возвращать индекс первого вхождения элемента
10. Индекс последнего вхождения
11. Конструктор, принимающий обычный массив. Создать магический массив с элементами из этого массива
12. Написать метод, который вернет массив, состоящий из элементов магческого массива

 */