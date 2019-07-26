package com.example.hardwarelayertest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView animationText;
    TextView startText;
    ObjectAnimator objectAnimator1;
    ObjectAnimator objectAnimator2;
    ObjectAnimator objectAnimator3;
    ObjectAnimator objectAnimator4;
    ObjectAnimator objectAnimator5;
    AnimatorSet animatorSet;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationText = findViewById(R.id.text);
        startText = findViewById(R.id.start);

        animatorSet = new AnimatorSet();
        objectAnimator1 = ObjectAnimator.ofFloat(animationText, View.TRANSLATION_X, 150);
        objectAnimator2 = ObjectAnimator.ofFloat(animationText, View.ALPHA, 0);
        objectAnimator3 = ObjectAnimator.ofFloat(animationText, View.TRANSLATION_Y, 150);
        objectAnimator4 = ObjectAnimator.ofFloat(animationText, View.SCALE_X, 150);
        objectAnimator5 = ObjectAnimator.ofFloat(animationText, View.SCALE_Y, 150);
        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4, objectAnimator5);
        animatorSet.setDuration(500);

        objectAnimator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                animationText.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                animationText.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animationText.setLayerType(View.LAYER_TYPE_NONE, null);
                i = 0;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animationText.setText(String.format("%s%d", animationText.getText().toString(), i));
                i++;
            }
        });

        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animatorSet.start();
            }
        });

    }
}
