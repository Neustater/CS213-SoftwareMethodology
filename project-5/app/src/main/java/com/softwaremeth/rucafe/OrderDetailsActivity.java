package com.softwaremeth.rucafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.MenuItem;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 The UserOrdersActivity Class handles the viewing
 of current MenuItems ordered and their prices
 through GUI and allows the user to cancel an item
 @author Muhammad Faizan Saiyed, Michael Neustater
 */

public class OrderDetailsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Order currentOrder;
    private ArrayList<com.softwaremeth.rucafe.MenuItem> currentOrderList;
    private ArrayList<String> currentOrderListStrings;
    private StoreOrders storeOrder;

    private TextView subtotalBox;
    private TextView salesTaxBox;
    private TextView totalBox;
    private static final int EMPTY = 0;

    private ListView listview;
    private ArrayAdapter<String> list;

    private boolean placeOrder = false;;
    private boolean removeItem = false;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedUserOrder");
        storeOrder = (StoreOrders) intent.getSerializableExtra("serializedUserStoreOrder");

        listview = findViewById(R.id.itemsList);
        listview.setOnItemClickListener(this);

        currentOrderListStrings = new ArrayList<String>();
        currentOrderList = currentOrder.getCurrentOrder();

       updateStringsList();

        list = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, currentOrderListStrings);
        listview.setAdapter(list);

        subtotalBox = (TextView) findViewById(R.id.subtotalBoxUser);
        salesTaxBox = (TextView) findViewById(R.id.salesTaxBoxUser);
        totalBox = (TextView) findViewById(R.id.totalBoxUser);

        updateTotals();

    }

    /**
     * This method calls the finish method when the back button is pressed on the android.
     */
    @Override
    public void onBackPressed(){
        finish();
    }

    /**
     * This method checks if the MenuItem selected to go back to the home page.
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
        resultIntent.putExtra("serializedUserReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedUserStoreReturn", (Serializable) storeOrder);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    /**
     * The Method waits for an Item Click to remove items from the order.
     * @param parent checks for the parent that is clicked.
     * @param view checks for the action that is pressed.
     * @param position checks for the position of selections for lists clicked.
     * @param id gets the id of the item clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final int removedPos = position;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.item_remove_message));
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removeItem = true;
                removedItem(removedPos);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                removeItem = false;
                removedItem(removedPos);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Method removes items from the current order list.
     * @param position checks for the position of the Strings list that needs to be removed.
     */
    public void removedItem(int position){
        if(removeItem) {
            currentOrderList.remove(position);
            list.remove(currentOrderListStrings.get(position));
            updateStringsList();
            updateTotals();
            Toast.makeText(this, getResources().getString(R.string.item_removed),
                    Toast.LENGTH_SHORT).show();
            list.notifyDataSetChanged();
        }
    }

    /**
     * Method updates the strings list of the current orders.
     */
    private void updateStringsList(){
        currentOrderList = currentOrder.getCurrentOrder();
        currentOrderListStrings.clear();
        if((currentOrderList.size() == EMPTY)){
            currentOrderListStrings.add(getResources().getString(R.string.no_items));
            listview.setEnabled(false);
        }
        for(int i = 0; i < currentOrderList.size(); i++) {
            currentOrderListStrings.add(i, currentOrderList.get(i).toString());
        }
    }

    /**
     * Method waits for button click, handles place order button in GUI
     * @param view waits for the order button to be pressed.
     */
    public void placeOrder(View view) {
        if(currentOrderList.size() != EMPTY){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.user_order_message));
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
        dialog.show();}else{
            Toast.makeText(this, getResources().getString(R.string.order_empty),
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Method places the order, and adds it to the store orders.
     */
    public void placedOrder(){
        if(placeOrder){
                storeOrder.add(currentOrder);
                currentOrder = new Order();
                Toast.makeText(this, getResources().getString(R.string.placed_order),
                        Toast.LENGTH_SHORT).show();

                finish();
        }
    }

    /**
     * Method updates the subtotal, sales tax, and total price of the order.
     */
    private void updateTotals(){
        currentOrder.updateTotals();
        double subTotal = currentOrder.getSubTotal();
        double salesTax = currentOrder.getTax();
        double total = currentOrder.getTotal();

        DecimalFormat df = new DecimalFormat("0.00");
        String curSubtotalStr = df.format(subTotal);
        subtotalBox.setText("$ " + curSubtotalStr);
        String curSalesTaxStr = df.format(salesTax);
        salesTaxBox.setText("$ " + curSalesTaxStr);
        String curTotalStr = df.format(total);
        totalBox.setText("$ " + curTotalStr);
    }

}