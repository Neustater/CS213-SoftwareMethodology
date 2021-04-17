package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;


public class OrderDonutQuantityActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner quantitySpinner;

    private Order currentOrder;
    private StoreOrders storeOrder;
    private String donutType;
    private int quantity;
    private TextView donutTypeText;
    private Donuts currentDonut;
    private double curPrice;
    TextView subtotalBox;

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

    public void addToOrder(View view) {
        currentOrder.add(currentDonut);
        backPressed = false;
        finish();
    }

    private void updateSubtotal(){
        curPrice = currentDonut.returnPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subtotalBox.setText("$" + curPriceStr);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
        currentDonut.setQuantity(quantity);

        updateSubtotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

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

    @Override
    public void onBackPressed() {
        backPressed = true;
        finish();
    }

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