///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
// */
//package beautysalon;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class Beautysalon extends Application {
//
//    Scene BeautySalon;    //Warehouse Inventory Scene
//    Scene Invoice;  //Search Scene
//
//    @Override
//    public void start(Stage primaryStage) {
//
//        //######## Task1: Observablelists
//        //The WarehouseInventory Scene ------------------------------------------------------------------------------------------
//        Label CustomerName = new Label("Customer Name: ");
//        Label Customermobile = new Label("Customer Mobile Number: ");
//        Label Services = new Label("Services: ");
//        Label SerDate = new Label("Date: ");
//        Label Stylist = new Label("Stylist: ");
//        Label Time = new Label("Time: ");
//
//        TextField txCustomerName = new TextField();
//        TextField txMobile = new TextField();
//        ComboBox<String> CMServices = new ComboBox();
//
//        CMServices.getItems().addAll("Hair-cutting", "Hair-Styling", "Hair-Coloring", "Makeup", "Manicures", "Pedicures", "Facials and skin care treatments");
//        CMServices.setValue("Hair-cutting");
//
//        ListView lvStylist = new ListView();
//        lvStylist.getItems().add("Mona");
//        lvStylist.getItems().add("Huda");
//        lvStylist.getItems().add("Emmy");
//        DatePicker ExDate = new DatePicker();
//        ExDate.setValue(LocalDate.now());
//        ComboBox cboTime = new ComboBox();
//
//        cboTime.getItems().addAll("4:00PM", "4:00PM", "11:20AM", "6:10PM", "9:30AM");
//        cboTime.setValue("4:00PM");
//
//        GridPane form = new GridPane();
//        form.add(CustomerName, 0, 0);
//        form.add(txCustomerName, 1, 0);
//
//        form.add(Customermobile, 0, 1);
//        form.add(txMobile, 1, 1);
//
//        form.add(Services, 0, 2);
//        form.add(CMServices, 1, 2);
//
//        form.add(Stylist, 2, 2);
//        form.add(lvStylist, 3, 2);
//
//        form.add(SerDate, 0, 3);
//        form.add(ExDate, 1, 3);
//
//        form.add(Time, 0, 4);
//        form.add(cboTime, 1, 4);
//        form.setAlignment(Pos.CENTER);
//        form.setHgap(10);
//        form.setVgap(10);
//        Button ExportToFile = new Button("Export to file");
//        Button Add = new Button("Book service");
//        Button Update = new Button("Delete");
//        Button Next = new Button("Print Invoice");
//        Button Clear = new Button("Clear");
//
//        HBox buttons = new HBox(20);
//        buttons.setAlignment(Pos.CENTER);
//        buttons.getChildren().addAll(Add, Update, Clear, ExportToFile);
//
//        Label lblmsg = new Label();
//        lblmsg.setTextFill(Color.DARKRED);
//        lblmsg.setMaxHeight(30);
//        lblmsg.setMaxWidth(500);
//        lblmsg.setMinHeight(30);
//        lblmsg.setMinWidth(500);
//        lblmsg.setFont(new Font(16.0));
//        lblmsg.setAlignment(Pos.CENTER);
//        ObservableList<String> obCustomerNames = FXCollections.observableArrayList();
////"Ali" , "Mohammed", "saied", "Fahd");
//        ObservableList<String> obCustomerMobiles = FXCollections.observableArrayList();
////"64682342" , "38538465345", "23428346", "35835863");
//        ObservableList<String> obServices
//                = FXCollections.observableArrayList();
//        //"Hair-cutting", "Hair-Styling", "Hair-Coloring", "Makeup", "Manicures", "Pedicures", "Facials and skin care treatments");
//        ObservableList<String> obStylists
//                = FXCollections.observableArrayList();
//        //"Huda", "Mona", "Emmy","Hind","Lona","Majdooleen","Sonya" );
//        ObservableList<String> obDateTime = FXCollections.observableArrayList();
//        ListView lvName = new ListView(obCustomerNames);
//
//        ListView lvMobile = new ListView(obCustomerMobiles);
//
//        ListView lvService = new ListView(obServices);
//        ListView lvFoStylist = new ListView(obStylists);
//
//        ListView lvDateTime = new ListView(obDateTime);
// List<customer1> Customers = new ArrayList<>();
//        Session sessionread = HibernateUtil.getSessionFactory().openSession();
//        Query query = sessionread.createQuery("from customer1");
//        Customers = query.list();
//        sessionread.close();
//        for (customer1 c : Customers) {
//            obCustomerNames.add(c.getCustomerName());
//            obCustomerMobiles.add(String.valueOf(c.getCustomerPhoneNumber()));
//            obServices.add(c.getService());
//            obStylists.add(c.getStylist());
//            obDateTime.add(c.getDateTime());
//
//        }
//        
//        
//        
//        Add.setOnAction(e -> {
//            String stylist = (String) lvStylist.getSelectionModel().getSelectedItems().get(0);
//            String dt = ExDate.getValue().toString();
//            dt = dt + " at " + cboTime.getValue();
//            if (txCustomerName.getText().equals("") || txMobile.getText().equals("")) {
//                lblmsg.setText("Enter Customer name and Mobile phone");
//            } else {
//                boolean isnumber = true;
//                try {
//                    Long x = Long.parseLong(txMobile.getText());
//                    isnumber = true;
//                } catch (NumberFormatException ex) {
//                    isnumber = false;
//                }
//                if (!isnumber) {
//                    lblmsg.setText("Mobile number must be digit only");
//                } else if (txMobile.getText().length() != 10) {
//                    lblmsg.setText("The length of mobile number must be 10 digits");
//                } else if (obStylists.indexOf(stylist) >= 0 && (obStylists.indexOf(stylist) == obDateTime.indexOf(dt))) {
//                    lblmsg.setText("Stylist busy at this time..");
//                } else {
//                    lblmsg.setText("");
//
//                    obCustomerNames.add(txCustomerName.getText());
//                    obCustomerMobiles.add(txMobile.getText());
//                    obServices.add(CMServices.getValue());
//                    obStylists.add(
//                            (String) lvStylist.getSelectionModel().getSelectedItems().get(0));
//
//                    obDateTime.add(dt);
//
//                    customer1 c = new customer1();
//                    c.setCustomerName(txCustomerName.getText());
//                    c.setCustomerPhoneNumber(Long.parseLong(txMobile.getText()));
//                    c.setService(CMServices.getValue());
//                    c.setStylist((String) lvStylist.getSelectionModel().getSelectedItems().get(0));
//                    c.setDateTime(dt);
//
//                    Session s1 = HibernateUtil.getSessionFactory().openSession();
//                    Transaction t = s1.beginTransaction();
//
//                    s1.save(c);
//                    t.commit();
//                    s1.close();
//                }
//            }
//
//        });
//
//        ExportToFile.setOnAction(e -> {
//            try {
//                FileWriter fw = new FileWriter("out.txt");
//                for (String s : obCustomerNames) {
//                    String line = s + " " + obCustomerMobiles.get(obCustomerNames.indexOf(s)) + " "
//                            + obServices.get(obCustomerNames.indexOf(s))
//                            + " " + obStylists.get(obCustomerNames.indexOf(s))
//                            + " " + obDateTime.get(obCustomerNames.indexOf(s)) + "\n";
//                    fw.write(line);
//                }
//                fw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Beautysalon.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//        Update.setOnAction(e -> {
//            int ind = lvService.getSelectionModel().getSelectedIndex();
//            customer1 c = new customer1();
//            c.setCustomerName(obCustomerNames.get(ind));
//            c.setCustomerPhoneNumber(Long.parseLong(obCustomerMobiles.get(ind)));
//            c.setService(obServices.get(ind));
//            c.setStylist(obStylists.get(ind));
//            c.setDateTime(obDateTime.get(ind));
//            Session s1 = HibernateUtil.getSessionFactory().openSession();
//            Transaction t = s1.beginTransaction();
//
//            s1.delete(c);
//            t.commit();
//            s1.close();
//
//            obCustomerNames.remove(ind);
//            obCustomerMobiles.remove(ind);
//            obServices.remove(ind);
//            obStylists.remove(ind);
//            obDateTime.remove(ind);
//            lblmsg.setText("Customer \"" + obCustomerNames.get(ind)  + "\" was deleted."); 
//
//        });
//        Clear.setOnAction(e -> {
//            txCustomerName.setText("");
//            lvStylist.getSelectionModel().select(0);
//            CMServices.getSelectionModel().select(0);
//            lvStylist.getSelectionModel().select(0);
//            ExDate.setValue(LocalDate.now());
//            cboTime.getSelectionModel().select(0);
//
//            txMobile.setText("");
//
//        });
//
//        HBox lvs = new HBox(10);
//        lvs.setAlignment(Pos.CENTER);
//        lvs.setMaxWidth(700);
//        lvs.setMinWidth(700);
//        lvs.setMaxHeight(300);
//        lvs.setMinHeight(300);
//
//        VBox VBlvName = new VBox(10);
//
//        VBox VBlvMobile = new VBox(10);
//
//        VBox VBlvService = new VBox(10);
//        VBox VBlvStylist = new VBox(10);
//
//        VBox VBlvDateTime = new VBox(10);
//
//        VBlvName.getChildren().addAll(new Label("Customer Name: "), new ScrollPane(lvName));
//        VBlvMobile.getChildren().addAll(new Label("Customer Phone Number: "), new ScrollPane(lvMobile));
//        VBlvService.getChildren().addAll(new Label("Service: "), new ScrollPane(lvService));
//        VBlvStylist.getChildren().addAll(new Label("Stylist: "), new ScrollPane(lvFoStylist));
//        VBlvDateTime.getChildren().addAll(new Label("Date and time: "), new ScrollPane(lvDateTime));
//
//        lvs.getChildren().addAll(VBlvName, VBlvMobile, VBlvService, VBlvStylist,
//                VBlvDateTime);
//
//        HBox btSearch = new HBox(20);
//        btSearch.getChildren().add(Next);
//        btSearch.setAlignment(Pos.BOTTOM_RIGHT);
//        btSearch.setPadding(new Insets(20));
//
//        VBox controls = new VBox(20);
//        controls.setAlignment(Pos.CENTER);
//        controls.getChildren().addAll(form, lblmsg, buttons, lvs, btSearch);
//
//        BeautySalon = new Scene(controls, 800, 700);
//
//        //The Search Scene -----------------------------------------------------------------------------------------
//        VBox searchControl = new VBox();
//        Label lbltf = new Label("Enter Customer Mobile Number: ");
//
//        TextField tfSearch = new TextField();
//        Button SearchItem = new Button("Print Invoice");
//        Label msg = new Label();
//        msg.setTextFill(Color.DARKRED);
//        msg.setMaxHeight(100);
//        msg.setMaxWidth(700);
//        msg.setMinHeight(100);
//        msg.setMinWidth(700);
//        msg.setFont(new Font(20.0));
//
//        Button Back = new Button("Back");
//
//        HBox paneSearch = new HBox(lbltf, tfSearch, SearchItem);
//        paneSearch.setAlignment(Pos.TOP_LEFT);
//        paneSearch.setSpacing(10);
//
//        searchControl.getChildren().addAll(paneSearch, msg);
//        searchControl.setAlignment(Pos.TOP_LEFT);
//        searchControl.setSpacing(10);
//        Pane Invo = new Pane();
//        Invo.setStyle("-fx-border-color:black; -fx-border-width:1px; -fx-background-color:white;");
//        Invo.setPrefSize(700, 400);
//        Label lblInvo = new Label("Customer Invoice");
//        Label Invodata = new Label("");
//
//        lblInvo.setAlignment(Pos.CENTER);
//        lblInvo.setPadding(new Insets(10));
//        lblInvo.setStyle("-fx-font-size: 28px; -fx-text-fill: black;");
//        lblInvo.setPrefWidth(750);
//        Invo.getChildren().addAll(lblInvo, Invodata);
//        VBox root2 = new VBox(10);
//        root2.setPadding(new Insets(20));
//        HBox back = new HBox(10);
//        back.setAlignment(Pos.BOTTOM_RIGHT);
//        back.getChildren().add(Back);
//        root2.getChildren().addAll(searchControl, Invo, back);
//
//        SearchItem.setOnAction(e -> {
//            msg.setText("");
//            if (tfSearch.getText().equals("")) {
//                msg.setText("Enter the customer mobile number to print invoice");
//            } else {
//                boolean isnumber = true;
//                try {
//                    Long x = Long.parseLong(tfSearch.getText());
//                    isnumber = true;
//                } catch (NumberFormatException ex) {
//                    isnumber = false;
//                }
//                if (!isnumber) {
//                    msg.setText("Mobile number must be digit only");
//                } else if (tfSearch.getText().length() != 10) {
//                    msg.setText("The length of mobile number must be 10 digits");
//                } else if (obCustomerMobiles.size() == 0) {
//                    msg.setText("No customers in the list");
//                } else {
//                    String phone = tfSearch.getText();
//                    int ind = obCustomerMobiles.indexOf(phone);
//                    String name = obCustomerNames.get(ind);
//
//                    if (!obCustomerMobiles.contains(phone)) {
//                        msg.setText("The customer not found.");
//                    } else {
//                        msg.setText("");
//                        String invoic = "\n\n\n\nCustimer Name:" + name;
//                        invoic += "\nService\t\t\t Stylist\t\t Date and time\n";
//
//                        double price = 0;
//                        for (int i = 0; i < obCustomerMobiles.size(); i++) {
//
//                            if (obCustomerMobiles.get(i).equals(phone)) {
//
//                                invoic += obServices.get(i) + "\t\t ";
//                                invoic += obStylists.get(i) + "\t\t ";
//                                invoic += obDateTime.get(i) + "\n";
//                                switch (obServices.get(i)) {
//                                    case "Makeup":
//                                    case "Hair-Coloring":
//                                        price += 500;
//                                        break;
//                                    case "Hair-Styling":
//                                        price += 150;
//                                        break;
//                                    case "Hair-cutting":
//                                        price += 80;
//
//                                        break;
//                                    case "Manicures":
//
//                                        price += 100;
//                                        break;
//                                    case "Pedicures":
//                                        price += 150;
//                                        break;
//                                    case "Facials and skin care treatments":
//                                        price += 30;
//
//                                        break;
//                                }
//
//                            }
//                        }
//                        invoic += "Price:" + price;
//
//                        Invodata.setText(invoic);
//                    }
//                }
//            }
//        });
//
//        Invoice = new Scene(root2, 800, 700);
//
//        primaryStage.setTitle("Beauty Salon");
//        primaryStage.setScene(BeautySalon);
//        primaryStage.show();
//
//        Next.setOnAction(e -> {
//            primaryStage.setTitle("Invoice");
//            primaryStage.setScene(Invoice);
//        });
//        CMServices.setOnAction(e -> {
//            lvStylist.getItems().clear();
//            String serv = CMServices.getValue();
//            //   CMServices.getItems().addAll("Hair-cutting", "Hair-Styling", "Hair-Coloring", "Makeup", "Manicures", "Pedicures", "Facials and skin care treatments");
//
//            switch (serv) {
//                case "Hair-cutting":
//                case "Hair-Styling":
//                case "Hair-Coloring":
//                    lvStylist.getItems().addAll("Huda", "Mona", "Emmy");
//
//                    break;
//                case "Makeup":
//                    lvStylist.getItems().addAll("Hind", "Lona");
//
//                    break;
//                case "Manicures":
//                case "Pedicures":
//                    lvStylist.getItems().addAll("Majdooleen");
//
//                    break;
//                case "Facials and skin care treatments":
//                    lvStylist.getItems().addAll("Sonya");
//
//                    break;
//            }
//        });
//        Back.setOnAction(e -> {
//            primaryStage.setTitle("Beauty Salon");
//            primaryStage.setScene(BeautySalon);
//        });
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package beautysalon_lab8;

