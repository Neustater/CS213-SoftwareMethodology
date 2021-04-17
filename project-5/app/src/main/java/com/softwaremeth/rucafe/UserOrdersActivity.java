package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.MenuItem;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UserOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);

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

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedUserReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedUserStoreReturn", (Serializable) storeOrder);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        currentOrderList.remove(position);
        list.remove(currentOrderListStrings.get(position));
        updateStringsList();
        list.notifyDataSetChanged();
    }

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

    public void placeOrder(View view) {
        storeOrder.add(currentOrder);
        currentOrder = new Order();

        finish();
    }

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