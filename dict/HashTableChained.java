/* HashTableChained.java */

package dict;
import list.*;
/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {
    
    /**
     *  Place any data fields here.
     **/
    private DList[] dataStore;
    private int size;
    private int hugePrime;
    
    
    
    /**
     *  Construct a new empty hash table intended to hold roughly sizeEstimate
     *  entries.  (The precise number of buckets is up to you, but we recommend
     *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
     **/
    
    public HashTableChained(int sizeEstimate) {
        // Your solution here.
        int temp = nearestPrime(sizeEstimate * 2);
        this.dataStore = new DList[temp];
        this.size = 0;
        this.hugePrime = nearestPrime(sizeEstimate * 30);
        makeEmpty();
    }
    
    /**
     *  Construct a new empty hash table with a default size.  Say, a prime in
     *  the neighborhood of 100.
     **/
    
    public HashTableChained() {
        // Your solution here.
        this.dataStore = new DList[101];
        this.size = 0;
        this.hugePrime = nearestPrime(101 * 30);
        makeEmpty();
    }
    
    
    /**
     *  nearestPrime generates the prime nearest to n that
     *  is less than or equal to n using the Sieve of Eratosthenes.
     *  Do not expect reasonable output for n < 2
     *  @param n is the number being passed to the method. n must be 2 or greater
     **/
    private int nearestPrime(int n){
        try{
            DList allPrimes = new DList();
            for(int i = 2; i <= n; i++){
                allPrimes.insertBack(i);
            }
            ListNode primeNode = allPrimes.front();
            int i = 0;
            while(i < allPrimes.length()){
                i++;
                int prime = ((Integer) primeNode.item()).intValue();
                ListNode nextNode = primeNode.next();
                int j = i;
                while(j < allPrimes.length()){
                    int testNumber = ((Integer) nextNode.item()).intValue();
                    if(testNumber % prime == 0){
                        ListNode temp = nextNode;
                        nextNode = nextNode.next();
                        temp.remove();
                    } else{
                        nextNode = nextNode.next();
                        j++;
                    }
                }
                primeNode = primeNode.next();
            }
            return ((Integer) allPrimes.back().item()).intValue();
        } catch(InvalidNodeException failure){
            return -1;
        }
    }
    
    /**
     *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
     *  to a value in the range 0...(size of hash table) - 1.
     *
     *  This function should have package protection (so we can test it), and
     *  should be used by insert, find, and remove.
     **/
    
    int compFunction(int code) {
        // Replace the following line with your solution.
        return positiveMod(positiveMod(37 * code + 5, hugePrime), dataStore.length);
    }
    
    /**
     * positiveMod returns a positive mod value for x % y
     **/
    private int positiveMod(int x, int y){
        int possibleSolution = x % y;
        if(possibleSolution >= 0){
            return possibleSolution;
        } else{
            return possibleSolution + y;
        }
    }
    
    /**
     *  Returns the number of entries stored in the dictionary.  Entries with
     *  the same key (or even the same key and value) each still count as
     *  a separate entry.
     *  @return number of entries in the dictionary.
     **/
    
    public int size() {
        // Replace the following line with your solution.
        return this.size;
    }
    
    /**
     *  Tests if the dictionary is empty.
     *
     *  @return true if the dictionary has no entries; false otherwise.
     **/
    
    public boolean isEmpty() {
        // Replace the following line with your solution.
        return size == 0;
    }
    
    /**
     *  Create a new Entry object referencing the input key and associated value,
     *  and insert the entry into the dictionary.  Return a reference to the new
     *  entry.  Multiple entries with the same key (or even the same key and
     *  value) can coexist in the dictionary.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the key by which the entry can be retrieved.
     *  @param value an arbitrary object.
     *  @return an entry containing the key and value.
     **/
    
    public Entry insert(Object key, Object value) {
        // Replace the following line with your solution.
        
        // determine whether the table needs to be resized
        if(size >= dataStore.length){
            try{
                DList[] temporary = new DList[nearestPrime(dataStore.length * 2)];
                int newHugePrime = nearestPrime(30 * 2 * dataStore.length);
                for(int j = 0; j < temporary.length; j++){
                    temporary[j] = new DList();
                }
                
                for(int i = 0; i < dataStore.length; i++){
                    DListNode currentNode = (DListNode) dataStore[i].front();
                    for(int j = 0; j < dataStore[i].length(); j++){
                        // put entry in new location
                        Object currentKey = ((Entry) currentNode.item()).key();
                        int targetIndex = positiveMod(positiveMod(37 * key.hashCode() + 5, newHugePrime), temporary.length);
                        temporary[targetIndex].insertBack(currentNode.item());
                        currentNode = (DListNode) currentNode.next();
                    }
                }
                dataStore = temporary;
                hugePrime = newHugePrime;
            } catch(InvalidNodeException error){}
        }
        Entry hashEntry = new Entry();
        hashEntry.key = key;
        hashEntry.value = value;
        
        int targetIndex = compFunction(key.hashCode());
        dataStore[targetIndex].insertBack(hashEntry);
        size++;
        return hashEntry;
    }
    
    
    /**
     *  Search for an entry with the specified key.  If such an entry is found,
     *  return it; otherwise return null.  If several entries have the specified
     *  key, choose one arbitrarily and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     **/
    
    public Entry find(Object key) {
        // Replace the following line with your solution.
        int targetIndex = compFunction(key.hashCode());
        DList searchBucket = dataStore[targetIndex];
        if(searchBucket.length() == 0){
            return null;
        }
        try{
            DListNode potentialNode = (DListNode) searchBucket.front();
            int i = 0;
            while(i < searchBucket.length()){
                if(((Entry)potentialNode.item()).key.equals(key)){
                    return ((Entry)potentialNode.item());
                }
                potentialNode = (DListNode) potentialNode.next();
                i++;
            }
            return null;
        } catch(InvalidNodeException failure){
            return null;
        }
    }
    
    /**
     *  Remove an entry with the specified key.  If such an entry is found,
     *  remove it from the table and return it; otherwise return null.
     *  If several entries have the specified key, choose one arbitrarily, then
     *  remove and return it.
     *
     *  This method should run in O(1) time if the number of collisions is small.
     *
     *  @param key the search key.
     *  @return an entry containing the key and an associated value, or null if
     *          no entry contains the specified key.
     */
    
    public Entry remove(Object key) {
        // Replace the following line with your solution.
        int targetIndex = compFunction(key.hashCode());
        DList searchBucket = dataStore[targetIndex];
        if(searchBucket.length() == 0){
            return null;
        }
        try{
            DListNode potentialNode = (DListNode) searchBucket.front();
            int i = 0;
            while(i < searchBucket.length()){
                if(((Entry)potentialNode.item()).key.equals(key)){
                    Entry tempEntry = (Entry)potentialNode.item();
                    potentialNode.remove();
                    size--;
                    return tempEntry;
                }
                potentialNode = (DListNode) potentialNode.next();
                i++;
            }
            return null;
        } catch(InvalidNodeException error){
            return null;
        }
    }
    
    /**
     *  Remove all entries from the dictionary.
     */
    public void makeEmpty() {
        // Your solution here.
        for(int i = 0; i < dataStore.length; i++){
            dataStore[i] = new DList();
        }
        size = 0;
    }
    
}
