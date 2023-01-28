package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText userNameView;
    int quantity = 0;
    ArrayList spinnerArrayList;
    Spinner spinner;
    ArrayAdapter spinnerAdapter;
    HashMap<String, Integer> goodsMap;
    String selectedGood;
    double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();

        createMap();

        userNameView = findViewById(R.id.userName);
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500);
        goodsMap.put("drums", 400);
        goodsMap.put("keyboard", 440);
    }

    public void handleCart(View view) {
        Order order = new Order();
        order.userName = userNameView.getText().toString();
        Log.d("order", "order.userName: " + order.userName);

        order.name = selectedGood;
        Log.d("order", "order.name: " + order.name);

        order.quantity = quantity;
        Log.d("order", "order.quantity: " + order.quantity);

        order.price = price;
        Log.d("order", "order.price: " + order.price);

        order.total = quantity * price;
        Log.d("order", "order.total: " + order.total);


        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("userName", order.userName);
        intent.putExtra("name", order.name);
        intent.putExtra("quantity", order.quantity);
        intent.putExtra("price",  order.price);
        intent.putExtra("total", order.total);
        startActivity(intent);
    }

    public void increaseQuantity(View view) {
        TextView textView = findViewById(R.id.quantity);
        quantity = quantity + 1;
        textView.setText(String.valueOf(quantity));
        TextView priceView = findViewById(R.id.price);
        priceView.setText(price * quantity + " $");
    }

    public void decreaseQuantity(View view) {
        TextView textView = findViewById(R.id.quantity);
        if (quantity > 0) {
            quantity = quantity - 1;
        }
        TextView priceView = findViewById(R.id.price);
        priceView.setText(price * quantity + " $");
        textView.setText(String.valueOf(quantity));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedGood = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(selectedGood);
        TextView priceView = findViewById(R.id.price);
        priceView.setText(price * quantity + " $");

        ImageView goodImage = findViewById(R.id.goodImage);
        switch (selectedGood) {
            case "guitar":
                goodImage.setImageResource(R.drawable.guitar_2);
                break;
            case "drums":
                goodImage.setImageResource(R.drawable.drums_good);
                break;
            case "keyboard":
                goodImage.setImageResource(R.drawable.keyboard_good);
                break;
            default:
                goodImage.setImageResource(R.drawable.guitar_2);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}