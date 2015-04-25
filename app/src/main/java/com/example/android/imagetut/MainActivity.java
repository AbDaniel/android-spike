package com.example.android.imagetut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, RepresentationActivity.class);
        EditText editTextCoffee = (EditText) findViewById(R.id.edit_text_coffee);
        String coffeePercentage = editTextCoffee.getText().toString();


        EditText editTextMilk = (EditText) findViewById(R.id.edit_text_milk);
        String milkPercentage = editTextMilk.getText().toString();

        EditText editTextFroth = (EditText) findViewById(R.id.edit_text_froth);
        String frothPercentage = editTextFroth.getText().toString();

        intent.putExtra(EXTRA_TEXT_COFFEE_PERCENTAGE, Integer.parseInt(coffeePercentage));
        intent.putExtra(EXTRA_TEXT_MILK_PERCENTAGE, Integer.parseInt(milkPercentage));
        intent.putExtra(EXTRA_TEXT_FROTH_PERCENTAGE, Integer.parseInt(frothPercentage));
        startActivity(intent);

    }

    public static final String EXTRA_TEXT_COFFEE_PERCENTAGE = "CoffePercentage";
    public static final String EXTRA_TEXT_MILK_PERCENTAGE = "MilkPercentage";
    public static final String EXTRA_TEXT_FROTH_PERCENTAGE = "FrothPercentage";
}
