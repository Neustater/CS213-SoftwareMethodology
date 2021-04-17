package com.softwaremeth.rucafe;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    public final static int REQ_CODE_DONUT = 1;
    public final static int REQ_CODE_COFFEE = 2;
    public final static int REQ_CODE_USER_ORDER = 3;
    public final static int REQ_CODE_STORE_ORDER = 4;
    public final static int REQ_CODE_DONUT_ORDER = 5;
    private Order currentOrder;
    private StoreOrders storeOrders;
    private static final int OFF_SET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentOrder = new Order();
        storeOrders = new StoreOrders();
        storeOrders.add(currentOrder);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (REQ_CODE_DONUT) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedDonutReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutReturn");
                }
                break;
            }
            case (REQ_CODE_DONUT_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedOrderDonutQuantity");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderDonutQuantity");
                }
                break;
            }
            case (REQ_CODE_COFFEE) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedCoffeeReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreOrderCoffeeReturn");
                }
                break;
            }
            case (REQ_CODE_USER_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedUserReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedUserStoreReturn");
                }
                break;
            }
            case (REQ_CODE_STORE_ORDER) : {
                if (resultCode == Activity.RESULT_OK) {
                    currentOrder = (Order)data.
                            getSerializableExtra("serializedStoreUserReturn");
                    storeOrders = (StoreOrders) data.
                            getSerializableExtra("serializedStoreReturn");
                }
                break;
            }
        }
    }

    public void orderDonut(View view) {
        Intent orderDonut = new Intent(this, OrderDonutActivity.class);
        orderDonut.putExtra("serializedOrderDonut", currentOrder);
        orderDonut.putExtra("serializedStoreOrderDonut", storeOrders);

        startActivityForResult(orderDonut, REQ_CODE_DONUT);
    }

    public void orderCoffee(View view) {
        Intent orderCoffee = new Intent(this, OrderCoffeeActivity.class);
        orderCoffee.putExtra("serializedOrderCoffee", currentOrder);
        orderCoffee.putExtra("serializedStoreOrderCoffee", storeOrders);

        startActivityForResult(orderCoffee, REQ_CODE_COFFEE);
    }

    public void checkUserOrders(View view) {
        Intent userOrder = new Intent(this, UserOrdersActivity.class);

        userOrder.putExtra("serializedUserOrder", currentOrder);
        userOrder.putExtra("serializedUserStoreOrder", storeOrders);

        int numStoreOrders = storeOrders.returnOrder().size()-OFF_SET;
        currentOrder = storeOrders.returnOrder().get(numStoreOrders);
        startActivityForResult(userOrder, REQ_CODE_USER_ORDER);
    }

    public void checkStoreOrders(View view) {
        Intent storeOrder = new Intent(this, StoreOrdersActivity.class);
        storeOrder.putExtra("serializedStoreUserOrder", currentOrder);
        storeOrder.putExtra("serializedStoreOrder", storeOrders);
        startActivityForResult(storeOrder, REQ_CODE_STORE_ORDER);
    }

}