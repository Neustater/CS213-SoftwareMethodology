package com.softwaremeth.rucafe;

import java.text.DecimalFormat;

/**
 The Donut Class allows the user to create a Donut object.
 Also allows access to add flavors to the donut.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public class Donuts extends MenuItem implements Customizable{

    private String type;
    private double price;
    private int quantity;

    private static final double DONUT_PRICE = 1.39;

    private static final int INIT = 0;

    /**
     * Constructor to create a Donut Object
     */
    public Donuts(String type){
        price = INIT;
        quantity = INIT;
        this.type = type;
    }

    /**
     * Method to calculate the item price
     */
    public void itemPrice(){
        price = quantity * DONUT_PRICE;
    }

    /**
     * Method to return a String of the Donut object.
     * @return the Donut Object in a specified string format.
     */
    public String toString(){

        itemPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(price));
        return  quantity + "x " + type + " Donut(s) " + " " + curPriceStr;
    }

    /**
     * Method to set the quantity of the Donut object.
     * @param quantity takes in a int and set the quantity of the Donut Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Method to return the price of the Donut.
     * @return a double called price holding the price of the Donut.
     */
    public double returnPrice(){
        itemPrice();
        return price;
    }

    /**
     * Method to add donut of flavor, required
     * by Customizable
     * Not Required for Project 5
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * Method to add donut of flavor, required
     * by Customizable
     * Not Required for Project 5
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }
}