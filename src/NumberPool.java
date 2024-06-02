import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;


public class NumberPool {

    private final Queue<Integer> pool ;
    private final ReentrantLock lock;
    // Constants
    private final int MIN_MEMBERS = 2;
    private final int NO_MEMBERS = 0;


    public NumberPool(Integer[] initialMembers) {
        pool = new LinkedList<Integer>();
        pool.addAll(Arrays.asList(initialMembers));
        lock = new ReentrantLock();
    }

    public void summarize() {
        // Continue summarizing while there are at least 2 members in the pool
        while (pool.size() >= MIN_MEMBERS) {
            Integer member1, member2; // Create Integer variables to store the members

            // Acquire the lock to ensure thread-safe access to the pool
            lock.lock();
            try {
                // Remove the first two members from the pool
                member1 = pool.poll();
                member2 = pool.poll();
            } finally {
                // Release the lock to allow other threads to access the pool
                lock.unlock();
            }

            // Check if both members were successfully retrieved from the pool
            if (member1 != null && member2 != null) {
                // Calculate the sum of the two members
                int sumOfMembers = member1 + member2;

                // Add the sum back to the pool
                addMember(sumOfMembers);

                // Print the summary information for the current thread
                System.out.println("Thread " + Thread.currentThread().getId() +
                        " -Summarized: " + member1 + " + " + member2 + " = " + sumOfMembers);
            }
        }
    }

    public void addMember(int member) {
        // Acquire the lock to ensure thread-safe access to the pool
        lock.lock();
        try {
            // Add the member to the pool
            pool.add(member);
        } finally {
            // Release the lock to allow other threads to access the pool
            lock.unlock();
        }
    }

    public int getSum() {
        // Return the first member (head) of the pool if it exists, otherwise return -1
        return pool.peek() == null ? NO_MEMBERS : pool.peek();
    }
}