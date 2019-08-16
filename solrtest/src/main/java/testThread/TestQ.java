package testThread;

import org.apache.commons.collections.list.SynchronizedList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestQ {
    public static void test(String a){
        System.out.println(a);
    }


    public static void main(String[] args) {
        int i=0;
        for (test("a"); i < 2 ; test("b")) {
            test("d");
            i++;
            System.out.println(i);

            System.out.println("版本更新");

            System.out.println("二次更新");
        }

    }

    @Test
    public void testSynchonized(){
        ArrayList<String> objects = new ArrayList<String>();
        List<String> strings = Collections.synchronizedList(objects);
        strings.add("1");
        strings.add("0");
        strings.add("2");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        Collections.sort(strings);


        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
