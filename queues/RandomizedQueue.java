import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


// Since it's randomized, an array would be better
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] qElements;       // queue elements
    private int size;               // number of elements on queue
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        qElements = (Item[]) new Object[1];
        size = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }     
    
    // return the number of items on the queue
    public int size() {
        return size;
    }   

    private void resizeArray(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = qElements[i];
        }
        qElements = copy;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        // if the index (size) is the same as the length, double the size
        if (size == qElements.length) {
            resizeArray(2*qElements.length);  
        }
        
        // add item
        qElements[size++] = item;                        
    }     
                  
    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size); // Get a random index number
        Item value = qElements[randomIndex];       // Grab that value
        size--;                                    // Decrease the size of the array
        if (randomIndex < size) {
            qElements[randomIndex] = qElements[size];  // Avoid duplicates / allow access to last item in queue
        }
        qElements[size] = null; // loitering 

        if (size > 0 && size == qElements.length / 4) {
            resizeArray(qElements.length / 2);
        }
        return value;
    }
    
    // return (but do not remove) a random item
    public Item sample()   {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return qElements[StdRandom.uniform(size)];
    }      
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()  {
        return new RandomizedQueueIterator();
    }       
   
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] randomizedArray; // Array of items, shuffled
        private int location = 0;       // Keep track of location

        private RandomizedQueueIterator() {
            randomizedArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomizedArray[i] = qElements[i];
            }
            StdRandom.shuffle(randomizedArray);
        }

        public boolean hasNext() {
            return location < size;
        }

        public Item next() {
            // If location = size, then no elements
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return randomizedArray[location++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("Cat");
        q.enqueue("Dog");
        q.enqueue("Mouse");
        q.enqueue("Horse");
        
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        
//        Iterator<String> itr = q.iterator();

//         for (String string : q) {
//            System.out.println(string);
//        } 
//        System.out.println(q.isEmpty());
    }  // unit testing
}