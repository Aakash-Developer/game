/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package battleship;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author zange
 */
public interface IPlayerMap {
    GridPane GetShipMap();
    GridPane GetAttackMap();
    VBox GetPlayerMap();
}
