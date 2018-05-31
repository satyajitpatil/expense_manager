/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiraanaapp;

import getters_setters.data_table2;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author satyajit_patil
 */
public class SecondactivityController implements Initializable {
    String query_to_showtable,query_to_show_totalamount,totalamount_value,chart_query;
    PreparedStatement ps=null;     
    ResultSet rs=null;
    private String append_it_last_on_chart_query = "";
    
        
    
    
    //class objects
    public connection_access access;

    @FXML
    private Pane s_activity;
    @FXML
    private Pane sa_upperpane;
    @FXML
    private Label satotalamount_label,label_for_cb_category2;
    @FXML
    private DatePicker fromdate_dp;
    @FXML
    private DatePicker lastdate_dp;
    @FXML
    private ComboBox<String> sacategory_cb;
    @FXML
    private TableView<data_table2> table2;
    @FXML
    private TableColumn<?, ?> sacol_productname;
    @FXML
    private TableColumn<?, ?> sacol_gap1;
    @FXML
    private TableColumn<?, ?> sacol_amount;
    @FXML
    private TableColumn<?, ?> sacol_gap2;
    @FXML
    private TableColumn<?, ?> sacol_category;
    @FXML
    private TableColumn<?, ?> sacol_gap3;
    @FXML
    private TableColumn<?, ?> sacol_date;
    @FXML
    private TableColumn<?, ?> sacol_gap4;
    @FXML
    private TableColumn<?, ?> sacol_whospentit;
    @FXML
    private AreaChart<String,Integer> area_chart; 

    
    ObservableList<data_table2> table2_list=FXCollections.observableArrayList();/*list for table with id: new_table */
    ObservableList<String> sacategory_cb_list=FXCollections.observableArrayList("All categories","Food","Transportation","Healthcare","fuel","Housing bills","Entertainment","Clothing");
    /**
     * Initializes the controller class.
     * @throws java.sql.SQLException
     */
    //constructor
    public SecondactivityController() throws SQLException {
        this.access = new connection_access();
    }
    
