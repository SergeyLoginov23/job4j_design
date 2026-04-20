package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 35;
        long hight = 182L;
        double weight = 78.8;
        float money = 155.55F;
        char sex = 'M';
        boolean knowsJava = true;
        byte sisters = 1;
        short dogs = 1;
        LOG.debug("User info age : {}, hight : {}, weight : {}, money : {}, sex : {}, knowsJava : {}, sisters : {}, "
                + "dogs : {}", age, hight, weight, money, sex, knowsJava, sisters, dogs);
    }
}