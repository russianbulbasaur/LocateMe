package mein.mutter.locateme.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class fancyone_done extends ViewModel
{
    public MutableLiveData<Integer> a = new MutableLiveData<>();
    public void setdata(int i)
    {
        a.setValue(i);
    }
}