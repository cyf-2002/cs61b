import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {
    @Test
    public void test1() {
        ArrayDeque ar = new ArrayDeque();

        ar.addLast(2);
        ar.addLast(3);
        ar.addLast(4);

        ar.addLast(6);
        ar.addFirst(4);
        ar.addFirst(3);
        ar.addFirst(2);
        ar.addFirst(1);
        ar.addFirst(0);
        ar.printDeque();

        ar.removeLast();
        ar.printDeque();
        ar.removeLast();
        ar.printDeque();
        ar.removeFirst();
        ar.printDeque();

        ArrayDeque ar1 = new ArrayDeque(ar);

        ar1.printDeque();
    }

}
