package com.softwaremeth.rucafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 The OrderCoffeeActivity Class handles user ordering
 of coffee objects through GUI and adds them to the
 current order
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class OrderCoffeeActivity extends AppCompatActivity{

    private Order currentOrder;
    private StoreOrders storeOrder;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;

    private static final String[] UNIVERSAL_SIZES = {"Short","Tall","Grande","Venti"};

    private CheckBox caramelBox;
    private CheckBox whippedBox;
    private CheckBox syrupBox;
    private CheckBox milkBox;
    private CheckBox sugarBox;
    private CheckBox creamBox;

    private TextView subtotalBox;

    private String size;
    private int quantity;
    private Coffee currentCoffee;
    private double curPrice;
    private boolean placeOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedOrderCoffee");
        storeOrder = (StoreOrders) intent.getSerializableExtra("serializedStoreOrderCoffee");

        sizeSpinner = findViewById(R.id.sizeSpinner);
        sizeSpinner.setOnItemSelectedListener(new SizeSpinnerClass());

        quantitySpinner = findViewById(R.id.quantitySpinnerCoffee);
        quantitySpinner.setOnItemSelectedListener(new QuantitySpinnerClass());

        currentCoffee = new Coffee();
        subtotalBox = (TextView) findViewById(R.id.subtotalBoxCoffee);

        quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
        size = (String) sizeSpinner.getSelectedItem();
        currentCoffee.setQuantity(quantity);
        sizeConversionSetter();

        updateSubtotal();

    }

    /**
     *  finish method returns variables to the super class finishing this class process.
     */
    @Override
    public void finish(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedCoffeeReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreOrderCoffeeReturn", (Serializable) storeOrder);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    /**
     * Method calls the finish method when the back button is pressed on the android.
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * Method checks if the MenuItem selected to go back to the home page.
     * @param item checks for the item that is selected in the Menu.
     * @return boolean true if pressed home, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When caramel is checked on screen, it adds Caramel to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkCaramel(View view) {
        caramelBox = findViewById(R.id.caramelBox);

        if(caramelBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.caramel));
        }
        if(!caramelBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.caramel));
        }
        updateSubtotal();
    }

    /**
     * When cream is checked on screen, it adds Cream to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkCream(View view) {
        creamBox = findViewById(R.id.creamBox);

        if(creamBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.cream));
        }
        if(!creamBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.cream));
        }
        updateSubtotal();
    }

    /**
     * When sugar is checked on screen, it adds Sugar to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkSugar(View view) {
        sugarBox = findViewById(R.id.sugarBox);

        if(sugarBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.sugar));
        }
        if(!sugarBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.sugar));
        }
        updateSubtotal();
    }

    /**
     * When milk is checked on screen, it adds Milk to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkMilk(View view) {
        milkBox = findViewById(R.id.milkBox);

        if(milkBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.milk));
        }
        if(!milkBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.milk));
        }
        updateSubtotal();
    }

    /**
     * When whipped is checked on screen, it adds Whipped to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkWhipped(View view) {
        whippedBox = findViewById(R.id.whippedBox);

        if(whippedBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.whipped));
        }
        if(!whippedBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.whipped));
        }
        updateSubtotal();
    }

    /**
     * When syrup is checked on screen, it adds Syrup to the current Coffee.
     * Also updates the sub total.
     * @param view checks for a selection.
     */
    public void checkSyrup(View view) {
        syrupBox = findViewById(R.id.syrupBox);

        if(syrupBox.isChecked()){
            currentCoffee.add(getResources().getString(R.string.syrup));
        }
        if(!syrupBox.isChecked()){
            currentCoffee.remove(getResources().getString(R.string.syrup));
        }
        updateSubtotal();
    }

    /**
     * This method adds the Coffee order to the current order of the User.
     * @param view checks for a selection.
     */
    public void addToOrder(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.coffee_order_message));
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                placeOrder = true;
                placedOrder();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                placeOrder = false;
                placedOrder();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();


    }


    /**
     * This method places the order, adding it to the current order of the User.
     */
    public void placedOrder(){
        if(placeOrder) {
            currentOrder.add(currentCoffee);

            Toast.makeText(this, getResources().getString(R.string.added_to_order_coffee),
                    Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * This method updates the sub total of the coffee.
     */
    private void updateSubtotal(){
        curPrice = currentCoffee.returnPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subtotalBox.setText("$" + curPriceStr);
    }

    /**
     * This method sets the size of the coffee.
     */
    private void sizeConversionSetter(){
        String convertedSize;
        if(size.equals(getResources().getString(R.string.shorti))){
            convertedSize = UNIVERSAL_SIZES[0];
        }else if(size.equals(getResources().getString(R.string.tall))){
            convertedSize = UNIVERSAL_SIZES[1];
        }else if(size.equals(getResources().getString(R.string.grande))){
            convertedSize = UNIVERSAL_SIZES[2];
        }else if(size.equals(getResources().getString(R.string.venti))){
            convertedSize = UNIVERSAL_SIZES[3];
        }else{
            convertedSize = null;
        }
        currentCoffee.setSize(convertedSize);
    }

    /**
     The SizeSpinnerClass is an internal class that handles the
     size spinner inputs
     @author Muhammad Faizan Saiyed, Michael Neustater
     */
    class SizeSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        /**
         * Method waits for an Item Click to set the quantity of the coffee and the size of the coffee.
         * Also updates the subtotal of the coffee.
         * @param parent checks for the parent that is clicked.
         * @param view checks for the action that is pressed.
         * @param position checks for the position of selections for lists clicked.
         * @param id gets the id of the item clicked.
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            size = (String) sizeSpinner.getSelectedItem();
            sizeConversionSetter();
            updateSubtotal();
        }

        /**
         * When nothing is selected, it does nothing.
         * @param parent checks for a selection.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     The QuantitySpinnerClass is an internal class that handles the
     quantity spinner inputs
     @author Muhammad Faizan Saiyed, Michael Neustater
     */
    class QuantitySpinnerClass implements AdapterView.OnItemSelectedListener
    {
        /**
         * Method waits for an Item Click to set the quantity of the coffee and the size of the coffee.
         * Also updates the subtotal of the coffee.
         * @param parent checks for the parent that is clicked.
         * @param view checks for the action that is pressed.
         * @param position checks for the position of selections for lists clicked.
         * @param id gets the id of the item clicked.
         */
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
        {
            quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
            currentCoffee.setQuantity(quantity);
            updateSubtotal();
        }

        /**
         * When nothing is selected, it does nothing.
         * @param parent checks for a selection.
         */
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}


