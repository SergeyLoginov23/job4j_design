package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import ru.job4j.serialization.json.Landmark;

import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityXml {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean capital;

    private LandmarkXml landmark;
    @XmlElementWrapper(name = "streets")
    @XmlElement(name = "street")
    private String[] streets;

    public CityXml() { }

    public CityXml(boolean capital, int age, String name, LandmarkXml landmark, String[] streets) {
        this.capital = capital;
        this.age = age;
        this.name = name;
        this.landmark = landmark;
        this.streets = streets;
    }

    @Override
    public String toString() {
        return "City{"
                + "name=" + name
                + ", age=" + age
                + ", capital=" + capital
                + ", landmark=" + landmark
                + ", streets=" + Arrays.toString(streets)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final  CityXml city = new CityXml(false, 30, "Vladimir", new LandmarkXml("Golden Gate", 862),
                new String[] {"Lenina", "Pobedy", "Mira"});

        JAXBContext context = JAXBContext.newInstance(CityXml.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(city, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}