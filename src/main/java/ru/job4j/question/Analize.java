package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, User> previousMap = new HashMap<>();
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user.getId(), user);
        }
        for (User user : current) {
            currentMap.put(user.getId(), user);
        }
        for (User oldUser : previous) {
            User newUser = currentMap.get(oldUser.getId());
            if (newUser == null) {
                info.incrementDeleted();
            } else if (!Objects.equals(oldUser.getName(), newUser.getName())) {
                info.incrementChanged();
            }
        }
        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                info.incrementAdded();
            }
        }
        return info;
    }

}