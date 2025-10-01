import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int total = 0; // 初始化总和为0
        for (int num : L) { // 遍历列表中的每个元素
            total += num; // 累加每个整数到总和
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer>m= new ArrayList<>();
        for (int num : L) {
            if(num%2==0) {
                m.add(num);
            }
        }
        return m;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer>m= new ArrayList<>();
        for (int num : L1) {
            if (L2.contains(num)) {
                m.add(num);
            }
        }
        return m;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String str : words) {          // 遍历每个字符串
            for (int i=0;i<str.length();i++) {  // 遍历每个字符,这里不能用增强for循环，因为字符串不能遍历，只能遍历有迭代器的数据类型
                if (str.charAt(i) == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
