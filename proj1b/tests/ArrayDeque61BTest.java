import deque.ArrayDeque61B;
import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {


//add_first_from_empty：验证在空双端队列上执行addFirst操作的功能。
//add_last_from_empty：检查addLast方法在空双端队列上的工作情况。
//add_first_nonempty：验证addFirst方法在非空双端队列上的功能。
//add_last_nonempty：验证addLast方法在非空双端队列上的运作。
//add_first_trigger_resize: 验证在底层数组已满时调用addFirst是否正常工作
//add_last_trigger_resize：检查在底层数组已满时调用addLast是否有效

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    void addFirstTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.toList()).containsExactly();
        lld1.addFirst(1);
        assertThat(lld1.toList()).containsExactly(1);
        lld1.addFirst(2);
        assertThat(lld1.toList()).containsExactly(2, 1).inOrder();
        for (int i = 0; i <= 6; i++) {
            lld1.addFirst(1);
        }
        assertThat(lld1.toList()).containsExactly(1, 1, 1, 1, 1, 1, 1, 2, 1).inOrder();
    }

    @Test
    void addLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        assertThat(lld1.toList()).containsExactly(1);
        lld1.addLast(2);
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
        for (int i = 0; i <= 6; i++) {
            lld1.addLast(1);
        }
        assertThat(lld1.toList()).containsExactly(1, 2, 1, 1, 1, 1, 1, 1, 1).inOrder();
    }

    //get_valid: 检查get方法在有效索引上的运作情况。
//get_oob_large：检查在大范围、越界索引时get功能是否有效。
//get_oob_neg：验证在负数索引下get函数能否正常工作。
    @Test
    void getTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.get(-1)).isEqualTo(null);
        lld1.addLast(1);
        assertThat(lld1.get(0)).isEqualTo(1);
        assertThat(lld1.get(1)).isEqualTo(null);
    }

    // isEmpty Tests
//is_empty_true: Check that size works on an empty deque.
//is_empty_false: Check that size works on a non- empty deque.
    @Test
    void isEmptyTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addLast(1);
        assertThat(lld1.isEmpty()).isFalse();

    }

    //size: Check that size works.
//size_after_remove_to_empty: Add some elements to a deque and remove them all, then check that size still works.
//size_after_remove_from_empty: Remove from an empty deque, then check that size still works.
    @Test
    void sizeTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addLast(1);
        assertThat(lld1.size()).isEqualTo(1);
    }

    //remove_first: Check that removeFirst works.
    //remove_last: Check that removeLast works.
    //remove_first_to_empty: Add some elements to a deque and remove almost all of them. Check that removing the last element with removeFirst works.
    //remove_last_to_empty: Add some elements to a deque and remove almost all of them. Check that removing the last element with removeLast works.
    //remove_first_to_one: Add some elements to a deque and remove almost all of them. Check that removing the second to last element with removeFirst works.
    //remove_last_to_one: Add some elements to a deque and remove almost all of them. Check that removing the second to last element with removeLast works.
    //remove_first_trigger_resize: Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately.
    //remove_last_trigger_resize: Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately.
    @Test
    void removeFirstText() {
        ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.removeFirst()).isEqualTo(2);
        for (int i = 0; i <= 8; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i <= 5; i++) {
            lld1.removeFirst();
        }
        assertThat(lld1.toList()).containsExactly(6, 7, 8).inOrder();
        assertThat(lld1.length()).isEqualTo(8);//测试一下放大缩小
    }

    @Test
    void removeLastText() {
        ArrayDeque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.removeLast()).isEqualTo(1);
        for (int i = 0; i <= 8; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i <= 5; i++) {
            lld1.removeLast();
        }
        assertThat(lld1.toList()).containsExactly(0, 1, 2).inOrder();
        assertThat(lld1.length()).isEqualTo(8);//测试一下放大缩小
    }}


















