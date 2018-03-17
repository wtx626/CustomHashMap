/**
 * Created with IntelliJ IDEA.
 * User: wutianxiong
 * Date: 2018/3/16
 * Time: 20:35
 */
public class CustomHashMap<K, V> implements CustomMap<K, V> {

    /**
     * 默认的hash数组大小 必须为2的幂次方
     */
    static final int DEFAULT_INIT_CAPACITY = 1 << 4;

    /**
     * HashMap数组的负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = (float) 0.75;

    /**
     * 用来存放键值对元素，table大小必须为2的幂次方
     */
    Entry<K, V>[] table;

    /**
     * 设置的负载因子
     */
    private float loadFactor;

    /**
     * HashMap初始化之后的实际大小
     */
    private int initCapacity;

    /**
     * HashMap中已插入元素的个数
     */
    private int entryUse;

    public CustomHashMap() {
        this(DEFAULT_INIT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }


    /**
     * 自定义HashMap构造方法，传入大小，负载因子
     */
    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Illegal initial capacity: " +
                initialCapacity);

        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);

        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;

        this.loadFactor = loadFactor;
        this.initCapacity = capacity;
        //初始化table大小
        table = new Entry[capacity];
    }

    /**
     * 求出给出值在hash桶里的index
     * 正常解法是hash mod length
     * 这里通过&（length-1)得出的结果是一样的，但速度上会有10倍的提升
     */
    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    /**
     * 1 检查HashMap数组是否需要扩容，若是，转2，否则，转3
     * 2
     */

    @Override
    public V put(K key, V value) {
        V oldValue = null;

        if (entryUse > initCapacity * loadFactor) {//还没有达到负载因子
            resize(initCapacity << 1);
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);

        if (table[i] == null) {//该索引值没有entry元素
            table[i] = new Entry<K, V>(key, value, null);
            entryUse++;
        } else {//使用头插法插入到链表中
            Entry<K, V> p = table[i];
            Entry<K, V> tmp = p;
            table[i] = new Entry<>(key, value, tmp);
        }
        return null;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key), initCapacity);
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            if (key == e.getKey() || key.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 对旧的HashMap数组进行扩容，并将值重新散列到新的HashMap上
     */
    void resize(int newCapacity) {
        //复制旧HashMap
        Entry[] oldTable = table;
        Entry[] newTable = new Entry[newCapacity];
        //遍历oldTable
        for (Entry e : table) {
            while (e != null) {
                //先用next记下e的下一个元素，待会e.next要变化
                Entry next = e.next;
                //再次求元素的hashcode
                int hash = e.key == null ? 0 : hash(e.key);
                //求出在新HashMap上的index
                int i = indexFor(hash, newCapacity);
                //使用头插法
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
        initCapacity = newCapacity;
        table = newTable;
    }


    /**
     * 这里参考了JDK的HashMap的hash函数的实现
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * HashMap里的基本元素
     */
    static class Entry<K, V> implements CustomMap.Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {

        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
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
