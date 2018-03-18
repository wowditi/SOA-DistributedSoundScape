
/**
 * ControlServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
        package org.example.www.controlservice.server;

        /**
        *  ControlServiceMessageReceiverInOut message receiver
        */

        public class ControlServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ControlServiceSkeletonInterface skel = (ControlServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("setSoundScapeSourceLayout".equals(methodName)){
                
                org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse setSoundScapeSourceLayoutResponse13 = null;
	                        org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE wrappedParam =
                                                             (org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               setSoundScapeSourceLayoutResponse13 =
                                                   
                                                   
                                                         skel.setSoundScapeSourceLayout(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), setSoundScapeSourceLayoutResponse13, false, new javax.xml.namespace.QName("http://www.example.org/ControlService/",
                                                    "setSoundScapeSourceLayout"));
                                    } else 

            if("processPlaybackCommand".equals(methodName)){
                
                org.example.www.controlserviceelements.ProcessPlaybackCommandResponse processPlaybackCommandResponse15 = null;
	                        org.example.www.controlserviceelements.ProcessPlaybackCommandRequest wrappedParam =
                                                             (org.example.www.controlserviceelements.ProcessPlaybackCommandRequest)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.controlserviceelements.ProcessPlaybackCommandRequest.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               processPlaybackCommandResponse15 =
                                                   
                                                   
                                                         skel.processPlaybackCommand(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), processPlaybackCommandResponse15, false, new javax.xml.namespace.QName("http://www.example.org/ControlService/",
                                                    "processPlaybackCommand"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        } catch (ErrorMessage e) {

            msgContext.setProperty(org.apache.axis2.Constants.FAULT_NAME,"ErrorMessage");
            org.apache.axis2.AxisFault f = createAxisFault(e);
            if (e.getFaultMessage() != null){
                f.setDetail(toOM(e.getFaultMessage(),false));
            }
            throw f;
            }
        
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.controlserviceelements.ErrorMessage param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.controlserviceelements.ErrorMessage.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.controlserviceelements.ProcessPlaybackCommandRequest param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.controlserviceelements.ProcessPlaybackCommandRequest.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.controlserviceelements.ProcessPlaybackCommandResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.controlserviceelements.ProcessPlaybackCommandResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse wrapSetSoundScapeSourceLayout(){
                                org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse wrappedElement = new org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.controlserviceelements.ProcessPlaybackCommandResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.controlserviceelements.ProcessPlaybackCommandResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.controlserviceelements.ProcessPlaybackCommandResponse wrapProcessPlaybackCommand(){
                                org.example.www.controlserviceelements.ProcessPlaybackCommandResponse wrappedElement = new org.example.www.controlserviceelements.ProcessPlaybackCommandResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.example.www.controlserviceelements.ErrorMessage.class.equals(type)){
                
                        return org.example.www.controlserviceelements.ErrorMessage.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.controlserviceelements.ProcessPlaybackCommandRequest.class.equals(type)){
                
                        return org.example.www.controlserviceelements.ProcessPlaybackCommandRequest.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.controlserviceelements.ProcessPlaybackCommandResponse.class.equals(type)){
                
                        return org.example.www.controlserviceelements.ProcessPlaybackCommandResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE.class.equals(type)){
                
                        return org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse.class.equals(type)){
                
                        return org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    