package com.example.kp_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iconLogo, letterR, letterE, letterA, letterD, letterChamThan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconLogo = findViewById(R.id.icon_logo);
        letterR = findViewById(R.id.letterR);
        letterE = findViewById(R.id.letterE);
        letterA = findViewById(R.id.letterA);
        letterD = findViewById(R.id.letterD);
        letterChamThan = findViewById(R.id.letterChamThan);

        letterR.setVisibility(View.INVISIBLE);
        letterE.setVisibility(View.INVISIBLE);
        letterA.setVisibility(View.INVISIBLE);
        letterD.setVisibility(View.INVISIBLE);
        letterChamThan.setVisibility(View.INVISIBLE);
        startAnimations();




    }

    private void startAnimations() {
        Animation fadeInLogoAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeInLogoAnimation.setDuration(1000);

        iconLogo.startAnimation(fadeInLogoAnimation);

        fadeInLogoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startLetterAnimation(letterR, R.anim.slide_up, 10);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startLetterAnimation(final ImageView letter, int animationId, final long delay) {
        Animation letterAnimation = AnimationUtils.loadAnimation(this, animationId);
        letterAnimation.setStartOffset(delay);
        letterAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                letter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Check which letter just animated and start the next one
                if (letter == letterR) {
                    startLetterAnimation(letterE, R.anim.slide_up, delay);
                } else if (letter == letterE) {
                    startLetterAnimation(letterA, R.anim.slide_up, delay);
                } else if (letter == letterA) {
                    startLetterAnimation(letterD, R.anim.slide_up, delay);
                } else if (letter == letterD) {
                    startLetterAnimation(letterChamThan, R.anim.slide_up, delay);
                } else if (letter == letterChamThan) {
                    goHome();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        letter.startAnimation(letterAnimation);
    }

    private void goHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}