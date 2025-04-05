/**
 * todo
 *
 * @Date 2024-09-20 10:48
 * @Author 杨海波
 **/
public class Add {

    public static void main(String[] args) {
        System.out.println(calc(100));
    }

    public static int calc(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i*i;
        }

        return sum;
    }
}
