import org.example.HashMapCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        for(int i = 0; i<=1000; i++)
        {
            myMap.put(i, String.valueOf(i+2));
        }
        Assertions.assertEquals(myMap.get(50), "52");
    }
    @Test
    <V, K>
    void entrySetTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        for(int i = 0; i<=60; i++)
        {
            myMap.put(i, String.valueOf(i+2));
        }
        Set<HashMapCustom.Entry<Integer, String>> set = myMap.entrySet();
        Assertions.assertEquals(myMap.entrySet(), set);
    }
    @Test
    <K>
    void keySetTest()
    {
        HashMapCustom<Integer, String> myMap = new HashMapCustom<>();
        Set<Integer> keySetMap = myMap.keyset();
        for(int i = 0; i<=1000; i++)
        {
            myMap.put(i, String.valueOf(i+2));
            keySetMap.add(i);
        }
        System.out.println(keySetMap);
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
