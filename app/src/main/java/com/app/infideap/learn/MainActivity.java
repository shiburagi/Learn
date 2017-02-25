package com.app.infideap.learn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String USERNAME = "username";
    private DrawerLayout drawer;
    private EditText editText;
    private TextView textView;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        editText = (EditText) findViewById(R.id.editText_text);
        editText2 = (EditText) findViewById(R.id.editText_text2);
        textView = (TextView) findViewById(R.id.textView_text);

        textView.setText(getIntent().getStringExtra(USERNAME));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.length()==0||editText2.length()==0)
                    return;

                double n = Double.parseDouble(editText.getText().toString());
                double m = Double.parseDouble(editText2.getText().toString());
                textView.setText(String.valueOf(m+n));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        editText.addTextChangedListener(watcher);
        editText2.addTextChangedListener(watcher);

        findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okay(v);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }else if (id == R.id.nav_sign_out) {
            FirebaseAuth.getInstance().signOut();
            redirect();
        } else if (id == R.id.nav_send) {
            Toast.makeText(getBaseContext(), "Send click", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_dummy) {
            Snackbar.make(drawer, "Dummy click", Snackbar.LENGTH_LONG)
                    .setAction("okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).setActionTextColor(Color.WHITE).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void redirect() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void okay(View view) {
        Snackbar.make(view, "Okay click : " + editText.getText().toString(), Snackbar.LENGTH_LONG).show();
    }

    public void submit(View view) {
        Snackbar.make(view, "Submit click", Snackbar.LENGTH_LONG).show();
    }
}
