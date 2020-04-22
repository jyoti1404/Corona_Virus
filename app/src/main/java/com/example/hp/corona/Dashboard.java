package com.example.hp.corona;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
       // openFragment(TableFragment.newInstance("", ""));

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TableFragment()).commit();

    }
    /*public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment= null;
                    //Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                    switch (item.getItemId()) {
                        case R.id.table:
                               fragment = new TableFragment();
                               break;
                        case R.id.symptoms:
                            fragment = new SymptomsFragment();
                            break;
                        case R.id.prevention:
                            // Switch to page three
                            fragment = new PreventionFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    return true;
                }

               // FragmentManager manager = getSupportFragmentManager();
               // FragmentTransaction fragmentTransaction = manager.beginTransaction();
                 //       fragmentTransaction.replace(R.id.container, fragment);
                   //     fragmentTransaction.commit();
                     //   fragmentTransaction.addToBackStack(null);

            };

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Corona:
                Toast.makeText(Dashboard.this,"Corona Virus",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this,Corona.class);
                startActivity(intent);
                finish();
                break;
            case R.id.aboutus:
                Toast.makeText(Dashboard.this,"About Us",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Dashboard.this,AboutUs.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.feedback:
                Toast.makeText(Dashboard.this,"Feedback",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Dashboard.this,Feedback.class);
                startActivity(intent2);
                finish();
                break;
        }
        return true;
    }

    //public void callFragment(View v) {
      //  Fragment fragment = null;
        //if (v == bottomNavigationView) {
          //  fragment = new TableFragment();
        //} else {
            //fragment = new SymptomsFragment();
        //}
          // }
}
