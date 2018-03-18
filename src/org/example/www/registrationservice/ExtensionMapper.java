
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.4  Built on : Dec 28, 2015 (10:04:10 GMT)
 */

        
            package org.example.www.registrationservice;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "RemoveSpeakerRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.RemoveSpeakerRequest.Factory.parse(reader);
                        

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
                  "SpeakerDevice".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SpeakerDevice.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "SpeakerDeviceArray".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.SpeakerDeviceArray.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "RegisterUserRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.RegisterUserRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "RemoveUserRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.RemoveUserRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "GetSpeakersRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.GetSpeakersRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "SetSpeakerLocationRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.SetSpeakerLocationRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "GetSpeakersResponse".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.GetSpeakersResponse.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/RegistrationServiceElements/".equals(namespaceURI) &&
                  "AddSpeakerRequest".equals(typeName)){
                   
                            return  org.example.www.registrationserviceelements.AddSpeakerRequest.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://www.example.org/SoundScapeDataTypes/".equals(namespaceURI) &&
                  "IPv4Address".equals(typeName)){
                   
                            return  org.example.www.soundscapedatatypes.IPv4Address.Factory.parse(reader);
                        

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
    