    private void total_amount(String query)
    {
        int c=0;
        try {
            ps=access.con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                c= rs.getInt(1);
            }
            satotalamount_label.setText("Total Amount:  RS "+Integer.toString(c));
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(" amount error");
        }
        
    }
    private void display_record(String query)
    {
        try {
            table2_list.clear();
            ps=access.con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                table2_list.add(new data_table2
                (
                  rs.getString("product"),
                  rs.getInt("amount"),
                  rs.getString("category"),
                  rs.getDate("date")
                ));
                table2.setItems(table2_list);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
       
    }
    
    
    
    @FXML
    private void show_rec(ActionEvent event){
        if(!(sacategory_cb.getValue()).equals("All categories")&&(fromdate_dp.getValue()!=null) && (lastdate_dp.getValue()!= null)){ /*1-111*/
            query_to_showtable = "select product,amount,category,date from expense where category ='"+ sacategory_cb.getValue()+"' and date >= '"+fromdate_dp.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where category ='"+ sacategory_cb.getValue()+"' and date >= '"+fromdate_dp.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            append_it_last_on_chart_query =" date >= '"+fromdate_dp.getValue()+"' and where date <= '"+lastdate_dp.getValue()+"' ";
        } 
        else if(!(sacategory_cb.getValue()).equals("All categories")  &&  (fromdate_dp.getValue()!=null)   &&    (lastdate_dp.getValue()==null)){/*2-110*/
            
            query_to_showtable="select product,amount,category,date from expense where category ='"+ sacategory_cb.getValue()+"' and date >= '"+fromdate_dp.getValue()+"';";
            
            query_to_show_totalamount="select sum(amount) from expense where category ='"+ sacategory_cb.getValue()+"' and date >= '"+fromdate_dp.getValue()+"';";
            
            append_it_last_on_chart_query =" date >= '"+fromdate_dp.getValue()+"' ";
        }
        else if(!(sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()==null) && (lastdate_dp.getValue()!=null)){/*3-101*/
            
            query_to_showtable = "select product,amount,category,date from expense where category ='"+ sacategory_cb.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where category ='"+ sacategory_cb.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            append_it_last_on_chart_query =" date <= '"+lastdate_dp.getValue()+"' ";
        }
        else if(!(sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()==null) && (lastdate_dp.getValue()==null)){/*4-100*/
            
            query_to_showtable = "select product,amount,date,category from expense where category ='"+ sacategory_cb.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where category ='"+ sacategory_cb.getValue()+"';";
        }
        else if((sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()!=null) && (lastdate_dp.getValue()!=null)){/*5-011*/
            
            query_to_showtable = "select product,amount,category,date from expense where date >= '"+fromdate_dp.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where date >= '"+fromdate_dp.getValue()+"' and date <= '"+lastdate_dp.getValue()+"';";
            
            append_it_last_on_chart_query =" date >= '"+fromdate_dp.getValue()+"' and where date <= '"+lastdate_dp.getValue()+"';";

        }
        else if((sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()!=null) && (lastdate_dp.getValue()==null)){/*6-010*/
            
            query_to_showtable = "select product,amount,category,date from expense where date >= '"+fromdate_dp.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where date >= '"+fromdate_dp.getValue()+"' ";
            
            append_it_last_on_chart_query =" date >= "+fromdate_dp.getValue();

        }
        else if((sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()==null) && (lastdate_dp.getValue()!=null)){/*7-001*/
  
            query_to_showtable = "select product,amount,category,date from expense where date <= '"+lastdate_dp.getValue()+"';";
            
            query_to_show_totalamount = "select sum(amount) from expense where date <= '"+lastdate_dp.getValue()+"' ";
            
            append_it_last_on_chart_query =" date <= "+lastdate_dp.getValue();
        }
        else if((sacategory_cb.getValue()).equals("All categories") && (fromdate_dp.getValue()==null) && (lastdate_dp.getValue()==null)){/*8-000*/
            
            query_to_showtable = "select product,amount,category,date from expense;";
            
            query_to_show_totalamount = "select sum(amount) from expense;";
        }
        else
        {
            query_to_showtable = "select product,amount,category,date from expense;";
            
            query_to_show_totalamount = "select sum(amount) from expense;";
        }
        System.out.println(query_to_showtable);
        display_record(query_to_showtable);
        total_amount(query_to_show_totalamount);
        //String and_append_it_last_on_chart_query = " and "+append_it_last_on_chart_query;
        //append_it_last_on_chart_query = " where "+append_it_last_on_chart_query;
        //string_manipulation_for_chart(append_it_last_on_chart_query,and_append_it_last_on_chart_query);
    }
    @FXML
    private void setlabel_of_category_combobox2(){
        label_for_cb_category2.setText((String) sacategory_cb.getValue());
    }
    
    
    
    
    private void load_chart(String chart_query,String legend) 
    {
        DateFormat dfr= new SimpleDateFormat("yyyy-mm-dd");
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        series.setName(legend);
        try{
            ps=access.con.prepareStatement(chart_query);
            rs=ps.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(dfr.format(rs.getDate(1)),rs.getInt(2)));
            }
            area_chart.getData().add(series);
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
    
    private void string_manipulation_for_chart(String append_query,String and_append_query)
    {
        String var1 = "select date ,sum(amount) from expense "+append_query+" group by date;";
        String var2 = "select date ,sum(amount) from expense where category = 'Food' "+and_append_query+" group by date;";
        String var3 = "select date ,sum(amount) from expense where category = 'Transportation' "+and_append_query+" group by date;";
        String var4 = "select date ,sum(amount) from expense where category = 'Healthcare' "+and_append_query+" group by date;";
        String var5 = "select date ,sum(amount) from expense where category = 'fuel' "+and_append_query+" group by date;";
        String var6 = "select date ,sum(amount) from expense where category = 'Housing bills' "+and_append_query+" group by date;";
        String var7 = "select date ,sum(amount) from expense where category = 'Entertainment' "+and_append_query+" group by date;";
        String var8 = "select date ,sum(amount) from expense where category = 'clothing' "+and_append_query+" group by date;";
       
        ArrayList variables = new ArrayList();
        variables.add(var1);        variables.add(var2);
        variables.add(var3);        variables.add(var4);   
        variables.add(var5);        variables.add(var6);
        variables.add(var7);        variables.add(var8);
        
        for(int i=0;i<variables.size();i++){
            if(i==0){
                area_chart.getData().clear();
                // kind of refresh
            }
            String final_query = (String) variables.get(i);
            load_chart(final_query,sacategory_cb_list.get(i));
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sacategory_cb.setItems(sacategory_cb_list);
        sacategory_cb.setValue("All categories");
        
        query_to_showtable = "select product,amount,category,date from expense;";
        query_to_show_totalamount = "select sum(amount) from expense;";
        display_record(query_to_showtable);
        total_amount(query_to_show_totalamount);
        
        
        sacol_productname.setCellValueFactory(new PropertyValueFactory<>("product"));
        sacol_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        sacol_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        sacol_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        

        string_manipulation_for_chart("","");
    }    
    
}
