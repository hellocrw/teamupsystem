package crw.study.demos.algorithms;

/**
 * 递归算法：Hanoi问题
 */
public class RecusionDemo01 {

    public static void hanoiMethod(int n, int a, int b, int c) {
        if (n > 0) {
            hanoiMethod(n-1, a, c, b);
            move(a, b);
            hanoiMethod(n-1, c, b, a);
        }
    }

    public static void move(int a, int b){

    }

    public static void main(String[] args) {

    }


}
