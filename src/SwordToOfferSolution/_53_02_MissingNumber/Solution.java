package SwordToOfferSolution._53_02_MissingNumber;

/*
 * 0~n-1 中缺失的数
 *
 * 思路：
 * 1. 由于每个数与其对应的下标是相同的，例如数字 0 对应索引 0，数字 1 对应索引 1 等等；
 * 2. 所以如果一个数 m 缺失了，则 m + 1 位置上的数就会前移到 m 的位置，m + 2 位置上的数就会前移到 m + 1 的位置；
 * 3. 那就直接找出第一个值和下标不相等的元素即可；
 * 4. 利用二分查找：
 *     如果中间元素的值和下标相等，则只需要查找右部分元素即可（因为左侧的元素都是对应位置相等的）；
 *     如果中间元素的值和下标不相等，并且前一个元素和它（指的是这前一个元素）的下标相等，则说明找到了这个缺失的数；
 *     如果中间元素的值和下标不相等，并且前一个元素和它（指的是这前一个元素）的下标也不相等，则需要在左部分的数组中查即可；
 * 5. 当所有的数都满足 arr[i] = i 的时候，缺失的就是 i。
 */
public class Solution {
    public static int findMissingNumber(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (arr[middle] != middle) {
                if (middle == 0 || arr[middle - 1] == middle - 1) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            } else {
                left = middle + 1;
            }
        }
        // 当所有的数都满足 arr[i] = i 的时候，缺失的就是 i
        if (left == arr.length) {
            return arr.length;
        }
        // 无效的输入，比如数组不是按要求排序的，
        // 或者有数字不在 0 到 n - 1 范围之内
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4};
        System.out.println(findMissingNumber(arr));
    }
}