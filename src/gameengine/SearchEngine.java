/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.Constant.Action;
import api.Constant.State;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author zange
 */
public class SearchEngine {
    
    State currentState;
    Action currentAction;

    public SearchEngine(){
    
         
    }
   
    public class Transition{
        
        State state;
        Action action;

        public Transition(State state, Action action){
            
            this.state = state;
            this.action = action;
        }
        
        @Override    
        public boolean equals(Object o) {        
      
            Transition obj = (Transition)o;
            return obj != null && this.state.equals(obj.state) && this.action.equals(obj.action);    
        }    
        
        @Override    
        public int hashCode() {        
            
            return 17 + 21*state.hashCode() + 13*action.hashCode();    
        }  
    }
    
    
    public class Fsm{
        
        Dictionary<Transition,State> fsm;
        
        public Fsm(){

            fsm = new Hashtable<Transition,State>(){
                {
                    put(new Transition(State.Invalid , Action.INIT), State.RANDOM_SEARCH);
                    put(new Transition(State.RANDOM_SEARCH , Action.INIT), State.RANDOM_SEARCH);
                }
            };
            
        }
    }
    
}
