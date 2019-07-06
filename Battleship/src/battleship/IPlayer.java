package battleship;
import utils.Tuple;
/*
 * @author Team 4
 * @Zbigniew Ivan Angelus
 * @Chen-Fang Chung
 * @Ayush Dave
 * @Aakash Ahuja
 * @Pulkit Wadhwa
 */
public interface IPlayer {
    
    public Tuple NextMove();
    public int GetNumberOfShips();
}
