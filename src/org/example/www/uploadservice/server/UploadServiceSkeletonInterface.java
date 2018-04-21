
/**
 * UploadServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:03:39 GMT)
 */
    package org.example.www.uploadservice.server;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.apache.axis2.AxisFault;

/**
     *  UploadServiceSkeletonInterface java skeleton interface for the axisService
     */
    public interface UploadServiceSkeletonInterface {
     
         
        /**
         * Auto generated method signature
         * 
                                    * @param uploadSongRequest
         * @throws SQLException 
         * @throws AxisFault 
         * @throws RemoteException 
         */

        
                public void uploadSong
                (
                  org.example.www.uploadserviceelements.UploadSongRequestE uploadSongRequest
                 ) throws SQLException, AxisFault, RemoteException
            ;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param isSongLoadedRequest
             * @throws ErrorMessage : 
         * @throws SQLException 
         */

        
                public org.example.www.uploadserviceelements.IsSongLoadedResponse isSongLoaded
                (
                  org.example.www.uploadserviceelements.IsSongLoadedRequestE isSongLoadedRequest
                 )
            throws ErrorMessage, SQLException;
        
         
        /**
         * Auto generated method signature
         * 
                                    * @param deleteSongRequest
         * @throws SQLException 
         */

        
                public void deleteSong
                (
                  org.example.www.uploadserviceelements.DeleteSongRequestE deleteSongRequest
                 ) throws SQLException
            ;
        
         }
    