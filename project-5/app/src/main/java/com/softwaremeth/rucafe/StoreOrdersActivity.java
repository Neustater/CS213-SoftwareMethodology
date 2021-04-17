package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;


public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Order currentOrder;
    private StoreOrders storeOrders;
    private ArrayList<Order> storeOrdersList;
    private ArrayList<String> storeOrdersListStrings;

    private static final int OFF_SET = 1;

    private ListView listview;
    private ArrayAdapter<String> list;

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        storeOrdersList.remove(position+OFF_SET);
        list.remove(storeOrdersListStrings.get(position));
        updateStringsList();
        list.notifyDataSetChanged();
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
        resultIntent.putExtra("serializedStoreUserReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreReturn", (Serializable) storeOrders);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    private void updateStringsList(){
        storeOrdersList = storeOrders.returnOrder();

        storeOrdersListStrings.clear();
        if((storeOrdersList.size() <= OFF_SET)){
            storeOrdersListStrings.add(getResources().getString(R.string.no_orders));
            listview.setEnabled(false);
        }
        for(int i = 1; i < storeOrdersList.size(); i++) {
            storeOrdersListStrings.
                    add(getResources().getString(R.string.order_no) + " "
                            + storeOrdersList.get(i).toString());
        }
    }
}