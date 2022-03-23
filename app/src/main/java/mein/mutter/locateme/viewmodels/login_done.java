package mein.mutter.locateme.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class login_done extends ViewModel {
    public MutableLiveData<Integer> a = new MutableLiveData<>();
    public void setData(int i)
    {
        a.setValue(i);
    }
}
