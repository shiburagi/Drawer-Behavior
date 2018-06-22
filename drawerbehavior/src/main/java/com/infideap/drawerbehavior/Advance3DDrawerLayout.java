package com.infideap.drawerbehavior;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Shiburagi on 21/09/2017.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Advance3DDrawerLayout extends AdvanceDrawerLayout {
    private static final String TAG = Advance3DDrawerLayout.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public Advance3DDrawerLayout(Context context) {
        super(context);
    }


    public Advance3DDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Advance3DDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    void updateSlideOffset(CardView child, AdvanceDrawerLayout.Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        updateSlideOffset(child, (Setting) setting, width, slideOffset, isLeftDrawer);

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    void updateSlideOffset(CardView child, Setting setting, float width, float slideOffset, boolean isLeftDrawer) {
        if (setting.degree > 0) {
            child.setRotationY((isLeftDrawer ? -1 : 1) * setting.degree * slideOffset);
        }
        ViewCompat.setX(child, width * slideOffset);


    }

    @Override
    AdvanceDrawerLayout.Setting createSetting() {
        return new Setting();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void setViewRotation(int gravity, float degree) {
        int absGravity = getDrawerViewAbsoluteGravity(gravity);
        Setting setting;
        if (!settings.containsKey(absGravity)) {
            setting = (Setting) createSetting();
            settings.put(absGravity, setting);
        } else
            setting = (Setting) settings.get(absGravity);

        setting.degree = degree;

        setting.scrimColor = Color.TRANSPARENT;
        setting.drawerElevation = 0;
    }

    class Setting extends AdvanceDrawerLayout.Setting {
        float degree;
    }
}
