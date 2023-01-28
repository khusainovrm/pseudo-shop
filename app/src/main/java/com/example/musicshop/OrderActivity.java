package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Order");


        Intent i = getIntent();
        String orderUserName = i.getStringExtra("userName");
        String goodName = i.getStringExtra("name");
        int goodQuantity = i.getIntExtra("quantity", 0);
        double goodPrice = i.getDoubleExtra("price", 0);
        double goodTotal = i.getDoubleExtra("total", 0);


        TextView userNameView = findViewById(R.id.orderUserName);
        userNameView.setText(orderUserName);

        TextView name = findViewById(R.id.goodName);
        name.setText("goodName: " + goodName);

        TextView quantity = findViewById(R.id.goodQuantity);
        quantity.setText("goodQuantity: " + goodQuantity);

        TextView price = findViewById(R.id.goodPrice);
        price.setText("goodPrice: " + goodPrice);

        TextView total = findViewById(R.id.goodTotalPrice);
        total.setText("TOTAL: " + goodTotal);
        text =
                "Hello " + orderUserName + "! You orderd: " + goodName + ". Total price is: " + goodPrice;
    }

    public void submitOrder(View view) {
        composeEmail(new String[]{"khusainovrm@yandex.ru"}, "Hello from music store", text);
        Log.d("order email", text);
    }

    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.d("intent start", "yes");
            startActivity(intent);
        }
    }
}