public class MyHashMap<K, V>{
    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;

    public MyHashMap(){
        size = 0;
        keySet = new MyHashSet<K>();
        hashArray = new Object[100000];
    }

    public V put(K key, V value){
        keySet.add(key);
        int hashCode = key.hashCode();
        if(key.hashCode() < 0 || key.hashCode() > 100000){
            hashCode = Math.abs((key.hashCode())/100000); 
        }
        V rval = (V) hashArray[hashCode];

        if(rval == null){
            size++;
            hashArray[hashCode] = value;
            return null;
        }else if(rval == value){
            return rval;
        }else{
            hashArray[hashCode] = value;
            return rval;
        }
    }

    public V get(Object o){
        K key = (K) o;
        int hashCode = key.hashCode();
        V rval = (V) hashArray[hashCode];
        return rval;
    }

    public V remove(Object o){
        K key = (K) o;
        int hashCode = key.hashCode();
        V rval = (V) hashArray[hashCode];
        size--;
        keySet.remove(key);
        hashArray[hashCode] = null;
        return rval;

    }

    public MyHashSet<K> keySet(){
        return keySet;
    }

    public String toString(){
        String returnString = ""; 
        DLList<K> keys = keySet.DLList(); 
        for(int i = 0; i < keys.size(); i++){
            int hashCode = keys.get(i).hashCode(); 
            if(keys.get(i).hashCode() < 0 || keys.get(i).hashCode() > 100000){
                hashCode = Math.abs((keys.get(i).hashCode())/100000); 
            }
            V rval = (V) hashArray[hashCode]; 
            returnString += keys.get(i) + " - " + rval + ", ";
        }
        return returnString; 
    }
    
}
