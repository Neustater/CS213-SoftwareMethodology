package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.text.DecimalFormat;

public class OrderCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Order currentOrder;
    private StoreOrders storeOrder;
    private Spinner sizeSpinner;
    private Spinner quantitySpinner;

    private static final String[] UNIVERSAL_SIZES = {"Short","Tall","Grande","Venti"};

    CheckBox caramelBox;
    CheckBox whippedBox;
    CheckBox syrupBox;
    CheckBox milkBox;
    CheckBox sugarBox;
    CheckBox creamBox;

    TextView subtotalBox;

    private String size;
    private int quantity;
    private Coffee currentCoffee;
    private double curPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("serializedOrderCoffee");
        storeOrder = (StoreOrders) intent.getSerializableExtra("serializedStoreOrderCoffee");

        sizeSpinner = findViewById(R.id.sizeSpinner);
        sizeSpinner.setOnItemSelectedListener(this);

        quantitySpinner = findViewById(R.id.quantitySpinnerCoffee);
        quantitySpinner.setOnItemSelectedListener(this);

        currentCoffee = new Coffee();
        subtotalBox = (TextView) findViewById(R.id.subtotalBoxCoffee);

        quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
        size = (String) sizeSpinner.getSelectedItem();
        currentCoffee.setQuantity(quantity);
        sizeConversionSetter();

        updateSubtotal();
    }

    @Override
    public void finish(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("serializedCoffeeReturn", (Serializable) currentOrder);
        resultIntent.putExtra("serializedStoreOrderCoffeeReturn", (Serializable) storeOrder);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
        size = (String) sizeSpinner.getSelectedItem();

        currentCoffee.setQuantity(quantity);
        sizeConversionSetter();
        updateSubtotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

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

    public void addToOrder(View view) {
        currentOrder.add(currentCoffee);

        finish();
    }

    private void updateSubtotal(){
        curPrice = currentCoffee.returnPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(curPrice);
        subtotalBox.setText("$" + curPriceStr);
    }

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

}