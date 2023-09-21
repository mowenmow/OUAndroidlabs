package algonquin.cst2335.ouandroidlabs.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.widget.Toast;

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

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
//            variableBinding.myedittext.setText("Your edit text has:" + model.editString);
        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has:" + s);
        });

        model.isSelected.observe(this, selected -> {
            CharSequence text = "The value is now:" + variableBinding.mycheckbox.isChecked();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            variableBinding.mycheckbox.setChecked(selected);
            variableBinding.myswitch.setChecked(selected);
            variableBinding.myradiobtn.setChecked(selected);
        });

        variableBinding.mycheckbox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            model.isSelected.postValue(variableBinding.mycheckbox.isChecked());
        });

        variableBinding.myswitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            model.isSelected.postValue(variableBinding.myswitch.isChecked());
        });

        variableBinding.myradiobtn.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            model.isSelected.postValue(variableBinding.myradiobtn.isChecked());
        });

        variableBinding.myimagebtn.setOnClickListener(click -> {
            CharSequence text = "The width=" + variableBinding.myimagebtn.getWidth() + " and height=" + variableBinding.myimagebtn.getHeight();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        });
    }}