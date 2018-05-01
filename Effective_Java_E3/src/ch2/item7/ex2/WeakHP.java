package ch2.item7.ex2;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

//Subject : Eliminate obsolete object references7
//Use of WeakHashMap, Strong reference, Soft reference, Weak reference
class WeakHP {

    public static void strongReference() {
        Integer prime = 1;
    }

    public static void softReference() {
        Integer prime = 1;
        SoftReference<Integer> soft = new SoftReference<Integer>(prime);
        prime = null;
    }

    public static void weakReference() {
        Integer prime = 1;
        WeakReference<Integer> weak = new WeakReference<Integer>(prime);
        prime = null;
    }

    public static void main(String[] args) throws InterruptedException {

        HashMap<Integer, String> hashMap = new HashMap<>();
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        HashMap<Integer, String> hashMapWithWeakInt = new HashMap<>();

        String StringFirst = "foo";
        String StringSecond = "foo_2";

        Integer hash1 = new Integer(1);
        Integer hash2 = new Integer(2);

        Integer weakhash1 = new Integer(1);
        Integer weakhash2 = new Integer(2);

        Integer prime1 = new Integer(1);
        Integer prime2 = new Integer(2);
        WeakReference<Integer> hashweakint1 = new WeakReference<>(prime1);
        WeakReference<Integer> hashweakint2 = new WeakReference<>(prime2);


        hashMap.put(hash1, StringFirst);
        hashMap.put(hash2, StringSecond);

        weakHashMap.put(weakhash1, StringFirst);
        weakHashMap.put(weakhash2, StringSecond);

        System.out.println("initial : " + hashMap);
        hash1 = null;
        System.out.println("null : " + hashMap);
        System.gc();
        Thread.sleep(500);
        System.out.println("after GC : " + hashMap);

        WeakReference<Integer> weak = new WeakReference<Integer>(hash1);
        hash1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println("weak final GC : " + hashMap);

        System.out.println("-------------------------");
        System.out.println("initial : " + weakHashMap);
        weakhash1 = null;
        System.out.println("null : " + weakHashMap);
        System.gc();
        Thread.sleep(500);
        System.out.println("after GC : " + weakHashMap);

        System.out.println("-------------------------");
        //System.out.println("initial : " + hashMapWithWeakInt);
        prime1 = null;
        //System.out.println("null : " + hashMapWithWeakInt);
        //System.gc();
        Thread.sleep(500);
        //System.out.println("after GC : " + hashMapWithWeakInt);
        System.out.println(hashweakint1.get());
        System.out.println(hashweakint2.get());

        System.out.println(prime1);
//        await().atMost(10, TimeUnit.SECONDS)
//                .until(() -> map.size() == 1);
//        await().atMost(10, TimeUnit.SECONDS)
//                .until(() -> map.containsKey(imageNameSecond));
    }
}