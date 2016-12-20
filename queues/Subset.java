
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Subset {
    
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String line;
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        
        // Queue of size N
        while (!StdIn.isEmpty()) {
            line = StdIn.readString();
            q.enqueue(line);
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
}