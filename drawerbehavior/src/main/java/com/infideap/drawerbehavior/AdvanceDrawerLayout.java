package com.infideap.drawerbehavior;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;

/**
 * Created by Shiburagi on 21/09/2017.
 */

public class AdvanceDrawerLayout extends DrawerLayout {
    private static final String TAG = AdvanceDrawerLayout.class.getSimpleName();
    private HashMap<Integer, Setting> settings = new HashMap<>();
    private int defaultScrimColor = 0x99000000;
    private float defaultDrawerElevation;
    private FrameLayout frameLayout;
    public View drawerView;

    public AdvanceDrawerLayout(Context context) {
        super(context);
        init(context, null, 0);

    }

    public AdvanceDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public AdvanceDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);

    }


    private void init(Context context, AttributeSet attrs, int defStyle) {
        defaultDrawerElevation = getDrawerElevation();
        addDrawerListener(new DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                AdvanceDrawerLayout.this.drawerView = drawerView;
                updateSlideOffset(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        frameLayout = new FrameLayout(context);

        super.addView(frameLayout);

    }


    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        child.setLayoutParams(params);
        addView(child);
    }

    @Override
    public void addView(View child) {
        if (child instanceof NavigationView) {
            super.addView(child);
        } else {
            CardView cardView = new CardView(getContext());
            cardView.setRadius(0);
            cardView.addView(child);
            cardView.setCardElevation(0);
            frameLayout.addView(cardView);
        }
    }

    public void setViewScale(int gravity, float percentage) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = new Setting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        setting.percentage = percentage;

        setting.scrimColor = Color.TRANSPARENT;
        setting.drawerElevation = 0;


    }

    public void setViewElevation(int gravity, float elevation) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = new Setting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        setting.scrimColor = Color.TRANSPARENT;
        setting.drawerElevation = 0;
        setting.elevation = elevation;
    }

    public void setViewScrimColor(int gravity, int scrimColor) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = new Setting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        setting.scrimColor = scrimColor;
    }

    public void setDrawerElevation(int gravity, float elevation) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = new Setting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        setting.elevation = 0;
        setting.drawerElevation = elevation;
    }

    public void setRadius(int gravity, float radius) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = new Setting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        setting.radius = radius;
    }


    public Setting getSetting(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        return settings.get(absGravity);

    }

    @Override
    public void setDrawerElevation(float elevation) {
        defaultDrawerElevation = elevation;
        super.setDrawerElevation(elevation);
    }

    @Override
    public void setScrimColor(@ColorInt int color) {
        defaultScrimColor = color;
        super.setScrimColor(color);
    }

    public void useCustomBehavior(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);

        if (!settings.containsKey(absGravity)) {
            Setting setting = new Setting();
            settings.put(absGravity, setting);
        }
    }

    public void removeCustomBehavior(int gravity) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);

        if (settings.containsKey(absGravity)) {
            settings.remove(absGravity);
        }
    }


    @Override
    public void openDrawer(final View drawerView, boolean animate) {
        super.openDrawer(drawerView, animate);

        post(new Runnable() {
            @Override
            public void run() {
                updateSlideOffset(drawerView, isDrawerOpen(drawerView) ? 1f : 0f);

            }
        });
    }

    private void updateSlideOffset(View drawerView, float slideOffset) {
        final int absHorizGravity = getDrawerViewAbsoluteGravity(Gravity.START);
        final int childAbsGravity = getDrawerViewAbsoluteGravity(drawerView);


        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            CardView child = (CardView) frameLayout.getChildAt(i);
            Setting setting = settings.get(childAbsGravity);
            float adjust = 0;

            if (setting != null) {

                child.setRadius((int) (setting.radius * slideOffset));
                super.setScrimColor(setting.scrimColor);
                super.setDrawerElevation(setting.drawerElevation);
                float percentage = 1f - setting.percentage;
                float reduceHeight = getHeight() * percentage * slideOffset;
                FrameLayout.LayoutParams params
                        = (FrameLayout.LayoutParams) child.getLayoutParams();
                params.topMargin = (int) (reduceHeight / 2);
                params.bottomMargin = (int) (reduceHeight / 2);
                child.setLayoutParams(params);
                child.setCardElevation(setting.elevation * slideOffset);
                adjust = setting.elevation;
                float width = childAbsGravity == absHorizGravity ?
                        drawerView.getWidth() + adjust : -drawerView.getWidth() - adjust;
                ViewCompat.setX(child, width * slideOffset);


            } else {
                super.setScrimColor(defaultScrimColor);
                super.setDrawerElevation(defaultDrawerElevation);
            }


        }

    }


    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (drawerView != null)
            updateSlideOffset(drawerView, isDrawerOpen(drawerView) ? 1f : 0f);
    }


    int getDrawerViewAbsoluteGravity(int gravity) {
        return GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this)) & Gravity.HORIZONTAL_GRAVITY_MASK;

    }

    int getDrawerViewAbsoluteGravity(View drawerView) {
        final int gravity = ((LayoutParams) drawerView.getLayoutParams()).gravity;
        return getDrawerViewAbsoluteGravity(gravity);
    }

    private class Setting {
        float percentage = 1f;
        int scrimColor = defaultScrimColor;
        float elevation = 0;
        float drawerElevation = defaultDrawerElevation;
        float radius;

        public float getDrawerElevation() {
            return drawerElevation;
        }

        public float getElevation() {
            return elevation;
        }

        public float getPercentage() {
            return percentage;
        }

        public float getRadius() {
            return radius;
        }

        public int getScrimColor() {
            return scrimColor;
        }
    }
}
