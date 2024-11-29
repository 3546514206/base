
import java.io.FileInputStream;
import java.io.IOException;

/**
 * todo
 *
 * @Date 2024-11-29 11:33
 * @Author 杨海波
 **/
public class TestMain {


    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("example.txt");
            // 进行一些操作
            int data = fis.read();
            System.out.println((char) data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // fis 未被关闭，导致文件流未释放
    }


}
