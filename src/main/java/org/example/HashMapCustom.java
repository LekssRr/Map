package org.example;

import java.util.*;

public class HashMapCustom <K, V> {
    //Массив
    private Entry<K,V>[] table;
    //Начальная емкость HashMapCustom
    private int capacity= 4;
    //Все пары ключ-значения находящиеся в CustomHashMap
    LinkedList<Entry<K, V>> entrySetMap = new LinkedList<>();

    //Set<Entry<K, V>> entrySet = new HashSet<>();
    /*
    Статический вложенный класс хронящий в себе:
    1) K key
    2) V value
    3) Entry<K,V> next
     */
    public static class Entry<K, V> implements Map.Entry<K,V> {
        K key;
        V value;
        Entry<K,V> next;

        public Entry(K key, V value, Entry<K,V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        //Возвращает ключ
        public K getKey()
        {
            return key;
        }
        //Возвращает значение
        public V getValue()
        {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }

        //Переопределяем метод toString так что бы он возвращал key + "=" + value
        @Override
        public final String toString() { return key + "=" + value; }

    }
    //Возвращает все пары ключ значения находящиеся в CustomHashMap
    public Set<Entry<K, V>> entrySet() {

        Set<Entry<K, V>> res = new HashSet<>();
        for (int i = 0; i<= entrySetMap.size()-1; i++)
        {
            res.add(entrySetMap.get(i));
        }
        return res;
    }
    //Возвращает все key находящиеся в CustomHashMap
    public Set<K> keyset()
    {
        Set<K> result = new HashSet<>();
        for(int i = 0; i<= entrySetMap.size()-1;i++)
        {
            result.add(entrySetMap.get(i).key);
        }
        return result;
    }
    //Возвращает все value находящиеся в CustomHashMap
    public Collection<V> values()
    {
        Set<V> result = new HashSet<>();
        for(int i = 0; i<= entrySetMap.size()-1;i++)
        {
            result.add(entrySetMap.get(i).value);
        }
        return result;
    }

    //Конструктор CustomMap
    public HashMapCustom(){
        table = new Entry[capacity];
    }

    /*
     Метод помещает пару ключ-значение в HashMapCustom.
     Если Map уже содержит сопоставление для ключа, старое значение заменяется.
     1) Проверяем newKey не null
     2) Получаем hash newKey
     3) Создаем новый Entry
     4) Добавляем newEntry в entrySetMap, добавляем его key в keySetMap, добавляем его value в valuesMap
     5) Проверяем если в таблице нет записей, сохранить запись там.
     6) Если записи есть то создаем previous и приравниваем его к null, создаем current и приравниваем его к table[hash]
     7) Запускаем цикл пока не найдем пустую ячейку
     8) Проверяем если previous==null элемент должен быть вставлен в первый блок и выходим из функции
     9) В ином случае newEntry.next=current.next, previous.next=newEntry и выходим из функции
     10) В цикле приравниваем previous к current, а current приравниваем current.next
     */
    public V put(K newKey, V data){
        if(newKey==null)
            return null;

        int hash=hash(newKey);

        Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);

        entrySetMap.add(newEntry);

        if(table[hash] == null) {
            table[hash] = newEntry;

        }else {
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null) {
                if(current.key.equals(newKey)) {
                    if(previous==null) {
                        V resultValue = null;
                        for(int i =0; i<=table.length-1; i++) {
                            if(table[hash]!= null) {
                                resultValue = current.value;
                                newEntry.next=current.next;
                                table[hash]=newEntry;
                                return resultValue;
                            }
                            else {
                                newEntry.next=current.next;
                                table[hash]=newEntry;
                                return null;
                            }
                        }
                    }
                    else {
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return null;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
        return null;
    }

    /*
     Возвращеает value по переданному в него key
     */
    public V get(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next;
            }
            return null;
        }
    }
    /*
     Метод удаляет пару ключ-значение в HashMapCustom.
     1) находим hash по deleteKey
     2) Если ячейка в table = null возвращаем false
     3) В обратном случае создаем две переменные previous = null, current = table[hash];
     4) Запускаем цикл пока current != null, если такое произошло то мы достигли последнего узла входа в контейнер.
     5) Проверяем если current.key.equals(deleteKey)
     6) Проверяем если previous==null то удаляем первый узел записи и записываем новое значение следующего элемента
        возвращаем true
     7) В ином случае previous.next=current.next возвращаем true
     8) Удаляем ключ из keySetMap и создаем переменные deleteValue = null, deleteEntry = null
     9) Находим в entrySetMap нужный нам объект и его значения, и удаляем их из valuesMap entrySetMap
     10) Приравниваем previous к current, current приравниваем к current.next
     11) Возвращаем false
     */
    public boolean delete(K deleteKey){

        int hash=hash(deleteKey);

        if(table[hash] == null){
            return false;
        }else{
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){
                if(current.key.equals(deleteKey)){
                    if(previous==null){
                        deleteSet(deleteKey);
                        table[hash]=table[hash].next;

                        return true;
                    }
                    else{
                        previous.next=current.next;
                        return true;
                    }
                }
                previous=current;
                current = current.next;
            }
            return false;
        }
    }
    private void deleteSet(K keyDelete)
    {
        V deleteValue = null;
        Entry deleteEntry = null;

        for(int j =0; j<=entrySetMap.size()-1;j++) {
            if (entrySetMap.get(j).key.equals(keyDelete)) {
                deleteValue = entrySetMap.get(j).value;
                deleteEntry = entrySetMap.get(j);
            }
        }
        entrySetMap.remove(deleteEntry);
    }
    /*
     Функция при помощи ключа определяем hash
     */
    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }
}
