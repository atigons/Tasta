/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tasta.gui;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Winston
 */
public class LoginController implements Initializable {
    public LoginModel loginModel=new LoginModel();
   /* private static int cust_id;*/
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField textemail;

    @FXML
    private JFXPasswordField textpass;
    
    @FXML
    private Label alertLabel;
    public static int cust_id;
    
    
  @FXML
    public void exitScreen(ActionEvent event){
        System.exit(0);
    }
     @FXML
      public void Return(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
	}
      @FXML
     public void MenuScreen(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("Menu.fxml"));
                primaryStage.setTitle("Welcome To Tasta Menu");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(loginModel.isDbConnected()){
             System.out.println("Db connected");
        }else{
             System.out.println("Db not connected");
        }
    } 
    
     public static void CustomerId(int cusst){
        cust_id=LoginModel.customer_id;
        
    }
    public void Login(ActionEvent event){
     try {
            if(loginModel.isLogin(textemail.getText(), textpass.getText())){
                infoBox("Login Successfull "+cust_id,null,"Success" );
                
                Node node = (Node)event.getSource();
                Stage primaryStage =new Stage();
               Stage dialogStage = (Stage) node.getScene().getWindow();
               dialogStage.close();
               Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Menu.fxml")));
		primaryStage.setScene(scene);
                
               dialogStage.setScene(scene);
               dialogStage.show();
                
                
            }else{
                infoBox("Enter correct email and password",null,"Failed" );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            alertLabel.setText("Enter correct email and password");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void ForgotPassword(ActionEvent event){ 
    String host="atig.ons@esprit.tn";  //← my email address
        final String user="atig.ons@esprit.tn";//← my email address
        final String password="E13258828";//change accordingly   //← my email password

        String to=textemail.getText();//→ the EMAIL i want to send TO →

        // session object
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" NEW PASSWORD !!! ");
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
            double v= Math.random();
            message.setContent(
                    ""+v+"<h1 style =\"color:red\" >This is the new password !! </h1> <br/> <img width=\"50%\" height=\"50%\" src=https://i.imgur.com/iYcBkOf.png>",
                    "text/html");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {e.printStackTrace();}

       
    }
     public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
   
    
}