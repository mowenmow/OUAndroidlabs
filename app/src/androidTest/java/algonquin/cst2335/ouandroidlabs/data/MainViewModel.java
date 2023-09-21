package algonquin.cst2335.ouandroidlabs.data;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString=new MutableLiveData<>();

    public MainViewModel(){

    }

}