package beautysalon;


import beautysalon.customer1;
import beautysalon.HibernateUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author KEM003
 */
public class Beautysalon extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
 TextField tf = new TextField();
    Label msg = new Label("");
    Label Invodata = new Label("");
    Invodata.setTranslateY(100);
    Invodata.setTranslateX(50);
    Button btn = new Button("Print Invoice");
    Button btn2 = new Button("Back");
    Text text = new Text("Customer Invoice");
    text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
    
    Pane pane = new Pane();

    
    HBox hbox = new HBox(5);
    hbox.getChildren().addAll(new Label("Enter Customer Mobile Number:"), tf , btn);
    hbox.setTranslateX(30);
    
  VBox vbox = new VBox(5);
    vbox.setPadding(new Insets(20, 20, 20, 20));
    vbox.getChildren().addAll(msg,text,btn2);
    vbox.setStyle("-fx-border-color: gray;");
    vbox.setAlignment(Pos.TOP_CENTER);
    vbox.setTranslateY(80);
    vbox.setTranslateX(30);
    vbox.setPrefSize(640,400);
    btn2.setTranslateX(320);
    btn2.setTranslateY(360);
    msg.setTranslateY(-50);
    msg.setStyle("-fx-text-fill: red ;");
    msg.setScaleX(1.9);
    msg.setScaleY(1.9);

    
    pane.getChildren().addAll(hbox,vbox,Invodata);
 /////////////////////////////////Scene2////////////////////////////////////////    
       
