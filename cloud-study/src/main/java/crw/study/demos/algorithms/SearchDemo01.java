package crw.study.demos.algorithms;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 查找算法：二分搜索技术(分治思想)
 */
public class SearchDemo01 {

    /**
     * 搜索target，返回target在数组中的下标
     * @param arr 有序的数组
     * @param target 搜索目标
     * @return index数组下标
     */
    public static int searchMethod(int[] arr, int target) {

        int leftIndex=0, rightIndex = arr.length;

        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex)/2;
            int middleValue = arr[middleIndex];
            if (middleValue == target) {
                return middleIndex;
            } else if (middleValue > target) {
                rightIndex = middleIndex;
            } else {
                leftIndex = middleIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,6,2,7,9,1,2};
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        Arrays.sort(arr);
        int result = SearchDemo01.searchMethod(arr, target);
        System.out.println(result);
    }
}
