/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraanaapp;



import getters_setters.data;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author satyajit_patil
 */
public class firstactivityController implements Initializable  {
    /* global vatiables*/
    PreparedStatement ps=null;     
    ResultSet rs=null;
    int total_amount;
    String des;
    
    //class objects
    public connection_access access;
    
    
    // FXML declarations
    @FXML
    private Button addrecord_button;
    @FXML
    private ComboBox category_cb;
    @FXML
    private TextField productname_tf,productamount_tf;
    @FXML
    private Label Total_amount_label,label_for_cb_category;
    @FXML
    private TableView<data> new_table;
    @FXML
    private TableColumn<?, ?> col_productname;
    @FXML
    private TableColumn<?, ?> col_gap;
    @FXML
    private TableColumn<?, ?> col_amount;  
    @FXML
    private TableColumn<?, ?> col_gap2;
    @FXML
    private TableColumn<?, ?> col_category;
    
    
    //observable lists
    ObservableList<data> dataclass_object=FXCollections.observableArrayList();/*list for table with id: new_table */
    ObservableList<String> category_cb_list=FXCollections.observableArrayList("Transportation","Fuel","Healthcare","Food","Entertainment","Housing bills","Clothing","Others");
    ObservableList<String> whospentit_cb_list=FXCollections.observableArrayList("satyajit","sushant","common");

    
    
    //constructor
    public firstactivityController() throws SQLException {
        this.access = new connection_access();
    }
    
    /* a function for returning date*/
   private static LocalDate gettoday()
    {
        return LocalDate.now();
    }
   
    // button actions
    @FXML
    private void addrecord_fun(ActionEvent event)
    {
        try {
            String add_record="INSERT into expense (product,amount,date,category) VALUES(?,?,?,?)";
            ps=access.con.prepareStatement(add_record);
            ps.setString(1,productname_tf.getText());
            ps.setInt(2,Integer.parseInt(productamount_tf.getText()));
            ps.setObject(3,gettoday());
            ps.setString(4, (String) category_cb.getValue());
            ps.executeUpdate();
                    
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Enter the values");
            alert.setHeaderText("textfield or combobox is empty");
            alert.setContentText("Enter values in each Fields");
            alert.showAndWait();
        }
        total_amount=total_amount+Integer.parseInt(productamount_tf.getText());
        Total_amount_label.setText("RS:"+Integer.toString(total_amount));
        show_record();
    }
    @FXML
    private void setlabel_of_category_combobox(ActionEvent event){
        label_for_cb_category.setText((String) category_cb.getValue());
    }
    
    private void show_record()
    {
        String recent = "select * from expense order by id desc limit 1";
        try {
            ps=access.con.prepareStatement(recent);
            rs=ps.executeQuery();
            while(rs.next())
            {
                dataclass_object.add(new data
                (
                  rs.getString("product"),
                  rs.getInt("amount"),
                  rs.getString("category")
                ));
                new_table.setItems(dataclass_object);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("table not found");
            alert.setHeaderText("table might be missing");
            alert.setContentText("table might be missing");
            alert.showAndWait();
        }
    }
    
    
    //initialize method
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // properties
        col_productname.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        category_cb.setItems(category_cb_list);
        
        
    }    
    
}
