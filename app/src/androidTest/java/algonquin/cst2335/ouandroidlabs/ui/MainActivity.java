package algonquin.cst2335.ouandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.ouandroidlabs.databinding.ActivityMainBinding;
import algonquin.cst2335.ouandroidlabs.data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        EditText edit = variableBinding.edittext;
        TextView text = variableBinding.textview;
        Button btn = variableBinding.button;


        text.setText("Your edit text has: " + model.editString.getValue());

        btn.setOnClickListener(vw -> {
            String editString = edit.getText().toString();
            text.setText("Your edit text has: " + editString);
            model.editString.postValue(editString);
        });

        variableBinding.button.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.edittext.getText().toString());
            variableBinding.edittext.setText("Your edit text has: " + model.editString);
        });
        model.editString.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                variableBinding.edittext.setText("Your edit text has: " + s);
            }
        });
    }}
