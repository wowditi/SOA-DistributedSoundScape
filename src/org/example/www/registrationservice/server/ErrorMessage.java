
/**
 * ErrorMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

package org.example.www.registrationservice.server;

public class ErrorMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1521371435955L;
    
    private org.example.www.registrationserviceelements.ErrorMessage faultMessage;

    
        public ErrorMessage() {
            super("ErrorMessage");
        }

        public ErrorMessage(java.lang.String s) {
           super(s);
        }

        public ErrorMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ErrorMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.example.www.registrationserviceelements.ErrorMessage msg){
       faultMessage = msg;
    }
    
    public org.example.www.registrationserviceelements.ErrorMessage getFaultMessage(){
       return faultMessage;
    }
}
    