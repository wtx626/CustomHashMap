/**
 * Created with IntelliJ IDEA.
 * User: wutianxiong
 * Date: 2018/3/17
 * Time: 16:05
 */
public class TestCustomHashMap {
    public static void main(String[] args) {
        CustomHashMap customHashMap=new CustomHashMap<String,String>();
        for (int i=0;i<10;i++){
            customHashMap.put("key"+i,"value"+i);
        }
        for (int i=0;i<10;i++){
            System.out.println(customHashMap.get("key"+i));
        }
    }
}
