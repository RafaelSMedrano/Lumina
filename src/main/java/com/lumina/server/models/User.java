package com.lumina.server.models;

public class User {
    private String name;
    private Area currentArea;
    private Area bornArea;
    

    public User(Area bornAreaSet){
        this.setName("default");
        this.setBornArea(bornAreaSet);
        this.setCurrentArea(bornAreaSet);
    }
    public void setName(String nameSet){
        this.name = nameSet;
    }
    public void setCurrentArea(Area areaSet){        
        this.currentArea = areaSet;
        currentArea.addPerson(this.getName());
    }
    public void setBornArea(Area bornAreaSet){
        this.bornArea = bornAreaSet;
    }
    public String getName(){
        return name;
    }
    public Area getCurrentArea(){
        return currentArea;
    }
    public Area bornArea(){
        return bornArea;
    }



}
