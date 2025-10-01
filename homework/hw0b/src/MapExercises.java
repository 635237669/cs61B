import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character,Integer> map = new TreeMap<>();
        int b=1;
        for(char a='a';a <='z';a++) {
            map.put(a,b);
            b+=1;
        }
        return map;
    }


    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer,Integer> map = new TreeMap<>();
        for(int num:nums){
            map.put(num,num*num);
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new TreeMap<>();
        for(String a:words){
            if(map.containsKey(a)){
                int b=map.get(a);
                b+=1;
                map.put(a,b);
            }
            else{
                map.put(a,1);
            }
        }
        return map;
    }
}
