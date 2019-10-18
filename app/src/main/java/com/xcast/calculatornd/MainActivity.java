package com.xcast.calculatornd;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;

    private TextView result,operation;
    private final ArrayList<String> trigonometricOps;
    private static String lastOperation = "";
    private Button inverse;

    public MainActivity()
    {
        this.trigonometricOps = new ArrayList<String>()
        {{
            add("sin");
            add("cos");
            add("tan");
        }};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        result = findViewById(R.id.result);
        operation = findViewById(R.id.operation);

        Button clean = findViewById(R.id.button_clean);
        inverse = findViewById(R.id.buttonInverse);

        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                CharSequence text = operation.getText();
                if(text==null || text.toString().isEmpty()) {return;}
                String oldTxt = text.toString();
                String newTxt = oldTxt.substring(0,oldTxt.length()-1);
                operation.setText(newTxt);
                result.setText(MathEvaluator.evaluate(newTxt));
            }
        });

        clean.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                result.setText("");
                operation.setText("");
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void buttonClick(View view)
    {
        Button source = (Button)view;
        String key = source.getText().toString();
        String oldText = getOperation();
        String newOperation = oldText+key;
        String parenthesis = (trigonometricOps.contains(key))?"(":"";
        operation.setText(newOperation+parenthesis);
        result.setText(MathEvaluator.evaluate(newOperation));
    }

    public void invertNumber(View v)
    {
        String o = getOperation();
        if(o.isEmpty()) {return;}
        String newOp = "1/("+o+")";
        String r = MathEvaluator.evaluate(newOp);
        if(!r.isEmpty())
        {
            result.setText(r);
            operation.setText(newOp);
        }
    }

    private String getOperation()
    {
        CharSequence curOperation = operation.getText();
        return (curOperation!=null)?curOperation.toString():"";
    }
}
