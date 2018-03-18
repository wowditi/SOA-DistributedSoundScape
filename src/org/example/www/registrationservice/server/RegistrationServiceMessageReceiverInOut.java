
/**
 * RegistrationServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
        package org.example.www.registrationservice.server;

        /**
        *  RegistrationServiceMessageReceiverInOut message receiver
        */

        public class RegistrationServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        RegistrationServiceSkeletonInterface skel = (RegistrationServiceSkeletonInterface)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("addSpeaker".equals(methodName)){
                
                org.example.www.registrationserviceelements.AddSpeakerResponse addSpeakerResponse35 = null;
	                        org.example.www.registrationserviceelements.AddSpeakerRequestE wrappedParam =
                                                             (org.example.www.registrationserviceelements.AddSpeakerRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.registrationserviceelements.AddSpeakerRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               addSpeakerResponse35 =
                                                   
                                                   
                                                         skel.addSpeaker(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), addSpeakerResponse35, false, new javax.xml.namespace.QName("http://www.example.org/RegistrationService/",
                                                    "addSpeaker"));
                                    } else 

            if("setSpeakerLocation".equals(methodName)){
                
                org.example.www.registrationserviceelements.SetSpeakerLocationResponse setSpeakerLocationResponse37 = null;
	                        org.example.www.registrationserviceelements.SetSpeakerLocationRequestE wrappedParam =
                                                             (org.example.www.registrationserviceelements.SetSpeakerLocationRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.registrationserviceelements.SetSpeakerLocationRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               setSpeakerLocationResponse37 =
                                                   
                                                   
                                                         skel.setSpeakerLocation(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), setSpeakerLocationResponse37, false, new javax.xml.namespace.QName("http://www.example.org/RegistrationService/",
                                                    "setSpeakerLocation"));
                                    } else 

            if("getSpeakers".equals(methodName)){
                
                org.example.www.registrationserviceelements.GetSpeakersResponseE getSpeakersResponse39 = null;
	                        org.example.www.registrationserviceelements.GetSpeakersRequestE wrappedParam =
                                                             (org.example.www.registrationserviceelements.GetSpeakersRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.registrationserviceelements.GetSpeakersRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               getSpeakersResponse39 =
                                                   
                                                   
                                                         skel.getSpeakers(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), getSpeakersResponse39, false, new javax.xml.namespace.QName("http://www.example.org/RegistrationService/",
                                                    "getSpeakers"));
                                    } else 

            if("registerUser".equals(methodName)){
                
                org.example.www.registrationserviceelements.RegisterUserResponse registerUserResponse41 = null;
	                        org.example.www.registrationserviceelements.RegisterUserRequestE wrappedParam =
                                                             (org.example.www.registrationserviceelements.RegisterUserRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.registrationserviceelements.RegisterUserRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               registerUserResponse41 =
                                                   
                                                   
                                                         skel.registerUser(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), registerUserResponse41, false, new javax.xml.namespace.QName("http://www.example.org/RegistrationService/",
                                                    "registerUser"));
                                    } else 

            if("removeSpeaker".equals(methodName)){
                
                org.example.www.registrationserviceelements.RemoveSpeakerResponse removeSpeakerResponse43 = null;
	                        org.example.www.registrationserviceelements.RemoveSpeakerRequestE wrappedParam =
                                                             (org.example.www.registrationserviceelements.RemoveSpeakerRequestE)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.registrationserviceelements.RemoveSpeakerRequestE.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               removeSpeakerResponse43 =
                                                   
                                                   
                                                         skel.removeSpeaker(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), removeSpeakerResponse43, false, new javax.xml.namespace.QName("http://www.example.org/RegistrationService/",
                                                    "removeSpeaker"));
                                    
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
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.AddSpeakerRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.AddSpeakerRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.AddSpeakerResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.AddSpeakerResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.ErrorMessage param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.ErrorMessage.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.SetSpeakerLocationRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.SetSpeakerLocationRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.SetSpeakerLocationResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.SetSpeakerLocationResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.GetSpeakersRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.GetSpeakersRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.GetSpeakersResponseE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.GetSpeakersResponseE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.RegisterUserRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.RegisterUserRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.RegisterUserResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.RegisterUserResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.RemoveUserRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.RemoveUserRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.RemoveSpeakerRequestE param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.RemoveSpeakerRequestE.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.registrationserviceelements.RemoveSpeakerResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.registrationserviceelements.RemoveSpeakerResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.registrationserviceelements.AddSpeakerResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.registrationserviceelements.AddSpeakerResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.registrationserviceelements.AddSpeakerResponse wrapAddSpeaker(){
                                org.example.www.registrationserviceelements.AddSpeakerResponse wrappedElement = new org.example.www.registrationserviceelements.AddSpeakerResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.registrationserviceelements.SetSpeakerLocationResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.registrationserviceelements.SetSpeakerLocationResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.registrationserviceelements.SetSpeakerLocationResponse wrapSetSpeakerLocation(){
                                org.example.www.registrationserviceelements.SetSpeakerLocationResponse wrappedElement = new org.example.www.registrationserviceelements.SetSpeakerLocationResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.registrationserviceelements.GetSpeakersResponseE param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.registrationserviceelements.GetSpeakersResponseE.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.registrationserviceelements.GetSpeakersResponseE wrapGetSpeakers(){
                                org.example.www.registrationserviceelements.GetSpeakersResponseE wrappedElement = new org.example.www.registrationserviceelements.GetSpeakersResponseE();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.registrationserviceelements.RegisterUserResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.registrationserviceelements.RegisterUserResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.registrationserviceelements.RegisterUserResponse wrapRegisterUser(){
                                org.example.www.registrationserviceelements.RegisterUserResponse wrappedElement = new org.example.www.registrationserviceelements.RegisterUserResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.registrationserviceelements.RemoveSpeakerResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.registrationserviceelements.RemoveSpeakerResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.registrationserviceelements.RemoveSpeakerResponse wrapRemoveSpeaker(){
                                org.example.www.registrationserviceelements.RemoveSpeakerResponse wrappedElement = new org.example.www.registrationserviceelements.RemoveSpeakerResponse();
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
        
                if (org.example.www.registrationserviceelements.AddSpeakerRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.AddSpeakerRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.AddSpeakerResponse.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.AddSpeakerResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.ErrorMessage.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.ErrorMessage.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.GetSpeakersRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.GetSpeakersRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.GetSpeakersResponseE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.GetSpeakersResponseE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.RegisterUserRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.RegisterUserRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.RegisterUserResponse.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.RegisterUserResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.RemoveSpeakerRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.RemoveSpeakerRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.RemoveSpeakerResponse.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.RemoveSpeakerResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.RemoveUserRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.RemoveUserRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.SetSpeakerLocationRequestE.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.SetSpeakerLocationRequestE.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.registrationserviceelements.SetSpeakerLocationResponse.class.equals(type)){
                
                        return org.example.www.registrationserviceelements.SetSpeakerLocationResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    