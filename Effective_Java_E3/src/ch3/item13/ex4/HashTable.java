package ch3.item13.ex4;
/**
 * @author Roy Kim
 */

//Subject : Override clone judiciously
//Harm to the original object after cloning
public class HashTable implements Cloneable {
    private Entry[] buckets = null;

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        Entry(Object key, Object value, Entry next) {
            this.key   = key;
            this.value = value;
            this.next  = next;
        }

        // Recursively copy the linked list headed by this Entry
//        Entry deepCopy() {
//            return new Entry(key, value,
//                    next == null ? null : next.deepCopy());
//        }
    }

    // Broken - results in shared internal state!
    @Override public HashTable clone() {
        try {
            HashTable result = (HashTable) super.clone();
            result.buckets = buckets.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

//    @Override public HashTable clone() {
//        try {
//            HashTable result = (HashTable) super.clone();
//            result.buckets = new Entry[buckets.length];
//            for (int i = 0; i < buckets.length; i++)
//                if (buckets[i] != null)
//                    result.buckets[i] = buckets[i].deepCopy();
//            return result;
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }

    public static void main(String[] args) {
        HashTable clone1 = new HashTable();
        String[] str = new String[]{"1","2","3","4"};

        Entry first = new Entry("key", "value", null);
        Entry sec = new Entry("key", "value", first);

    }
}