//------------------------ the three blank Buttons ------------------------ 
        Button bt1 = new Button("Book Service");
        Button bt2 = new Button("   Delete   ");
        Button bt3 = new Button("    Clear    ");
        bt1.setStyle("-fx-background-color: thistle");
        bt2.setStyle("-fx-background-color: thistle ");
        bt3.setStyle("-fx-background-color: thistle");

        HBox bthbox = new HBox(10);
        bthbox.setTranslateY(60);
        bthbox.getChildren().addAll(bt1, bt2, bt3);
        bthbox.setAlignment(Pos.CENTER);
       
// -----------------------------------------------------------------------        
        //-------------------------Task1:observableLists-----------------------------
        ObservableList<String> obCustomerNames=FXCollections.observableArrayList();
        ObservableList<String> obCustomerMobiles=FXCollections.observableArrayList();
        ObservableList<String> obServices=FXCollections.observableArrayList();
        ObservableList<String> obStylists=FXCollections.observableArrayList();
        ObservableList<String> obDateTime=FXCollections.observableArrayList();
        // -------------------------- the list view ----------------------------------
        // --------- Customer Name
        ListView<String> vl_CustomerName = new ListView<>(obCustomerNames);    
        VBox VBlvName = new VBox(10);
        VBlvName.getChildren().addAll(new Label("Custmer Name:"), new ScrollPane(vl_CustomerName));
        vl_CustomerName.setItems(obCustomerNames);        
        
        // --------- Customer phone
        ListView<String> vl_CustomerPhone = new ListView<>(obCustomerMobiles);
        VBox VBlvPhone = new VBox(10);
        VBlvPhone.getChildren().addAll(new Label("Custmer Phone Number:"), new ScrollPane(vl_CustomerPhone));
         vl_CustomerPhone.setItems(obCustomerMobiles); 
        
        // --------- Service
        ListView<String> vl_Service = new ListView<>(obServices);
        VBox  VBlvService = new VBox(10);
        VBlvService.getChildren().addAll(new Label("Service:"), new ScrollPane(vl_Service));
        vl_Service.setItems(obServices);  
        
        // --------- Stylist
        ListView<String> vl_Stylist = new ListView<>(obStylists);
        VBox VBlvStayle = new VBox(10);
        VBlvStayle.getChildren().addAll(new Label("Stylist:"), new ScrollPane(vl_Stylist));
        vl_Stylist.setItems(obStylists);
        
        // --------- Date and Time
        ListView<String> vl_Date_Time = new ListView<>(obDateTime);
        VBox VBlvDate = new VBox(10);
        VBlvDate.getChildren().addAll(new Label("Data and time:"), new ScrollPane(vl_Date_Time));
        vl_Date_Time.setItems(obDateTime);       
    
        //--------- The position setting
        HBox vlhbox = new HBox(10);
        vlhbox.setAlignment(Pos.CENTER);
        vlhbox.setMaxWidth(700);
        vlhbox.setMinWidth(700);
        vlhbox.setMaxHeight(180);
        vlhbox.setMinHeight(180);
        vlhbox.setTranslateX(70);//25
        vlhbox.setTranslateY(120);
       // vlhbox.setPadding(new javafx.geometry.Insets(70, 10, 0, 10));
    
        vlhbox.getChildren().addAll(VBlvName, VBlvPhone, VBlvService, VBlvStayle, VBlvDate);
