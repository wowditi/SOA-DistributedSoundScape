
/**
 * UploadServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
    package org.example.www.uploadservice.service;
    /**
     *  UploadServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface UploadServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param uploadSongRequest
         */

        
                public void uploadSong
                (
                  org.example.www.uploadserviceelements.UploadSongRequestE uploadSongRequest
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param isSongLoadedRequest
             * @throws ErrorMessage : 
         */

        
                public org.example.www.uploadserviceelements.IsSongLoadedResponse isSongLoaded
                (
                  org.example.www.uploadserviceelements.IsSongLoadedRequestE isSongLoadedRequest
                 )
            throws ErrorMessage;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param deleteSongRequest
         */

        
                public void deleteSong
                (
                  org.example.www.uploadserviceelements.DeleteSongRequestE deleteSongRequest
                 )
            ;
        
         }
    