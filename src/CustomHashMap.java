/**
 * Created with IntelliJ IDEA.
 * User: wutianxiong
 * Date: 2018/3/16
 * Time: 20:35
 */
public class CustomHashMap<K,V> implements CustomMap<K,V>{

    /**
     * 默认的hash数组大小 必须为2的幂次方
     * */
    static final int DEFAULT_INIT_CAPACITY=1<<4;
    @Override
    public V put(K key,V value) {
        return null;
    }

    @Override
    public V get(K key) {

        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 这里参考了JDK的HashMap的hash函数的实现
     * */
    static final int hash(Object key){
        int h;
        return (key==null)?0:(h=key.hashCode())^(h>>>16);
    }
    /**
     * HashMap里的基本元素
     * */
    static class Entry<K,V> implements CustomMap.Entry<K,V>{

        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry() {

        }

        public Entry(K key,V value,Entry<K,V> next) {
            this.key=key;
            this.value=value;
            this.next=next;
        }
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }
}
