/*---------------------------------------------------------
 *  Stephanie Vasquez-Soltero
 *  Dec. 18th, 2016
 *  Implementing a Deque
 *---------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

// Will be using a linked list
public class Deque<Item> implements Iterable<Item> {
//    From Queue.java in book, a linked list implementation of a queue
//    http://introcs.cs.princeton.edu/java/43stack/Queue.java.html
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue
    
    // construct an empty deque
    public Deque() {
        first = null;
        last  = null;
        n = 0;
    }                          
    
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
        
        Node(Item item) {
            this.item = item;
            this.previous = null; // the first item will have previous as null
            this.next = null;     // the last item will have next as null
        }
    }
    
    // is the deque empty?
    public boolean isEmpty() { return first == null; }               
    
    // return the number of items on the deque
    public int size() { return n; }                        
   
    // add the item to the front
    public void addFirst(Item item) {  
        // same as a stack push
        if (item == null) {
            throw new NullPointerException(); 
        }
         // If empty last and first are the same
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } 
        else {
            Node oldfirst = first;     // grabbing the oldfirst
            first = new Node(item);    // creating new first
            first.next = oldfirst;     // setting the next of the new first to the oldfirst
            oldfirst.previous = first; // setting previous of the oldfirst to the new first
        }
        n++;
    }    
    
    // add the item to the end
    public void addLast(Item item) {
    // same as add(e) in a queue
        if (item == null) { 
            throw new NullPointerException(); 
        }
       // if it's empty, first and last are the same
        if (isEmpty()) {
            last = new Node(item);
            first = last;
        } 
        else {
                
            Node newLast = new Node(item); // create new last to be added to end of deque
            last.next = newLast;           // set current lasts next to new last
            newLast.previous = last;       // set new lasts previous to current last
            last = newLast;                // set current last to new last
        }
        n++;  
    }
    
    // remove and return the item from the front
    public Item removeFirst() { 
        // same as pop in a stack
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;  // save item to return
      
        if (size() == 1) {
            first = null;
            last = null;
        } 
        else {
            first = first.next;    // delete first node, set first node to next node in list
            first.previous = null; // set new firsts previous to null
        }     
        n--;
        return item;            
    }  
    
    // remove and return the item from the end
    public Item removeLast() {
        // same as remove in a queue
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        
        Item item = last.item;  // save item to return
        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.previous; // Set current last to second to last
            last.next = null;     // set new lasts next to null
        }
        n--;       
        return item;
    }                 
    
    // Iteratables have a method that returns an iterator 
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() { 
        return new DequeIterator();
    }         
    
    
    // Iterators have methods, hasNext() and next(), some have remove() but causes bugs so will not implement
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {  // !this.hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque<Integer> testDeque = new Deque<Integer>();
        testDeque.addFirst(10);
        testDeque.addFirst(15);
        testDeque.addLast(200);
        testDeque.addFirst(30);
        testDeque.addLast(250);
//        testDeque.addLast(null);
//        testDeque.addFirst(null);

//        testDeque.removeLast();
//        testDeque.removeLast();
//        testDeque.removeFirst();
//        testDeque.removeLast();
//        System.out.println(testDeque.isEmpty());
        
        // Testing iterator              
//        Iterator<Integer> itr = testDeque.iterator();
//        System.out.println(itr.next());
//        System.out.println(itr.next());

        
        for (Integer item : testDeque) {
            System.out.println(item.toString());
        }  
    }   
}

// keeping a pointer to the first and last node makes enqueue and 
// dequeue a constant time operation, otherwise enqueue(added to the end) is linear 