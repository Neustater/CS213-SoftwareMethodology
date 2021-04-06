package cafeGUI;

/**
 This Interface is used for the Donuts and Coffee class.
 @author Muhammad Faizan Saiyed, Michael Neustater
 */
public interface Customizable {
    /**
     * Method to add an Object to the Class.
     * @param obj takes in to check if it is a valid Object, and adds it.
     * @return a boolean being true if the object successfully been added, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Method to remove an Object to the Class.
     * @param obj takes in to check if it is a valid Object, and removes it.
     * @return a boolean being true if the object successfully been removed, false otherwise.
     */
    boolean remove(Object obj);
}