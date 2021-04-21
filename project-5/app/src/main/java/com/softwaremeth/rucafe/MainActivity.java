package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 The MainActivity Class handles the main menu for ordering
 and for checking store and user order using GUI
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class MainActivity extends AppCompatActivity{

    public final static int REQ_CODE_DONUT = 1;
    public final static int REQ_CODE_COFFEE = 2;
    public final static int REQ_CODE_USER_ORDER = 3;
    public final static int REQ_CODE_STORE_ORDER = 4;
    public final static int REQ_CODE_DONUT_ORDER = 5;
    private Order currentOrder;
    private StoreOrders storeOrders;
    private static final int OFF_SET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentOrder = new Order();
        storeOrders = new StoreOrders();
        storeOrders.add(currentOrder);
    }

    /**
     * The onActivityResult method creates the intent of the class.
     * Starting the screen and setting default values.
     * @param requestCode values that determines what function to execute
     *                    based on the request
     * @param resultCode determines the status of the result (Ok or Not OK)
     * @param data takes data being sent from the caller by the intent
     *
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQ_CODE_DONUT) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedDonutReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutReturn");
                }
                break;
            }
            case (REQ_CODE_DONUT_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedOrderDonutQuantity");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutQuantity");
                }
                break;
            }
            case (REQ_CODE_COFFEE) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedCoffeeReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderCoffeeReturn");
                }
                break;
            }
            case (REQ_CODE_USER_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedUserReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedUserStoreReturn");
                }
                break;
            }
            case (REQ_CODE_STORE_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedStoreUserReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreReturn");
                }
                break;
            }
        }
    }

    /**
     * This method sets up the screen to order a Donut.
     * Waits for the donut purchase to be sent back.
     * @param view checks for a selection.
     */
    public void orderDonut(View view) {
        Intent orderDonut = new Intent(this, OrderDonutActivity.class);
        orderDonut.putExtra("serializedOrderDonut", currentOrder);
        orderDonut.putExtra("serializedStoreOrderDonut", storeOrders);

        startActivityForResult(orderDonut, REQ_CODE_DONUT);
    }

    /**
     * This method sets up the screen to order Coffee.
     * Waits for the coffee purchase to be sent back.
     * @param view checks for a selection.
     */
    public void orderCoffee(View view) {
        Intent orderCoffee = new Intent(this, OrderCoffeeActivity.class);
        orderCoffee.putExtra("serializedOrderCoffee", currentOrder);
        orderCoffee.putExtra("serializedStoreOrderCoffee", storeOrders);

        startActivityForResult(orderCoffee, REQ_CODE_COFFEE);
    }

    /**
     * This method sets up the screen to look at the current Order of the User.
     * Can send the order to the store orders page.
     * @param view checks for a selection.
     */
    public void checkUserOrders(View view) {
        Intent userOrder = new Intent(this, OrderDetailsActivity.class);

        userOrder.putExtra("serializedUserOrder", currentOrder);
        userOrder.putExtra("serializedUserStoreOrder", storeOrders);

        int numStoreOrders = storeOrders.returnOrder().size()-OFF_SET;
        currentOrder = storeOrders.returnOrder().get(numStoreOrders);
        startActivityForResult(userOrder, REQ_CODE_USER_ORDER);
    }
    /**
     * This method sets up the check all of the store orders.
     * Can view or remove orders.
     * @param view checks for a selection.
     */
    public void checkStoreOrders(View view) {
        Intent storeOrder = new Intent(this, StoreOrdersActivity.class);
        storeOrder.putExtra("serializedStoreUserOrder", currentOrder);
        storeOrder.putExtra("serializedStoreOrder", storeOrders);
        startActivityForResult(storeOrder, REQ_CODE_STORE_ORDER);
    }

}