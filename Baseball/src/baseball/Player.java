/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

/**
 *
 * @author markeyabanks
 */
public class Player{
    
   private String name; //player name 
   private String team; //player team 
   private int atBats; //player # of bats
   private int hits; //player # of hits
 
  public Player(){this("", "", 0,0);} //default values
   
  //initalizes an Account with provided values
   public Player(String name, String team, int atBats, int hits){
     this.name = name;
     this.team = team;
     this.atBats = atBats;
     this.hits = hits;
  }


public String getName(){
   return name;
}
public void setName(String name){
   this.name = name;
}
public String getTeam(){
   return team;
}
public void setTeam(String team){
   this.team = team;
}
public int getAtBats(){
   return atBats;
}
public void setAtBats(int atBats){
   this.atBats = atBats;
}
public int getHits(){
   return hits;
}
public void setHits(int hits){
   this.hits = hits;
}
public String getAverage(){
   double average = 0;
   average = (double)hits/(double)atBats;
   return String.format("%.3f",average);
   }
   
 }


