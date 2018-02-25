
/**
 * RegistrationServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
    package org.example.www.registrationservice.server;
    /**
     *  RegistrationServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface RegistrationServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param setSpeakerLocationRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.registrationserviceelements.SetSpeakerLocationResponse setSpeakerLocation
                (
                  org.example.www.registrationserviceelements.SetSpeakerLocationRequestE setSpeakerLocationRequest
                 )
            throws ErrorMessage;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param getSpeakersRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.registrationserviceelements.GetSpeakersResponseE getSpeakers
                (
                  org.example.www.registrationserviceelements.GetSpeakersRequestE getSpeakersRequest
                 )
            throws ErrorMessage;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param registerUserRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.registrationserviceelements.RegisterUserResponse registerUser
                (
                  org.example.www.registrationserviceelements.RegisterUserRequestE registerUserRequest
                 )
            throws ErrorMessage;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param removeUserRequest
         */

        
                public void removeUser
                (
                  org.example.www.registrationserviceelements.RemoveUserRequestE removeUserRequest
                 )
            ;
        
         }
    