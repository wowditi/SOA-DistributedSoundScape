
/**
 * UploadCallbackServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
    package org.example.www.uploadcallbackservice.server;
    /**
     *  UploadCallbackServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface UploadCallbackServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param deleteSongResponse
         */

        
                public void deleteSongCallback
                (
                  org.example.www.uploadserviceelements.DeleteSongResponse deleteSongResponse
                 )
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param uploadSongResponse
         */

        
                public void uploadSongCallback
                (
                  org.example.www.uploadserviceelements.UploadSongResponse uploadSongResponse
                 )
            ;
        
         }
    