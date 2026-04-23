package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "landmark")
public class LandmarkXml {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;

    public LandmarkXml() {
    }

    public LandmarkXml(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Landmark{"
                + "name='" + name + '\''
                + "age='" + age + '\''
                + '}';
    }
}
