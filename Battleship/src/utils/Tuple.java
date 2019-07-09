package utils;

/**
 *
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 * @param <T1>
 * @param <T2>
 */

public class Tuple<T1, T2> { 
 
    /**
     *  value of position x-cooridinate
     */
    public final T1 t1; 
 
    /**
     * value of position y-cooridinate
     */
    public final T2 t2; 
  
    /**
     * Constructor of Tuple class
     * @param t1
     * @param t2
     */
    public Tuple(T1 t1, T2 t2) { 
    this.t1 = t1; 
    this.t2 = t2; 
  } 
} 
