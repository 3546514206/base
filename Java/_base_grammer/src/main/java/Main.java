/**
 * todo
 *
 * @Date 2024-08-19 15:30
 * @Author 杨海波
 **/
public class Main {

    public static void main(String[] args) {
        // String a = "yyy";
        // System.out.println(a.hashCode());
        // deil(a);
        // System.out.println(a.hashCode());


        User u = new User();
        System.out.println(u.hashCode());
        post(u);
        System.out.println(u.hashCode());
    }

    private static void post(User u) {
        System.out.println(u.hashCode());
        u = new User();
        System.out.println(u.hashCode());
    }


    private static void deil(String a) {
        System.out.println(a.hashCode());
        a = "sss";
        System.out.println(a.hashCode());
    }


}
