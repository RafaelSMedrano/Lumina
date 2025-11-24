package com.lumina.server.models;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private String name;
    private List<String> objects;
    private List<String> people;

    public Area(String name) {
        this.name = name;
        this.objects = new ArrayList<>();
        this.people = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getObjects() {
        return objects;
    }

    public List<String> getPeople() {
        return people;
    }

    public void addObject(String object) {
        objects.add(object);
    }

    public void addPerson(String person) {
        people.add(person);
    }

    public void describe() {
        System.out.println("You are in: " + name);
        if (people.isEmpty() && objects.isEmpty()) {
            System.out.println("The area is empty.");
        } else {
            if (!people.isEmpty()) {
                System.out.println("People in the area: " + String.join(", ", people));
            }
            if (!objects.isEmpty()) {
                System.out.println("Objects in the area: " + String.join(", ", objects));
            }
        }
    }
}
