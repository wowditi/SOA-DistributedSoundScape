
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:04:10 GMT)
 */

        
            package org.example.www.controlservice;
        
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
                  "VolumeLevel".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.VolumeLevel.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SoundScapeSourceLayout".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SoundScapeSourceLayout.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "GeneralDevice".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.GeneralDevice.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "Location".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.Location.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "IPv4Address".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.IPv4Address.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "PlaybackCommand".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.PlaybackCommand.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "ChannelLayout".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.ChannelLayout.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "Port".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.Port.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/ControlServiceElements/".equals(namespaceURI) &&
                  "SetSoundScapeSourceLayoutRequest".equals(typeName)){
                   
                            return  org.example.www.controlserviceelements.SetSoundScapeSourceLayoutRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "ChannelNumber".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.ChannelNumber.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SoundscapeId".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SoundscapeId.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    