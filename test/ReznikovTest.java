import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reznikov.list_implementation.implementations.ReznikovList;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Test(testName = "ReznikovList testing")
public class ReznikovTest {

    ReznikovList<String> reznikovList = new ReznikovList<>();
    LinkedList<String> arrayList = new LinkedList<>();

    @BeforeClass
    void init() {
        reznikovList = (ReznikovList<String>) getReznikovStringList();
        arrayList = (LinkedList<String>) getDefaultStringList();
    }

    List<String> getDefaultStringList() {
        List<String> list = new LinkedList<>();
        list.add("15");
        list.add("17");
        list.add("17");
        list.add("11");
        list.add("13");
        list.add("15");
        list.add("12");
        list.add("14");
        list.add("13");
        list.add("15");
        list.add("16");
        list.add("17");
        list.add("12");
        list.add("13");
        list.add("12");
        return list;
    }

    List<String> getReznikovStringList() {
        List<String> list = new ReznikovList<>();
        list.add("15");
        list.add("17");
        list.add("17");
        list.add("11");
        list.add("13");
        list.add("15");
        list.add("12");
        list.add("14");
        list.add("13");
        list.add("15");
        list.add("16");
        list.add("17");
        list.add("12");
        list.add("13");
        list.add("12");
        return list;
    }

    List<Object> getDefaultList() {
        List<Object> list = new LinkedList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        list.add("17");
        return list;
    }

    List<Object> getReznikovList() {
        List<Object> list = new ReznikovList<>();
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        list.add("17");
        return list;
    }

    @Test
    void addMethod() {
        getDefaultList();
    }

    @Test
    void toListMethod() {
        assertEquals(getDefaultList().toString(), getReznikovList().toString());
    }

    @Test
    void sizeMethod() {
        assertEquals(getDefaultList().size(), getReznikovList().size());
    }

    @Test
    void isEmptyMethod() {
        assertEquals(getDefaultList().isEmpty(), getReznikovList().isEmpty());
        assertTrue(new ReznikovList<>().isEmpty());
    }

    @Test
    void containsMethod() {
        assertEquals(getDefaultList().contains("12"), getReznikovList().contains("12"));
        assertEquals(getDefaultList().contains("fff"), getReznikovList().contains("fff"));
    }


