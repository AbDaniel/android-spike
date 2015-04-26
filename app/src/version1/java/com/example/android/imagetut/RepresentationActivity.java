package com.example.android.imagetut;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class RepresentationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_representation, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Paint transparentPaint;


        public PlaceholderFragment() {
            transparentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            transparentPaint.setColor(Color.TRANSPARENT);
        }

        public Drawable getCoffeeLayer(Integer rectPercentage) {
            Bitmap bmpBase = BitmapFactory.decodeResource(getResources(), R.drawable.rep_fluid_coffee).copy(Bitmap.Config.ARGB_8888, true);
            Integer rectangleHeight = (bmpBase.getHeight() * rectPercentage / 100);
            Canvas canvas = new Canvas(bmpBase);
            canvas.drawRect(0, 0, bmpBase.getWidth(), rectangleHeight, transparentPaint);
            Log.v("@@@", "Coffee Rectangle Size = " + rectPercentage);
            return new BitmapDrawable(getResources(), bmpBase);
        }

        public Drawable getMilkLayer(Integer rectPercentage) {
            Bitmap bmpBase = BitmapFactory.decodeResource(getResources(), R.drawable.rep_fluid_milk).copy(Bitmap.Config.ARGB_8888, true);
            Integer rectangleHeight = (bmpBase.getHeight() * rectPercentage / 100);
            Canvas canvas = new Canvas(bmpBase);
            canvas.drawRect(0, 0, bmpBase.getWidth(), rectangleHeight, transparentPaint);
            Log.v("@@@", "Milk Rectangle Size = " + rectPercentage);
            return new BitmapDrawable(getResources(), bmpBase);
        }

        public Drawable getForthLayer(Integer rectPercentage) {
            Bitmap bmpBase = BitmapFactory.decodeResource(getResources(), R.drawable.rep_fluid_froth).copy(Bitmap.Config.ARGB_8888, true);
            Integer rectangleHeight = (bmpBase.getHeight() * rectPercentage / 100);
            Canvas canvas = new Canvas(bmpBase);
            canvas.drawRect(0, 0, bmpBase.getWidth(), rectangleHeight, transparentPaint);
            Log.v("@@@", "Froth Rectangle Size = " + rectPercentage);
            return new BitmapDrawable(getResources(), bmpBase);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_representation, container, false);
            ImageView view = (ImageView) rootView.findViewById(R.id.image);
            Integer coffeePercentage = getActivity().getIntent().getIntExtra(MainActivity.EXTRA_TEXT_COFFEE_PERCENTAGE, 33);
            Integer milkPercentage = getActivity().getIntent().getIntExtra(MainActivity.EXTRA_TEXT_MILK_PERCENTAGE, 33);
            Integer frothPercentage = getActivity().getIntent().getIntExtra(MainActivity.EXTRA_TEXT_FROTH_PERCENTAGE, 33);
            Resources r = getResources();
            Drawable[] layers = new Drawable[4];
            layers[0] = getForthLayer(100 - coffeePercentage - milkPercentage - frothPercentage);
            layers[1] = getMilkLayer(100 - coffeePercentage - milkPercentage);
            layers[2] = getCoffeeLayer(100 - coffeePercentage);
            layers[3] = r.getDrawable(R.drawable.rep_outline_latte);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            view.setImageDrawable(layerDrawable);
            return rootView;
        }
    }
}