// -------------------------------------------------------------------------------------------------------------        


// ------------------------ Next Scene Button ------------------------
        Button Next = new Button("Print Invoice");
        Next.setTranslateX(-250);
        Next.setTranslateY(15);
        HBox nexthbox = new HBox();
        nexthbox.setPadding(new javafx.geometry.Insets(150, 10, 10, 15));
        nexthbox.getChildren().add(Next);
        nexthbox.setAlignment(Pos.CENTER_RIGHT);
        
        Button ExportToFile = new Button("ExportToFile");
          ExportToFile.setTranslateX(330);
          ExportToFile.setTranslateY(-35);
          HBox exportHBOX = new HBox();
          //exportHBOX.setPadding(new javafx.geometry.Insets(20, 10, 10, 15));
        exportHBOX.getChildren().add(ExportToFile);
        exportHBOX.setAlignment(Pos.CENTER);;
// --------------------------------------------------------------------
    TextField cusName = new TextField();
        cusName.setTranslateX(70);
        Label name = new Label("Customer Name :", cusName);
        name.setContentDisplay(ContentDisplay.RIGHT);
        name.setTranslateY(40);
         
        TextField cusPhone = new TextField();
        cusPhone.setTranslateX(15);
        Label phone = new Label("Customer Mobile Number :", cusPhone);
        phone.setContentDisplay(ContentDisplay.RIGHT);
        phone.setTranslateY(40);
        
        String [] items1 = {"Hair-Cutting","Hair-Styling","Hair-Coloring","Makeup","Manicures","Pedicures","Facials and skin care treatments"};
        Label Services = new Label("Service :");
        ComboBox ServiceList = new ComboBox();
        ServiceList.setTranslateX(100);
        ServiceList.getItems().addAll(items1);
        ServiceList.setValue("                    Hair-Cutting ");
        
        String [] items2= {"   Huda","   Mona","   Emmy","   Hind","   Lona","   Majdooleen","   Sonya"};
        ListView StylistList = new ListView();
        StylistList.getItems().addAll(items2);
        StylistList.setPrefHeight(100);
        StylistList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        StylistList.setTranslateY(40);
        
        Label Stylist = new Label("Stylist :", StylistList);
        Stylist.setContentDisplay(ContentDisplay.RIGHT);
        Stylist.setTranslateY(0);
        Stylist.setTranslateX(110);
        
        HBox box = new HBox(15);
        box.getChildren().addAll(Services, ServiceList, Stylist, StylistList);
        box.setAlignment(Pos.CENTER);
        box.setTranslateX(-107);//-80
        box.setTranslateY(30);
        
        
        DatePicker datePicker = new DatePicker();
        datePicker.setTranslateX(125);
        Label Date = new Label("Date :", datePicker);
        Date.setContentDisplay(ContentDisplay.RIGHT);
        Date.setTranslateY(30);
        
        String [] items3 = {"   4:00PM","   6:00PM","   8:00PM","   10:00PM"};
        ComboBox DateList = new ComboBox();
        DateList.getItems().addAll(items3);
        DateList.setValue("    4:00PM");
        DateList.setTranslateX(125);
        Label Time = new Label("Time :", DateList);
        Time.setContentDisplay(ContentDisplay.RIGHT);
        Time.setTranslateY(40);
        
        VBox part1 = new VBox(15);
        part1.getChildren().addAll(name, cusName, phone, cusPhone, box, Date, Time);
        part1.setTranslateX(40);
       
