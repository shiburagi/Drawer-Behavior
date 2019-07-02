package com.infideap.drawerbehavior;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

/**
 * Created by Shiburagi on 21/09/2017.
 */

public class AdvanceDrawerLayout extends DrawerLayout {
    private static final String TAG = AdvanceDrawerLayout.class.getSimpleName();
    HashMap<Integer, Setting> settings = new HashMap<>();
    private int defaultScrimColor = 0x99000000;
    private float defaultDrawerElevation;
    private FrameLayout frameLayout;
    public View drawerView;
    private int statusBarColor;
    private boolean defaultFitsSystemWindows;
    private float contrastThreshold = 3;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            defaultFitsSystemWindows = getFitsSystemWindows();
        }
        if (!isInEditMode())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = getActivity().getWindow().getStatusBarColor();
            }
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
        frameLayout.setPadding(0, 0, 0, 0);
        super.addView(frameLayout);

    }


//    @Override
//    public void setFitsSystemWindows(boolean fitSystemWindows) {
////        defaultFitsSystemWindows = fitSystemWindows;
//
//        super.setFitsSystemWindows(fitSystemWindows);
//
//        if (ViewCompat.getFitsSystemWindows(this)) {
//            if (Build.VERSION.SDK_INT >= 21) {
//                this.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
//                    @SuppressLint("RestrictedApi")
//                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
//                        DrawerLayout drawerLayout = (DrawerLayout)view;
//                        drawerLayout.setChildInsets(insets, insets.getSystemWindowInsetTop() > 0);
//                        return insets.consumeSystemWindowInsets();
//                    }
//                });
//                this.setSystemUiVisibility(1280);
//                TypedArray a = getC.obtainStyledAttributes(THEME_ATTRS);
//
//                try {
//                    this.mStatusBarBackground = a.getDrawable(0);
//                } finally {
//                    a.recycle();
//                }
//            } else {
//                this.mStatusBarBackground = null;
//            }
//        }
//    }

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
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                cardView.setContentPadding(-6, -9, -6, -9);
            }
            frameLayout.addView(cardView);
        }
    }

    public void setViewScale(int gravity, float percentage) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        assert setting != null;
        setting.percentage = percentage;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (percentage < 1) {
                setStatusBarBackground(null);
                setSystemUiVisibility(0);
            }
        }

        setting.scrimColor = Color.TRANSPARENT;
        setting.drawerElevation = 0;
    }

    public void setViewElevation(int gravity, float elevation) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        assert setting != null;
        setting.scrimColor = Color.TRANSPARENT;
        setting.drawerElevation = 0;
        setting.elevation = elevation;
    }

    public void setViewScrimColor(int gravity, int scrimColor) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        assert setting != null;
        setting.scrimColor = scrimColor;
    }

    public void setDrawerElevation(int gravity, float elevation) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
            settings.put(absGravity, setting);
        } else
            setting = settings.get(absGravity);

        assert setting != null;
        setting.elevation = 0;
        setting.drawerElevation = elevation;
    }


    public void setRadius(int gravity, float radius) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = createSetting();
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
            Setting setting = createSetting();
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

        Activity activity = getActivity();
        Window window = activity.getWindow();


        boolean isRtl = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isRtl = getLayoutDirection() == View.LAYOUT_DIRECTION_RTL ||
                    window.getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL ||
                    getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        }

        for (int i = 0; i < frameLayout.getChildCount(); i++) {

            CardView child = (CardView) frameLayout.getChildAt(i);
            Setting setting = settings.get(childAbsGravity);
            float adjust;

            if (setting != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && setting.percentage < 1.0) {

                    if (drawerView.getBackground() instanceof ColorDrawable) {

                        int color = ColorUtils.setAlphaComponent(statusBarColor, (int) (255 - 255 * slideOffset));
                        window.setStatusBarColor(color);

                        int bgColor = ((ColorDrawable) drawerView.getBackground()).getColor();
                        window.getDecorView().setBackgroundColor(bgColor);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            setSystemUiVisibility(ColorUtils.calculateContrast(Color.WHITE, bgColor) < contrastThreshold && slideOffset > 0.4 ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0);
                        }
                    }


                }

                child.setRadius((int) (setting.radius * slideOffset));
                super.setScrimColor(setting.scrimColor);
                super.setDrawerElevation(setting.drawerElevation);
                float percentage = 1f - setting.percentage;
                ViewCompat.setScaleY(child, 1f - percentage * slideOffset);
                child.setCardElevation(setting.elevation * slideOffset);
                adjust = setting.elevation;
                boolean isLeftDrawer;
                if (isRtl)
                    isLeftDrawer = childAbsGravity != absHorizGravity;
                else
                    isLeftDrawer = childAbsGravity == absHorizGravity;
                float width = isLeftDrawer ?
                        drawerView.getWidth() + adjust : -drawerView.getWidth() - adjust;
                updateSlideOffset(child, setting, width, slideOffset, isLeftDrawer);

            } else {
                super.setScrimColor(defaultScrimColor);
                super.setDrawerElevation(defaultDrawerElevation);
            }


        }

    }

    public void setContrastThreshold(float contrastThreshold) {
        this.contrastThreshold = contrastThreshold;
    }

    Activity getActivity() {
        return getActivity(getContext());
    }

    Activity getActivity(Context context) {
        if (context == null) return null;
        if (context instanceof Activity) return (Activity) context;
        if (context instanceof ContextWrapper)
            return getActivity(((ContextWrapper) context).getBaseContext());
        return null;
    }

    void updateSlideOffset(CardView child, Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        ViewCompat.setX(child, width * slideOffset);
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


    Setting createSetting() {
        return new Setting();
    }

    class Setting {
        boolean fitsSystemWindows;
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
