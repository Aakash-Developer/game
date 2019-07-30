package utils;

/**
 * It is custom data type to represent a (X,Y) coordinate 
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */

public class Tuple{ 
 
    public final int t1; 
    public final int t2; 
    
    public Tuple(int t1, int t2) { 
    
    this.t1 = t1; 
    this.t2 = t2; 
    
    }

    /**
     * It servers to debug the use of Tuple coordinates
     * @param tuple is a X,Y coordinate
     */
    public static void printIt(Tuple tuple){
        
        if(tuple != null){
            System.out.println("Tuple:"+"("+tuple.t1+","+tuple.t2+")");
        }
        else{
            System.out.println("Tuple NULL");
        }
  } 
    
} 
