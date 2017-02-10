/**
 * Created by xudazhou on 2017/1/18.
 */
public class Url2Sign {
    public native String sign(String url);

    static {
        System.loadLibrary("dict");
    }

    public static void main(String[] args) {
        System.out.println(new Url2Sign().sign("http://baijiahao.baidu.com/s?id=1556851339894822"));
    }
}
