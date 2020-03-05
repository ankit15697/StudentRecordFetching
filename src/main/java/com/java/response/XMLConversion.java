package com.java.response;
import com.fasterxml.jackson.xml.XmlMapper;

import java.io.IOException;

public class XMLConversion {
        public static String convert(Object data) throws IOException {
            XmlMapper mapper = new XmlMapper();
            try
            {

                String xml = mapper.writeValueAsString(data);
                return xml;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
