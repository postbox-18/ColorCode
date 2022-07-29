package com.example.colorcode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton add;
    private TextView show_code;
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        show_code = findViewById(R.id.show_code);
        //on click
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addItemBox.show(getParentFragmentManager(),"AddItemBox");
                alertBox(view);

            }
        });
    }

    private void alertBox(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.add_itembox, null);

        EditText color_code = customLayout.findViewById(R.id.color_code);
        color_code.setText("#");


        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String s_code = color_code.getText().toString();
                if (s_code.isEmpty()) {
                    show_code.setHint("Please enter the code");
                } else if (s_code.length() != 7) {
                    show_code.setHint("Please enter valid code");

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Log.e(TAG, "color>>" + s_code);
                        try {
                            show_code.setBackgroundColor(Color.parseColor(s_code));
                            show_code.setHint(s_code);
                            dialog.dismiss();
                        } catch (NumberFormatException nfe) {
                            Log.e(TAG, "color>>eror>>" + nfe.getMessage());
                            show_code.setHint("Please enter valid code");

                        }

                    }
                }


            }
        });

        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

}