    @Test
    void iteratorsComparing() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        iteratorsRemoveEven(def.iterator());
        iteratorsRemoveEven(rez.iterator());
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
    }

    String iteratorsPrint(Iterator<Object> iterator) {
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.toString();
    }

    void iteratorsRemoveEven(Iterator<Object> iterator) {
        int i = 0;
        while (iterator.hasNext()) {
            if (i != 0 && i % 2 == 0) {
                iterator.remove();
            } else {
                iterator.next();
            }
            i++;
        }
    }


    @Test
    void toArrayMethod() {
        assertEquals(Arrays.toString(getDefaultList().toArray()), Arrays.toString(getReznikovList().toArray()));
    }

    @Test
    void toArrayTMethod() {
        Object[] objs = new Object[3];
        assertEquals(Arrays.toString(getDefaultList().toArray(objs.clone())),
                Arrays.toString(getReznikovList().toArray(objs.clone())));
    }

    @Test
    void removeMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        removeWork(def);
        removeWork(rez);
        assertEquals(def.toString(), rez.toString());
    }

    void removeWork(List<Object> list) {
        AtomicInteger index = new AtomicInteger();
        list.removeIf(rec -> index.getAndIncrement() % 2 == 0);
    }

    @Test
    void addAllMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        def.addAll(getReznikovList());
        rez.addAll(getReznikovList());
        assertEquals(def.toString(), rez.toString());
    }

    @Test
    void addAllIndexMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        def.addAll(4, getReznikovList());
        rez.addAll(4, getReznikovList());
        assertEquals(def.toString(), rez.toString());
    }

    @Test
    void clearMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        def.clear();
        rez.clear();
        assertEquals(def.toString(), rez.toString());
    }

    @Test
    void getMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = random.nextInt(rez.size() - 1);
        }

        assertEquals(makeString(rez, array), makeString(def, array));
    }

    String makeString(List<Object> list, int[] indexes) {
        StringBuilder builder = new StringBuilder();
        for (int index : indexes) {
            builder.append(list.get(index));
        }
        return builder.toString();
    }

    @Test
    void setMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = random.nextInt(rez.size() - 1);
        }
        setting(def, array);
        setting(rez, array);
        assertEquals(def.toString(), rez.toString());
    }

    void setting(List<Object> list, int[] array) {
        for (int i = 0; i < array.length; i++) {
            list.set((Math.floorMod(i, list.size())), array[i]);
        }
    }

    @Test
    void addIMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        var array = generateArray(10);
        addIWork(rez, array);
        addIWork(def, array);
        assertEquals(def.toString(), rez.toString());
    }

    void addIWork(List<Object> list, int[] array) {
        for (int i = 0; i < array.length; i++) {
            list.add((Math.floorMod(i, list.size())), array[i]);
        }
    }

    int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            Random random = new Random();
            array[i] = random.nextInt();
        }
        return array;
    }

    @Test
    void removeIMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(iteratorsPrint(def.iterator()), iteratorsPrint(rez.iterator()));
        var str1 = removeIWork(def);
        var str2 = removeIWork(rez);
        assertEquals(str1, str2);
        assertEquals(def.toString(), rez.toString());
    }

    String removeIWork(List<Object> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                Object rem = list.remove(i);
                if (rem != null) {
                    builder.append(rem);
                }
            }
        }
        return builder.toString();
    }

    @Test
    void indexOfMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        int[] array = generateArray(10);
        var str1 = indexOfWork(def, array);
        var str2 = indexOfWork(rez, array);
        assertEquals(str1, str2);
        assertEquals(def.toString(), rez.toString());
    }

    String indexOfWork(List<Object> list, int[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length - 1; i++) {
            builder.append(list.indexOf(list.get(Math.abs(array[i] % array[i + 1]) % list.size())));
        }
        return builder.toString();
    }

    @Test
    void listIteratorMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        var str1 = listIteratorWork(def.listIterator());
        var str2 = listIteratorWork(rez.listIterator());
        assertEquals(str1, str2);
        assertEquals(def.toString(), rez.toString());
    }

    String listIteratorWork(ListIterator<Object> iterator) {
        StringBuilder builder = new StringBuilder();
        int[] array = new int[]{3, 5, 1};
        int i = 0;
        while (iterator.hasNext()) {
            var obj = iterator.next();
            int finalI = i;
            if (Arrays.stream(array).filter((rec) -> rec == finalI).count() > 0) {
                iterator.remove();
            } else {
                iterator.add(String.valueOf(finalI));
            }
            builder.append(iterator.nextIndex()).append(obj.toString()).append(iterator.previousIndex());
            i++;
        }

        i = 0;
        while (iterator.hasPrevious()) {
            var obj = iterator.previous();
            int finalI = i;
            if (Arrays.stream(array).filter((rec) -> rec == finalI).count() > 0) {
                iterator.remove();
            }
            builder.append(iterator.nextIndex()).append(obj.toString()).append(iterator.previousIndex());
            i++;
        }

        return builder.toString();
    }


    @Test
    void sorting() {
        assertEquals(reznikovList.toString(), arrayList.toString());
        Comparator<String> c = Comparator.comparing(String::toString);
        reznikovList.sort(c);
        arrayList.sort(c);
        assertEquals(arrayList.toString(), reznikovList.toString());
    }


    @Test
    void subListMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        assertEquals(rez.subList(1, 4).toString(), def.subList(1, 4).toString());
    }

    @Test
    void retainAllMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        var subrez = rez.subList(1, 4);
        var subdef = new LinkedList<>(def.subList(1, 4));
        rez.retainAll(subrez);
        def.retainAll(subdef);
        assertEquals(rez.toString(), def.toString());
    }

    @Test
    void removeAllMethod() {
        var rez = getReznikovList();
        var def = getDefaultList();
        var subrez = rez.subList(1, 4);
        var subdef = new LinkedList<>(def.subList(1, 4));
        rez.removeAll(subrez);
        def.removeAll(subdef);
        assertEquals(rez.toString(), def.toString());
    }

    @Test
    void containsAll() {
        var rez = getReznikovList();
        var def = getDefaultList();
        var subrez = rez.subList(1, 4);
        var subdef = new LinkedList<>(def.subList(1, 4));
        assertEquals(def.containsAll(subdef), rez.containsAll(subrez));
    }
}
