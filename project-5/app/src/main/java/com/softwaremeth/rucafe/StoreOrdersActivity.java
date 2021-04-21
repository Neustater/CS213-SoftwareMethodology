package com.softwaremeth.rucafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 The StoreOrdersActivity Class handles viewing of
 all the placed user orders and their prices
 through GUI and allows for placed orders to be cancelled
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Order currentOrder;
    private StoreOrders storeOrders;
    private ArrayList<Order> storeOrdersList;
    private ArrayList<String> storeOrdersListStrings;

    private static final int OFF_SET = 1;

    private ListView listview;
    private ArrayAdapter<String> list;
    private Boolean removeOrder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedStoreUserOrder");
        storeOrders = (StoreOrders) intent.getSerializableExtra("serializedStoreOrder");

        listview = findViewById(R.id.ordersList);
        listview.setOnItemClickListener(this);

        storeOrdersListStrings = new ArrayList<String>();
        storeOrdersList = new ArrayList<Order>();

        updateStringsList();

        list = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, storeOrdersListStrings);
        listview.setAdapter(list);

    }

    /**
     * The Method waits for an Item Click to remove items from the store order.
     * @param parent checks for the parent that is clicked.
     * @param view checks for the action that is pressed.
     * @param position checks for the position of selections for lists clicked.
     * @param id gets the id of the item clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final int removedPos = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.order_remove_message));
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removeOrder = true;
                removedOrder(removedPos);

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removeOrder = false;
                removedOrder(removedPos);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Method removes items from the current store order list.
     * @param position checks for the position of the Strings list that needs to be removed.
     */
    public void removedOrder(int position){
        if(removeOrder) {
            storeOrdersList.remove(position + OFF_SET);
            list.remove(storeOrdersListStrings.get(position));
            updateStringsList();
            list.notifyDataSetChanged();
            Toast.makeText(this, getResources().getString(R.string.order_removed),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method calls the finish method when the back button is pressed on the android.
     */
    @Override
    public void onBackPressed(){
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
     * Finish method returns variables to the super class finishing this class process.
     */
    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedStoreUserReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreReturn", (Serializable) storeOrders);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    /**
     * Method updates the strings list of the store orders.
     */
    private void updateStringsList(){
        storeOrdersList = storeOrders.returnOrder();

        storeOrdersListStrings.clear();
        if((storeOrdersList.size() <= OFF_SET)){
            storeOrdersListStrings.add(getResources().getString(R.string.no_orders));
            listview.setEnabled(false);
        }
        for(int i = 1; i < storeOrdersList.size(); i++) {

            storeOrdersList.get(i).updateTotals();
            double subTotal = storeOrdersList.get(i).getSubTotal();
            double salesTax = storeOrdersList.get(i).getTax();
            double total = storeOrdersList.get(i).getTotal();

            DecimalFormat df = new DecimalFormat("0.00");
            String curSubtotalStr = " $ " + df.format(subTotal);
            String curSalesTaxStr = " $ " + df.format(salesTax);
            String curTotalStr = " $ " + df.format(total);

            storeOrdersListStrings.
                    add(getResources().getString(R.string.order_no) + " "
                            + storeOrdersList.get(i).toString() + "\n"
                            + getResources().getString(R.string.subtotal)
                            + curSubtotalStr + " | "
                            + getResources().getString(R.string.salestax)
                            + curSalesTaxStr + " | "
                            + getResources().getString(R.string.total)
                            + curTotalStr);
        }
    }
}