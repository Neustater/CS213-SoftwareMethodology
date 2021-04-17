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

    @Override
    public void finish(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedDonutReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreOrderDonutReturn", (Serializable) storeOrder);
        setResult(Activity.RESULT_OK, resultIntent);
        super.finish();
    }

    @Override
    public void onBackPressed() {
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        donutType = (String) typesList.getItemAtPosition(position);

        Intent orderDonut = new Intent(this, OrderDonutQuantityActivity.class);
        orderDonut.putExtra("serializedOrderDonutQuantity", currentOrder);
        orderDonut.putExtra("serializedStoreOrderDonutQuantity", storeOrder);
        orderDonut.putExtra("serializedDonutTypeDonutQuantity", donutType);

        startActivityForResult(orderDonut, REQ_CODE_DONUT_ORDER);

    }

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