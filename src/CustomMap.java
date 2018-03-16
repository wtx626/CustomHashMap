/**
 * Created with IntelliJ IDEA.
 * User: wutianxiong
 * Date: 2018/3/16
 * Time: 20:34
 *
 */

/**
 * K V为泛型化类型，用来泛化Map中的key和value
 * */
public interface CustomMap<K,V> {
    /**
     *
     * 向map中添加一个键值对<key,value></>
     *
     * @param k key
     * @param v value
     * */
     V put(K k,V v);
     /**
      *
      * 根据键值获取value
      *
      * @param k key
      * @return value
      * */
     V get(K k);
     /**
      *
      * 返回Map的大小
      * */
     int size();
     interface Entry<K,V>{
         /**
          * 返回entry相关联的key值.
          *
          */
         K getKey();

         V getValue();

         V setValue(V value);
     }
}
