
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:04:10 GMT)
 */

        
            package org.example.www.uploadservice;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "GeneralDevice".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.GeneralDevice.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "Link".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.Link.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "Location".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.Location.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "LinkType".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.LinkType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SpeakerDevice".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SpeakerDevice.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SpeakerDeviceArray".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SpeakerDeviceArray.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "ASyncResponse".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.ASyncResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/UploadServiceElements/".equals(namespaceURI) &&
                  "IsSongLoadedRequest".equals(typeName)){
                   
                            return  org.example.www.uploadserviceelements.IsSongLoadedRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "IPv4Address".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.IPv4Address.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/UploadServiceElements/".equals(namespaceURI) &&
                  "UploadSongRequest".equals(typeName)){
                   
                            return  org.example.www.uploadserviceelements.UploadSongRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/UploadServiceElements/".equals(namespaceURI) &&
                  "DeleteSongRequest".equals(typeName)){
                   
                            return  org.example.www.uploadserviceelements.DeleteSongRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "Port".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.Port.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SoundscapeId".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SoundscapeId.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    