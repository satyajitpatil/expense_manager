/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraanaapp;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author satyajit_patil
 */
public class MainfxmlController implements Initializable {
   public connection_access access;
            
    /*
    Declarations
     */
    @FXML
    private AnchorPane holder,main_anchor_pane;
    @FXML
    private BorderPane borderpane;
    @FXML
    private VBox nav_vbox;
    @FXML
    private Button but1,but2,but3,but4;
    
    
    
    Parent temp1;
    Parent temp2;

    
    public MainfxmlController() throws SQLException {
        this.access = new connection_access();
    }
    
    
    /*
     * firstactivity function
     */
    @FXML
    private void fa(ActionEvent event)
    {
        try {   
            holder.getChildren().remove(temp2);
            temp1= FXMLLoader.load(getClass().getResource("firstactivity.fxml"));
            holder.getChildren().setAll(temp1);
        } catch (IOException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     * secondactivity function
     */
    @FXML
    private void sa(ActionEvent event)
    {
        try {   
            holder.getChildren().remove(temp1);
            temp2= FXMLLoader.load(getClass().getResource("secondactivity.fxml"));
            holder.getChildren().setAll(temp2);
            
        } catch (IOException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void faf()
    {
        try {
            temp1= FXMLLoader.load(getClass().getResource("firstactivity.fxml"));
            holder.getChildren().setAll(temp1);
        } catch (IOException ex) {
            Logger.getLogger(MainfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      if(access.con==null){
          System.out.println("null");
      }
      else{
          System.out.println("done");
      }
      faf();
    }       
}
