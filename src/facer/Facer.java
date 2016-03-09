
package facer;

public class Facer {
    
    int width=500,height=700;
    
    public static void main(String[] args) 
    {
        scaner s = new scaner();
 
        Thread t1 = new Thread(s);
        t1.start();
    
    }
    
}
