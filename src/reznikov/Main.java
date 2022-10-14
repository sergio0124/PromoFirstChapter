package reznikov;

import reznikov.list_implementation.implementations.ReznikovList;

import java.util.*;

public class Main<T> {
    int NUMBER_OF_ROWS = 1000000;

    List<Integer> generateArray() {
        return generateArray(NUMBER_OF_ROWS);
    }

    static List<Integer> generateArray(int size) {
        List<Integer> matrix = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int MIN_VALUE = -1000;
            int MAX_VALUE = 1000;
            int a = random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
            matrix.add(a);
        }
        return matrix;
    }


    public static void main(String[] args) {

        Main<Integer> main = new Main<>();
        Comparator<Integer> comparator = Comparator.comparing(Integer::intValue);
        System.out.println("size       ReznikovList  LinkedList  ArrayList");
        int size = 100;
        int step = 25000;
        while (size <= 1000000) {
            if(size == 1000 || size == 10000 || size == 50000 || size % 100000 == 0){
                System.out.println("size       ReznikovList  LinkedList  ArrayList");
            }
            System.out.println(main.sortComparison(size, comparator));
            if (size < 1000) {
                size += 100;
            } else if (size < 10000) {
                size += 1000;
            } else if (size < 50000) {
                size += 10000;
            } else {
                size += step;
            }
        }
    }


    boolean isSorted(List<T> list, Comparator<T> comparator) {
        var iterator = list.iterator();
        boolean sorted = true;
        T current = null;
        T previous;
        while (iterator.hasNext()) {
            previous = current;
            current = iterator.next();
            if (previous != null && comparator.compare(current, previous) < 0) {
                sorted = false;
                System.out.println(previous + "   " + current.toString());
            }
        }
        return sorted;
    }


    private void sortAndPrintTime(List<T> list, Comparator<T> comparator) {
        long time = System.currentTimeMillis();
        list.sort(comparator);
        System.out.println("Время выполнения в " + list.getClass().getSimpleName() +
                ": " + (System.currentTimeMillis() - time) + " миллисекунд.");
    }


    String sortComparison(int size, Comparator<Integer> comparator) {
        int NUMBER_COUNTS = 10;
        int NUMBER_REALIZATIONS = 3;

        List<Integer>[] array = new List[NUMBER_COUNTS];
        List<Integer>[] testLists = new List[NUMBER_REALIZATIONS];

        for (int i = 0; i < array.length; i++) {
            array[i] = generateArray(size);
        }
        testLists[0] = new ReznikovList<>();
        testLists[1] = new LinkedList<>();
        testLists[2] = new ArrayList<>();

        int[] tmp_array = new int[NUMBER_COUNTS];
        Main<Integer> main = new Main<>();
        StringBuilder builder = new StringBuilder();
        builder.append(size).append("        ");

        {
            for (List<Integer> testList : testLists) {
                boolean sorted = true;
                for (int i = 0; i < NUMBER_COUNTS; i++) {
                    testList.clear();
                    var list = array[i];
                    testList.addAll(list);
                    long time = System.currentTimeMillis();
                    testList.sort(comparator);
                    tmp_array[i] = (int) (System.currentTimeMillis() - time);
                    if (!main.isSorted(testList, comparator)) {
                        sorted = false;
                    }
                }
                builder.append(sorted
                        ? Arrays.stream(tmp_array).sum() / tmp_array.length + "            "
                        : "            error");
            }
        }
        return builder.toString();
    }
}