//-------------------------Task2---------------------------------------------
 Label lblmsg = new Label();
lblmsg.setTranslateX(300);
  lblmsg.setTranslateY(-290);
  lblmsg.setStyle("-fx-text-fill: red ;");
  lblmsg.setScaleX(1.9);
 lblmsg.setScaleY(1.9);
        
StylistList.getItems().clear();
             ServiceList.setOnAction ((Event event) -> {

// ------------------------ get the selected service ------------------------
            String selectedService = (String) ServiceList.getSelectionModel().getSelectedItem();
// ------------------------ show the stylist for the selected service ------------------------
         
        if(selectedService.equals("Makeup")){
            StylistList.getItems().setAll("   Hind", "   Lona");}

        else if(selectedService.equals("Hair-Cutting")
        ||selectedService.equals("Hair-Styling")
        ||selectedService.equals("Hair-Coloring")){       
            StylistList.getItems().setAll("   Huda", "   Mona", "   Emmy");}         
    
        else if(selectedService.equals("Manicures")
        ||selectedService.equals("Pedicures")){
            StylistList.getItems().setAll("   Majdooleen");}
   
        else if(selectedService.equals("Facials and skin care treatments")){
            StylistList.getItems().setAll("   Sonya");}
        
        
});

     bt1.setOnAction((ActionEvent event) -> {
            /**/
            customer1 customerobj = new customer1();
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            session1 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session1.beginTransaction();
            

          String selectedStylist = (String) StylistList.getSelectionModel().getSelectedItems().get(0);
           String selectedDate = datePicker.getValue().toString();
           selectedDate = selectedDate + " at " + DateList.getValue();

           if (cusName.getText().equals("") || cusPhone.getText().equals("")) {
                lblmsg.setText("Enter Customer name and Mobile phone");
            } else {
                boolean isnumber = true;
                try {
                    Long x = Long.parseLong(cusPhone.getText());
                    isnumber = true;
                } catch (NumberFormatException ex) {
                    isnumber = false;
                }
                if (!isnumber) {
                    lblmsg.setText("Mobile number must be digit only");
                } else if (cusPhone.getText().length() != 10) {
                    lblmsg.setText("The length of mobile number must be 10 digits");
                } else if (obStylists.indexOf(selectedStylist) >= 0 && (obStylists.indexOf(selectedStylist) == obDateTime.indexOf(selectedDate))) {
                    lblmsg.setText("Stylist busy at this time..");
                } else {
                    lblmsg.setText("");
                    obCustomerNames.add(cusName.getText());
                    obCustomerMobiles.add(cusPhone.getText());
                    obServices.add((String)ServiceList.getValue());
                    obStylists.add(
                            (String) StylistList.getSelectionModel().getSelectedItems().get(0));

                    obDateTime.add(selectedDate);
                    
                customerobj.setCustomerName(cusName.getText());
                customerobj.setCustomerPhoneNumber(Integer.parseInt(cusPhone.getText()));
                customerobj.setService((String)ServiceList.getValue());
                customerobj.setStylist((String) StylistList.getSelectionModel().getSelectedItems().get(0));
                customerobj.setDateTime(selectedDate);
                
                session1.save(customerobj);
                session1.getTransaction().commit();
                session1.close();
                }
            }

        });
  
     
        List<customer1> Customers = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from customer1");
        Customers = query.list();
        session.close();
        for (customer1 customerobj : Customers) {
            obCustomerNames.add(customerobj.getCustomerName());
            obCustomerMobiles.add(String.valueOf(customerobj.getCustomerPhoneNumber()));
            obServices.add(customerobj.getService());
            obStylists.add(customerobj.getStylist());
            obDateTime.add(customerobj.getDateTime());

        }
