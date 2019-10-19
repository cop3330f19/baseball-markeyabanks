/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.util.List;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
/**
 *
 * @author markeyabanks
 */
public class MLB {

   @XmlElement(name="player")
   private final List<Player> baseballPlayers = new ArrayList<>(); //store baseballPlayers
   
   public List<Player> getbaseballPlayers(){
      return baseballPlayers;

}//BaseballTest
    
}
