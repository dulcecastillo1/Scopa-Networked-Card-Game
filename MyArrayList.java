public class MyArrayList<E> {
    private Object[] list;
    private int size;
    private int listLength; 
    
    public MyArrayList(){
        listLength = 100; 
        list = new Object[listLength];
        size = 0;
    }
    public boolean addE(E e){
        for(int i = 0; i<list.length; i++){
            if (list[i]==null) {
                list[i]=e;
                size += 1;
                i+=1000000000;
            }
        }
        return true;
    }
    @SuppressWarnings("unchecked")
    public void addE(int c, E e){
        // list[c]=(e);
        E au = null;
        E bu = null;


        for(int i = c; c<size();i++){
            au = bu;
            au =  (E) list[i] ;
            bu = (E) list[i+1] ;
            list[i+1] = au;
        }
        list[c] = e;
        size += 1;
    }

    public void add(E e){
        if(size() == listLength){
            resize(); 
        }
        list[size()] = e;
        size += 1;
    }
    
    @SuppressWarnings("unchecked")
    public E get(int i){
        return (E) list[i];
    }


    @SuppressWarnings("unchecked")
        public E remove(int c){
        E s = (E) list[c];
        Object[] list1 = new Object[100];
        int ct = 0;
        for(int i =0; i<list.length;i++){
            if(i == c){
                i++;
                size --;
            }
            list1[ct] = list[i];
            ct++;
        }
        this.list = list1;
        return s;
    }


    @SuppressWarnings("unchecked")
    public E remove(E c){
        E s = null;
        for(int i = 0; i<size;i++){
            if(list[i].equals(c)){
                s = (E) list[i];
            }
        }
        int a = -1;
        for(int i =0; i<list.length;i++){
            if(list[i].equals(c)){
                a=i;
            }
        }
        if(a != -1){
            for(int i = a+1;i<list.length;i++ ){
                list[i-1] = list[i];
            }
            size--;
        }
        return s;
    }


    public void set(int i, E s){
        list[i]=s;
    }
    public int size(){
        return size;
    }


    public String toString(){
        String elements = "";
        for(int i = 0; i < list.length; i++){
            if(list[i]!=null){
                elements += (list[i] + "\n");

            }
        }
        return elements;
    }

    public void resize(){
        int newSize = (list.length)*2; 
        Object[] resizeArray = new Object[newSize]; 
        for(int i = 0; i < list.length; i++){
            Object o = list[i]; 
            resizeArray[i] = o; 
        }
        this.list = resizeArray; 
        
    }
    
    
}
