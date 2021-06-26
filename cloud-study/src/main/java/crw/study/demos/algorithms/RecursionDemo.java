package crw.study.demos.algorithms;

/**
 * 递归算法： n的阶层
 */
public class RecursionDemo {

    public static int factorial(int n) {
        if (n == 1) {
            return n;
        }
        return n * factorial(n-1);
    }

    public static void main(String[] args) {
        int result = RecursionDemo.factorial(5);
        System.out.println(result);
    }

}