//Task 2.2: Add an ActionEvent on the Delete button:
    bt2.setOnAction((e)-> {       
      int selectedService = vl_Service.getSelectionModel().getSelectedIndex();
       
        customer1 customerobj = new customer1();
        customerobj.setCustomerName(obCustomerNames.get(selectedService));
        customerobj.setCustomerPhoneNumber(Integer.parseInt(obCustomerMobiles.get(selectedService)));
        customerobj.setService(obServices.get(selectedService));
        customerobj.setStylist(obStylists.get(selectedService));
        customerobj.setDateTime(obDateTime.get(selectedService));
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx= session2.beginTransaction();
                    
        session2.delete(customerobj);
        session2.getTransaction().commit();
        session2.close();
        
      lblmsg.setText("Customer \"" + obCustomerNames.get(selectedService)  + "\" was deleted.");  
    
    obCustomerNames.remove(selectedService);
    obCustomerMobiles.remove(selectedService);
    obServices.remove(selectedService);
    obStylists.remove(selectedService);
    obDateTime.remove(selectedService);
    
    

 });
//Task 2.3: Add an ActionEvent on the “Clear” button:
    bt3.setOnAction((e)-> {
        
    cusName.setText("");
    cusPhone.setText("");
    ServiceList.setValue("                    Hair-Cutting ");
    StylistList.getItems().clear();
    datePicker.setValue(null); 
     DateList.setValue("    4:00PM");
 
 });
             
         
