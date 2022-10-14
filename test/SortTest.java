import org.testng.annotations.Test;
import reznikov.list_implementation.implementations.ReznikovList;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

public class SortTest {

    List<String> generateRandomStringList() {
        int MIN_VALUE = -1000;
        int MAX_VALUE = 1000;
        List<String> result = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 200; i++) {
            result.add(String.valueOf(random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE));
        }
        return result;
    }


    @Test
    void mergeTest() {
        var list = generateRandomStringList();
        ReznikovList<String> reznikovList = new ReznikovList<>(list);
        LinkedList<String> linkedList = new LinkedList<>(list);
        assertEquals(reznikovList.toString(), linkedList.toString());
        Comparator<String> c = Comparator.comparing(String::toString);
        reznikovList.sort(c);
        linkedList.sort(c);
        assertEquals(linkedList.toString(), reznikovList.toString());
    }

}
