package com.projects.elad.hacklist.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Elad on 8/14/2016.
 */
public class Experimental {

//  private void toggleExtraOptions() {
//    int cx = (ExtraInfoLayout.getLeft() + ExtraInfoLayout.getRight());
//    int cy = ExtraInfoLayout.getTop();
//    int radius = Math.max(ExtraInfoLayout.getWidth(), ExtraInfoLayout.getHeight());
//    //Below Android LOLLIPOP Version
//    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//      SupportAnimator animator =
//          ViewAnimationUtils.createCircularReveal(ExtraInfoLayout, cx, cy, 0, radius);
//      animator.setInterpolator(new AccelerateDecelerateInterpolator());
//      animator.setDuration(700);
//
//      SupportAnimator animator_reverse = animator.reverse();
//
//      if (hidden) {
//        ExtraInfoLayout.setVisibility(View.VISIBLE);
//        animator.start();
//        hidden = false;
//      } else {
//        animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
//          @Override
//          public void onAnimationStart() {
//
//          }
//
//          @Override
//          public void onAnimationEnd() {
//            ExtraInfoLayout.setVisibility(View.INVISIBLE);
//            hidden = true;
//
//          }
//
//          @Override
//          public void onAnimationCancel() {
//
//          }
//
//          @Override
//          public void onAnimationRepeat() {
//
//          }
//        });
//        animator_reverse.start();
//      }
//    }
//    // Android LOLIPOP And ABOVE Version
//    else {
//      if (hidden) {
//        Animator anim = android.view.ViewAnimationUtils.
//            createCircularReveal(ExtraInfoLayout, cx, cy, 0, radius);
//        ExtraInfoLayout.setVisibility(View.VISIBLE);
//        anim.start();
//        hidden = false;
//      } else {
//        Animator anim = android.view.ViewAnimationUtils.
//            createCircularReveal(ExtraInfoLayout, cx, cy, radius, 0);
//        anim.addListener(new AnimatorListenerAdapter() {
//          @Override
//          public void onAnimationEnd(Animator animation) {
//            super.onAnimationEnd(animation);
//            ExtraInfoLayout.setVisibility(View.INVISIBLE);
//            hidden = true;
//          }
//        });
//        anim.start();
//      }
//    }
//  }



}
