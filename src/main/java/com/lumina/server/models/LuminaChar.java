package com.lumina.server.models;


public class LuminaChar {

    /* Basic info */
    private String name;
    private String gender;
    private String classe;

    private Area currentArea;
    private Area bornArea;

    /* Status */
    private float hp;
    private float defense;
    private float elementalDefense;
    private float atack;
    private float elementalAtack;

    /* Core attributes */
    private float strenghtAttribute;
    private float agilityAttribute;
    private float knowlodgeAttribute;
    private float spiritualityAttribute;

    /* ---------- Default Constructor ---------- */
    public LuminaChar(
        
        String name,
        String gender,
        String classe,
        
        
        Area bornAreaSet,

        Float hp,
        Float defense,
        Float elementalDefense,
        Float atack,
        Float elementalAtack,
        Float strenghtAttribute,
        Float agilityAttribute,
        Float knowlodgeAttribute,
        Float spiritualityAttribute
    ) {
        this.name = (name != null) ? name : "default";
        this.gender = (gender != null) ? gender : "default";
        this.classe = (classe != null) ? classe : "default";


        this.bornArea = (bornArea != null) ? bornAreaSet : LuminaMap.getInstance().getArea(5, 5);



        this.hp = (hp != null) ? hp : 10.0f;
        this.defense = (defense != null) ? defense : 10.0f;
        this.elementalDefense = (elementalDefense != null) ? elementalDefense : 10.0f;
        this.atack = (atack != null) ? atack : 10.0f;
        this.elementalAtack = (elementalAtack != null) ? elementalAtack : 10.0f;
        this.strenghtAttribute = (strenghtAttribute != null) ? strenghtAttribute : 10.0f;
        this.agilityAttribute = (agilityAttribute != null) ? agilityAttribute : 10.0f;
        this.knowlodgeAttribute = (knowlodgeAttribute != null) ? knowlodgeAttribute : 10.0f;
        this.spiritualityAttribute = (spiritualityAttribute != null) ? spiritualityAttribute : 10.0f;
        
        //Char born!
        this.currentArea = bornArea;
        this.currentArea.addPerson(this.getName());

    }

    /* ---------- Getters & Setters ---------- */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    public Area getCurrentArea(){
        return currentArea;
    }    
    public void setCurrentArea(Area areaSet){        
        this.currentArea = areaSet;

    }
    public Area bornArea(){
        return bornArea;
    }
    public void setBornArea(Area bornAreaSet){
        this.bornArea = bornAreaSet;
    }    
    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getElementalDefense() {
        return elementalDefense;
    }

    public void setElementalDefense(float elementalDefense) {
        this.elementalDefense = elementalDefense;
    }

    public float getAtack() {
        return atack;
    }

    public void setAtack(float atack) {
        this.atack = atack;
    }

    public float getElementalAtack() {
        return elementalAtack;
    }

    public void setElementalAtack(float elementalAtack) {
        this.elementalAtack = elementalAtack;
    }

    public float getStrenghtAttribute() {
        return strenghtAttribute;
    }

    public void setStrenghtAttribute(float strenghtAttribute) {
        this.strenghtAttribute = strenghtAttribute;
    }

    public float getAgilityAttribute() {
        return agilityAttribute;
    }

    public void setAgilityAttribute(float agilityAttribute) {
        this.agilityAttribute = agilityAttribute;
    }

    public float getKnowlodgeAttribute() {
        return knowlodgeAttribute;
    }

    public void setKnowlodgeAttribute(float knowlodgeAttribute) {
        this.knowlodgeAttribute = knowlodgeAttribute;
    }

    public float getSpiritualityAttribute() {
        return spiritualityAttribute;
    }

    public void setSpiritualityAttribute(float spiritualityAttribute) {
        this.spiritualityAttribute = spiritualityAttribute;
    }
}


