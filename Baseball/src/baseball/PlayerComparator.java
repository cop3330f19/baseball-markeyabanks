/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.util.Comparator;

/**
 *
 * @author markeyabanks
 */
public class PlayerComparator implements Comparator<Player>{
    @Override
    public int compare(Player p1, Player p2){
        return p1.getTeam().compareTo(p2.getTeam());
    }
}
