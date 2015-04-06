package com.example.tt.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.CheckBox;


public class MainActivity extends ActionBarActivity {
    CheckBox  mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheckBox  = (CheckBox) findViewById(R.id.checkBox);
        Button alphaBtn = (Button) findViewById(R.id.button);
        Button translateBtn = (Button) findViewById(R.id.button2);
        Button  rotateBtn  = (Button) findViewById(R.id.button3);
        Button scaleBtn  = (Button) findViewById(R.id.button4);
        Button setBtn = (Button) findViewById(R.id.button5);

        //Fade  the button out and back in

        ObjectAnimator alphaAnima = ObjectAnimator.ofFloat(alphaBtn, View.ALPHA,0);
        alphaAnima.setRepeatCount(1);
        alphaAnima.setRepeatMode(ValueAnimator.REVERSE);
        //alphaAnima.start();

        //Move the button over  right  and then  back

        ObjectAnimator translateAnima  = ObjectAnimator.ofFloat(translateBtn,View.TRANSLATION_X,800);
        translateAnima.setRepeatCount(1);
        translateAnima.setRepeatMode(ValueAnimator.REVERSE);
       // translateAnima.start();

        //Spin  button around in  360

        ObjectAnimator rotateAnima = ObjectAnimator.ofFloat(rotateBtn,View.ROTATION, 720);
        rotateAnima.setRepeatCount(1);
        rotateAnima.setRepeatMode(ValueAnimator.REVERSE);
     // rotateAnima.start();
        //Scale button in  X  and Y /*---- Note the use of PropertyValuesHolder to animate
        // multiple properties on the same object in parallel. */----

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X,2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y,2);

        ObjectAnimator scaleAnima = ObjectAnimator.ofPropertyValuesHolder(scaleBtn,pvhX,pvhY);
        scaleAnima.setRepeatCount(1);
        scaleAnima.setRepeatMode(ValueAnimator.REVERSE);

        //Run the  animations above  in sequence

        AnimatorSet setAnimator = new AnimatorSet();

        setAnimator.play(translateAnima).after(alphaAnima).before(rotateAnima);
        setAnimator.play(rotateAnima).before(scaleAnima);


        //

        setupAnimation(alphaBtn,alphaAnima,R.animator.fade);
        setupAnimation(translateBtn,translateAnima,R.animator.translate);
        setupAnimation(rotateBtn,rotateAnima,R.animator.rotate);
        setupAnimation(scaleBtn,scaleAnima,R.animator.scale);


    }
    private void setupAnimation(View  v, final Animator animation){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation.start();
            }
        });
    }
    private void setupAnimation(View  view, final Animator animation , final int animatorID){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCheckBox.isChecked()){
                    Log.d("Check", "get here");
                    Animator anim = AnimatorInflater.loadAnimator(MainActivity.this,animatorID);
                    Log.d("Check", "get here1" + animatorID );
                    anim.setTarget(v);
                    anim.start();
                    return;
                }
                animation.start();
            }
        });
    }

}
