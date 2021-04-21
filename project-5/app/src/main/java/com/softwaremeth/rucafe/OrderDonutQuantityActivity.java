package com.softwaremeth.rucafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 The OrderCoffeeActivity Class handles user ordering a quantity
 of the selected donut flavor through GUI and adds them to the
 current order
 @author Muhammad Faizan Saiyed, Michael Neustater
 */


public class OrderDonutQuantityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner quantitySpinner;
    private Order currentOrder;
    private StoreOrders storeOrder;
    private String donutType;
    private int quantity;
    private TextView donutTypeText;
    private Donuts currentDonut;
    private double curPrice;
    private TextView subtotalBox;
    private boolean placeOrder = false;

    private boolean backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donut_quantity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedOrderDonutQuantity");
        storeOrder = (StoreOrders) intent.getSerializableExtra("serializedStoreOrderDonutQuantity");
        donutType = (String) intent.getSerializableExtra("serializedDonutTypeDonutQuantity");
        subtotalBox = (TextView) findViewById(R.id.subtotalBoxDonut);

        quantitySpinner = findViewById(R.id.quantitySpinnerDonut);
        quantitySpinner.setOnItemSelectedListener(this);

        currentDonut = new Donuts(donutType);
        currentDonut.setQuantity(Integer.parseInt((String) quantitySpinner.getSelectedItem()));
        updateSubtotal();

        donutTypeText = findViewById(R.id.donutTypeText);
        donutTypeText.setText(donutType);


    }

    /**
     * This method adds the Donut order to the current order of the User.
     * @param view checks for a selection.
     */
    public void addToOrder(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.donut_order_message));
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                placeOrder = true;
                placedOrder();
                backPressed = false;
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
        if(placeOrder){
            Toast.makeText(this, getResources().getString(R.string.added_to_order_donut),
                    Toast.LENGTH_SHORT);
            currentOrder.add(currentDonut);
            finish();
        }
    }

    /**
     * This method updates the sub total of the donut.
     */
    private void updateSubtotal(){
        curPrice = currentDonut.returnPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subtotalBox.setText("$" + curPriceStr);
    }

    /**
     * Method waits for an Item Click to set the quantity of the donut.
     * Also updates the subtotal of the donut.
     * @param parent checks for the parent that is clicked.
     * @param view checks for the action that is pressed.
     * @param position checks for the position of selections for lists clicked.
     * @param id gets the id of the item clicked.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
        currentDonut.setQuantity(quantity);

        updateSubtotal();
    }

    /**
     * When nothing is selected, it does nothing.
     * @param parent checks for a selection.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Finish method returns variables to the super class finishing this class process.
     */
    @Override
    public void finish(){
        Intent resultIntent = new Intent();
        resultIntent.
                putExtra("serializedDonutQuantityReturn", (Serializable) currentOrder);
        resultIntent.
                putExtra("serializedStoreOrderDonutQuantityReturn", (Serializable) storeOrder);
        if(backPressed){
            setResult(Activity.RESULT_CANCELED, resultIntent);
        }
        else{
            setResult(Activity.RESULT_OK, resultIntent);
        }
        super.finish();
    }

    /**
     * Method calls the finish method when the back button is pressed on the android.
     */
    @Override
    public void onBackPressed() {
        backPressed = true;
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
                backPressed = true;
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}