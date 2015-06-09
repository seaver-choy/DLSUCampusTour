package com.qualcomm.QCARSamples.ImageTargets;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class ViewDetails extends ActionBarActivity implements
        GestureDetector.OnGestureListener {

    private GestureDetectorCompat mDetector;

    Button buttonPrev, buttonNext;
    ViewFlipper viewFlipper;

    Animation slide_in_left, slide_out_right;
    Animation slide_in_right, slide_out_left;

    int gallery_grid_Images[]={
            R.drawable.gox1,
            R.drawable.menuhelp,
            R.drawable.menustart,
            R.drawable.menulocations,
            R.drawable.pauseicon,
            R.drawable.target1,
            R.drawable.door2,
            R.drawable.door3,
            R.drawable.target2,
            R.drawable.pattern1

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        mDetector = new GestureDetectorCompat(this,this);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        viewFlipper.setMeasureAllChildren(false);

        for(int i=0 ;i<gallery_grid_Images.length;i++)
        {
            //  This will create dynamic image view and add them to ViewFlipper
            setFlipperImage(gallery_grid_Images[i]);
        }

        slide_in_left = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_right);

        slide_in_right = AnimationUtils.loadAnimation(this,
                R.anim.slide_in_right);
        slide_out_left = AnimationUtils.loadAnimation(this,
                R.anim.slide_out_left);

    }

    private void setFlipperImage(int res) {
        Log.i("Set Filpper Called", res + "");
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        float sensitvity = 50;

        if((e1.getX() - e2.getX()) > sensitvity){
            viewFlipper.setInAnimation(slide_in_right);
            viewFlipper.setOutAnimation(slide_out_left);
            viewFlipper.showNext();

        }else if((e2.getX() - e1.getX()) > sensitvity){
            viewFlipper.setInAnimation(slide_in_left);
            viewFlipper.setOutAnimation(slide_out_right);
            viewFlipper.showPrevious();
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

}