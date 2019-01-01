package com.broa.fac;

import android.content.Context;
import android.content.Intent;

public class Navigator {

    //intent to MainActivity2
    public void statrMainActivity2(Context context){
        Intent intent = MainActivity2.strartActivity2(context);
        context.startActivity(intent );
    }

    public void startMainActivity(Context context){
        Intent intent = MainActivity.startMainActivity(context);
        intent.putExtra("eisa","eisa");
        context.startActivity(intent);
    }


}
