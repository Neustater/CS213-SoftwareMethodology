package com.softwaremeth.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

/**
 The MenuItem Class holds the Donut and Coffee Object Classes.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public abstract class MenuItem implements Serializable {

    private double price;
    private int quantity;

    /**
     * Method to return the price of the Object.
     * @return a double called price holding the price of the Object.
     */
    public abstract double returnPrice();

    /**
     * Method to return a String of the Object.
     * @return the Object in a specified string format.
     */
    public abstract String toString();

    /**
     * Method to set the quantity of the Object.
     * @param quantity takes in a int and set the quantity of the Object.
     */
    public abstract void setQuantity(int quantity);

    /**
     * Method to update the price of the MenuItems
     */
    abstract void itemPrice();

}