//-------------------------Task3---------------------------------------------
// Event-Driven Programming for the “Invoice” screen //////

    btn.setOnAction(e -> {
             if (tf.getText().equals("")) {
                msg.setText("Enter the customer mobile number to print invoice");
            } else  {
                boolean isnumber = true;
                try {
                    Long x = Long.parseLong(tf.getText());
                    isnumber = true;
                } catch (NumberFormatException ex) {
                    isnumber = false;
                }
                if (!isnumber) {
                    msg.setText("Mobile number must be digit only");
                } else if (tf.getText().length() != 10) {
                    msg.setText("The length of mobile number must be 10 digits");
                } else if (!(vl_CustomerPhone.getItems().contains(tf.getText()))) {
                    msg.setText("No customers in the list");
                } else {
                   // String ph = tf.getText();
                    int ind = obCustomerMobiles.indexOf(tf.getText());
                    String n = obCustomerNames.get(ind);

                   if (vl_CustomerPhone.getItems().contains(tf.getText())) {
                       //msg.setText("The customer not found.");
                  
                         msg.setText("");
                        String invoic = "\n\n\n\nCustimer Name:" + n;
                        invoic += "\nService\t\t\t Stylist\t\t Date and time\n";
                       
                        double price = 0;
                        for (int i = 0; i < obCustomerMobiles.size(); i++) {

                            if (obCustomerMobiles.get(i).equals(tf.getText())) {

                                invoic += obServices.get(i) + "\t\t ";
                                invoic += obStylists.get(i) + "\t\t ";
                                invoic += obDateTime.get(i)+ "\n";
                                switch (obServices.get(i)) {
                                    case "Makeup":
                                    case "Hair-Coloring":
                                       price+=500;
                                        break;
                                    case "Hair-Styling":
                                         price+=150;
                                        break;
                                    case "Hair-cutting":
                                         price+=80;

                                        break;
                                    case "Manicures":

                                         price+=100;
                                        break;
                                    case "Pedicures":
                                         price+=150;
                                        break;
                                    case "Facials and skin care treatments":
                                        price+=30;

                                        break;
                                }

                            }
                        }
                        invoic += "Price:" + price;

                        Invodata.setText(invoic);
                    }
                }
            }
        });
        ;



        Next.setOnAction(event -> {
            Beautysalon invoice = new Beautysalon();
            invoice.start(primaryStage);  
            primaryStage.setTitle("Invoice");
        });
         
        VBox root = new VBox();
        
        root.getChildren().addAll( part1,bthbox,vlhbox,nexthbox, lblmsg,ExportToFile);
        
        Scene BeautySalon = new Scene(root, 850, 740);
        Scene Invoice = new Scene(pane, 850, 740);
        
        BeautySalon.setFill(Color.VIOLET);
        
        btn2.setOnAction(e->{
           
            primaryStage.setScene(BeautySalon);
            primaryStage.setTitle("BeautySalon");
             
        });
        
       Next.setOnAction(e->{
             primaryStage.setScene(Invoice);
            primaryStage.setTitle("Invoice");
        });
       
       ExportToFile.setOnAction(e->{

     // Task 4 Add an ActionEvent to the ExportToFile button
        
            try {
                FileWriter fw = new FileWriter("customerInfo.txt");
                for (String s : obCustomerNames) {
                    String line = s + " " + obCustomerMobiles.get(obCustomerNames.indexOf(s)) + " "
                            + obServices.get(obCustomerNames.indexOf(s)) + " " + obStylists.get(obCustomerNames.indexOf(s))
                            + obDateTime.get(obCustomerNames.indexOf(s)) + " " + "\n";
                    fw.write(line);
                }
                fw.close();
            } catch (IOException ex) {
           System.err.println("File doesn't exist");
            }
       



    });
       // Set the first scene as the primary stage

 primaryStage.setTitle("BeautySalon");
 
       primaryStage.setScene(BeautySalon);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}