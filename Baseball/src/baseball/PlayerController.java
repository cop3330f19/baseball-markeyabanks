/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.bind.annotation.XmlElement;
import javafx.fxml.Initializable;
import javax.xml.bind.JAXB;

/**
 *
 * @author markeyabanks
 */
public class PlayerController implements Initializable{
    @FXML private ListView listViewTeams;
    @FXML private TableView tableViewDisplay;
    @FXML private TableColumn <String, Player> colPlayer;
    @FXML private TableColumn <Double, Player> colBattingAvg;
    
    
   @XmlElement(name="player")
   private List<Player> baseballPlayers = new ArrayList<>(); //store baseballPlayers
 
   
   private final String DIR = System.getProperty("user.dir");
   
   @Override 
   public void initialize(URL location, ResourceBundle resources){
       readFile();
       
    listViewTeams.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
       @Override
       public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
           
           
           colPlayer.setCellValueFactory(new PropertyValueFactory<>("Name"));
           colBattingAvg.setCellValueFactory(new PropertyValueFactory<>("Average"));
           
           ObservableList<Player> result = FXCollections.observableArrayList();
            ObservableList<Player> result2 = FXCollections.observableArrayList();

           int idx =0;
           double average = 0, sum = 0;
           int count = 0;
           //while the findTeam function does not return -1  and idx is less than the size of the arrayList
           while(idx != -1 && idx < baseballPlayers.size()){
            idx = findTeam(newValue,idx);
            if(idx!= -1){
                result.add(baseballPlayers.get(idx)); //add the player to the observable list 
                idx++;
                count++;
                sum += Double.parseDouble(baseballPlayers.get(idx).getAverage());
            }
            
           }
           average = sum / count;
          
           for(int k=0; k < result.size(); k++){
               
               if(Double.parseDouble(result.get(k).getAverage()) >= average){
                  
                   result2.add(result.get(k)); //add results to second observablelist 
               }      
           }
           tableViewDisplay.setItems(result2);//binds the ObservableList to tableView
        }
       });
   }//initialize 
   
   private int findTeam(String team, int start){
      
       for(int k = start; k < baseballPlayers.size(); k++){
           if(baseballPlayers.get(k).getTeam().equals(team))      
            
            return k;
   }//findTeam
       return -1;
  }     
  
   
   private void readFile(){
       ObservableList<String> teamList = FXCollections.observableArrayList();
   
   try(BufferedReader input = Files.newBufferedReader(Paths.get(DIR + "/src/baseball/baseball.xml"))){
        MLB baseballPlayer = JAXB.unmarshal(input, MLB.class);
        baseballPlayers = baseballPlayer.getbaseballPlayers();
        
         for(Player baseballP : baseballPlayer.getbaseballPlayers()){
         
            //create new object and store in arrayList
            if(!teamList.contains(baseballP.getTeam()))
               teamList.add(baseballP.getTeam());

         }//for 
      }
      catch (IOException ioException){
         System.err.println("Error opening file.");
      }
   
   //sort by the team name in ascending order 
      Collections.sort(baseballPlayers, new PlayerComparator());
   //sort the teamList
      Collections.sort(teamList);
   //binds the observableList to the listView
       listViewTeams.setItems(teamList);
       //listViewTeams.getItems().add("Just a test");
   }
}
