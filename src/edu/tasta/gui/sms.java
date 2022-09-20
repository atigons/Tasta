/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tasta.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 *
 * @author saife
 */
public class sms {
      String ACCOUNT_SID = "AC659b1752f6dca52ba8fe493440877fc3"; //Find your Account Sid and Auth Token at https://www.twilio.com/console
public static final String AUTH_TOKEN = "3bd4d2f529e72a5245df4d8ebe25880c";

    public sms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        
    }

 public void msg(){
String messageBody = "your order has been delivered :) ";
        //String to = jTextFieldTo.getText().toString();
       

       //for(int i=0; i<to.length; i++)
     
       
        Message message = Message.creator(
            new com.twilio.type.PhoneNumber("+21697805023"),                //Recipient(s)
            new com.twilio.type.PhoneNumber("+19707167376"),    //Sender Phone No. - Find your Twilio phone number at https://www.twilio.com/console
            messageBody)

        .create();
        System.out.println(message.getBody());
        
       
 }
 
 
 
    
    
}
