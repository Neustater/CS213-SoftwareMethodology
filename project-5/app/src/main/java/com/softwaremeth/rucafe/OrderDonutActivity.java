package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.Objects;

/**
 The OrderDonutActivity Class handles user ordering
 of donut flavors through GUI and passes it
 to OrderDonutQuantityActivity
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class OrderDonutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public final static int REQ_CODE_DONUT_ORDER = 5;
    private Order currentOrder;
    private StoreOrders storeOrder;
    private String donutType;

    private ListView typesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donut);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedOrderDonut");
        storeOrder = (StoreOrders) intent.getSerializableExtra("serializedStoreOrderDonut");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        typesList = findViewById(R.id.donutsList);
        typesList.setOnItemClickListener(this);

    }

    /**
     *  Finish method returns variables to the super class finishing this class process.
     */
    @Override
    public void finish(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedDonutReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreOrderDonutReturn", (Serializable) storeOrder);
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
     * Method waits for an Item Click to set the type of Donut.
     * Also updates the subtotal of the donut.
     * @param parent checks for the parent that is clicked.
     * @param view checks for the action that is pressed.
     * @param position checks for the position of selections for lists clicked.
     * @param id gets the id of the item clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        donutType = (String) typesList.getItemAtPosition(position);

        Intent orderDonut = new Intent(this, OrderDonutQuantityActivity.class);
        orderDonut.putExtra("serializedOrderDonutQuantity", currentOrder);
        orderDonut.putExtra("serializedStoreOrderDonutQuantity", storeOrder);
        orderDonut.putExtra("serializedDonutTypeDonutQuantity", donutType);

        startActivityForResult(orderDonut, REQ_CODE_DONUT_ORDER);

    }

    /**
     * This method sets the results back to the super class.
     * @param requestCode takes in the request code from super.
     * @param resultCode returns the result code to super.
     * @param data returns the data result.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQ_CODE_DONUT_ORDER): {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order) data.
                            getSerializableExtra("serializedDonutQuantityReturn");
                    storeOrder = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutQuantityReturn");
                    finish();
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    currentOrder = (Order) data.
                            getSerializableExtra("serializedDonutQuantityReturn");
                    storeOrder = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutQuantityReturn");
                }
                break;
            }
        }
    }
}