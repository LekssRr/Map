import org.example.HashMapCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashMapCustomTest {

    @Test
    void deleteTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        myMap.put(1, "True");
        boolean test = myMap.delete(1);
        Assertions.assertEquals(test, true);
    }
    @Test
    void putTest ()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        Assertions.assertEquals(myMap.put(1, "111"), null);
        myMap.put(1, "True");
        Assertions.assertEquals(myMap.put(1, "111"), "True");
    }
    @Test
    void getTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        myMap.put(1, "True");
        Assertions.assertEquals(myMap.put(1, "2222"), "True");
    }
    @Test
    <V, K>
    void entrySetTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        HashMap<Integer, String> set = new HashMap<>();

        for(int i = 0; i<=5; i++)
        {
            myMap.put(i, String.valueOf(i+2));
            set.put(i, String.valueOf(i+2));
        }
        Assertions.assertEquals(myMap.entrySet(),set.entrySet());
    }
    @Test
    <K>
    void keySetTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        Set<Integer> keySetMap = new HashSet<>();
        for(int i = 0; i<=1000; i++)
        {
            myMap.put(i, String.valueOf(i+2));
            keySetMap.add(i);
        }
        Assertions.assertEquals(myMap.keyset(), keySetMap);
    }
    @Test
    void valuesTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        Set<String> values = new HashSet<>();
        for(int i = 0; i<=1000; i++)
        {
            myMap.put(i, String.valueOf(i+2));
            values.add(String.valueOf(i+2));
        }
        Assertions.assertEquals(myMap.values(),values);
    }



}
