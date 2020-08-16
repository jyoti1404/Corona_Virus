
package com.example.hp.corona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About Us");


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home){
           // Intent intent = new Intent(AboutUs.this,TableFragment.class);
           // startActivity(intent);
            finish();
        }
        return true;


    }
}
