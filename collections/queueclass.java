package collections;
import java.util.*;
public class queueclass {
    public static void main(String[] args) {
        Queue<Integer> q= new LinkedList<>();
        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        q.offer(1);
        System.out.println(q);

    }

}
