
/**
 * ControlServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
    package org.example.www.controlservice.service;
    /**
     *  ControlServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface ControlServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param setSoundScapeSourceLayoutRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.controlserviceelements.SetSoundScapeSourceLayoutResponse setSoundScapeSourceLayout
                (
                  org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequestE setSoundScapeSourceLayoutRequest
                 )
            throws ErrorMessage;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param processPlaybackCommandRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.controlserviceelements.ProcessPlaybackCommandResponse processPlaybackCommand
                (
                  org.example.www.controlserviceelements.ProcessPlaybackCommandRequestE processPlaybackCommandRequest
                 )
            throws ErrorMessage;
        
         }
    