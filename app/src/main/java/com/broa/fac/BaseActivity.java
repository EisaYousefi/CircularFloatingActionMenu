package com.broa.fac;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements AlertDialogHandler {



    @BindView(R.id.activity_layout)
    LinearLayout activity2Layout;

    @BindView(R.id.dialog_layout)
    LinearLayout dialogLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_dialog)
    FloatingActionButton fabShowDialog;

    @BindView(R.id.fab_view_activity)
    FloatingActionButton fabViewActivity;

    @BindView(R.id.fabMain)
    FloatingActionButton mainFab;
    @BindView(R.id.result_CallBack)
    TextView callBack;
     Dialog dialog;
    private boolean flag=true;

    //Intent to MainActivity
    public static Intent startMainActivity(Context context){
        return new Intent(context,MainActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getIdView());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final Animation animationShowLayout =AnimationUtils.loadAnimation(this,R.anim.show_layout);
        final Animation animationHidLayout =AnimationUtils.loadAnimation(this,R.anim.hide_layout);
        final Animation animationShowButton =AnimationUtils.loadAnimation(this,R.anim.show_button);
        final Animation animationHidButton =AnimationUtils.loadAnimation(this,R.anim.hide_button);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag==true) {
                    flag=false;
                    dialogLayout.setVisibility(View.VISIBLE);
                    activity2Layout.setVisibility(View.VISIBLE);
                    dialogLayout.startAnimation(animationShowLayout);
                    activity2Layout.startAnimation(animationShowLayout);
                    //mainFab.startAnimation(animationShowButton);

                    mainFab.setImageDrawable(getResources().getDrawable(R.drawable.miness));
                }else {
                    flag=true;
                    dialogLayout.startAnimation(animationHidLayout);
                    activity2Layout.startAnimation(animationHidLayout);
                   // mainFab.startAnimation(animationHidButton);
                    dialogLayout.setVisibility(View.GONE);
                    activity2Layout.setVisibility(View.GONE);

                    mainFab.setImageDrawable(getResources().getDrawable(R.drawable.plass));
                }
            }

        });


        //Show MainActivity2
        fabViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity2();
            }
        });

        //Show Dialog
        fabShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  showdialod(BaseActivity.this);
            }
        });

    }
    @Override
    public void onPosetiveClicked(String text) {
        if (!text.equals("")) {
            callBack.setText(text);
            dialog.dismiss();
        }
        else {
            toastCustomer("فیلد خالی است");
            callBack.setText("");
        }
    }
    protected  void showdialod(final AlertDialogHandler handler){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        RelativeLayout layout =dialog.findViewById(R.id.layout_dialog);
        layout.setBackground(new ColorDrawable(Color.TRANSPARENT));
        Button button = dialog.findViewById(R.id.btn_callback_dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt = (TextView) dialog.findViewById(R.id.edt_dialog) ;
                handler.onPosetiveClicked(txt.getText().toString());

            }
        });
        dialog.show();
    }

    private void toastCustomer(String tv) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.tost,
                (ViewGroup) findViewById(R.id.linear_layout));
        TextView textView = view.findViewById(R.id.tv_Toast);
        textView.setText(tv);
        Toast toast = new Toast(BaseActivity.this);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    protected  void startMainActivity2(){
        Navigator navigator = new Navigator();
        navigator.statrMainActivity2(this);
    }

    protected abstract int getIdView();
}
