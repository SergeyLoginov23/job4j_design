package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        CityXml city = new CityXml(false, 30, "Vladimir", new LandmarkXml("Golden Gate", 862),
                new String[] {"Lenina", "Pobedy", "Mira"});
        JAXBContext context = JAXBContext.newInstance(CityXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(city, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            CityXml result = (CityXml) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}