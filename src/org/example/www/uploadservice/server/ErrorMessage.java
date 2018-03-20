
/**
 * ErrorMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */

package org.example.www.uploadservice.server;

public class ErrorMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1521552309517L;
    
    private org.example.www.uploadserviceelements.ErrorMessage faultMessage;

    
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
    

    public void setFaultMessage(org.example.www.uploadserviceelements.ErrorMessage msg){
       faultMessage = msg;
    }
    
    public org.example.www.uploadserviceelements.ErrorMessage getFaultMessage(){
       return faultMessage;
    }
}
    