package com.infideap.drawerbehavior

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.cardview.widget.CardView

/**
 * Created by Shiburagi on 21/09/2017.
 */
class Advance3DDrawerLayout : AdvanceDrawerLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun updateSlideOffset(child: CardView, setting: AdvanceDrawerLayout.Setting?, width: Float, slideOffset: Float, isLeftDrawer: Boolean) {
        updateSlideOffset(child, setting as Setting, width, slideOffset, isLeftDrawer)
    }

    private fun updateSlideOffset(child: CardView, setting: Setting, width: Float, slideOffset: Float, isLeftDrawer: Boolean) {
        if (setting.degree > 0) {
            val percentage = setting.degree / 90f
            child.x = width * slideOffset - child.width / 2.0f * percentage * slideOffset
            child.rotationY = (if (isLeftDrawer) -1 else 1) * setting.degree * slideOffset
        } else super.updateSlideOffset(child, setting, width, slideOffset, isLeftDrawer)
    }

    override fun createSetting(): AdvanceDrawerLayout.Setting {
        return Setting()
    }

    fun setRotation(gravity: Int, degree: Float) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        val setting: Setting?
        if (!settings.containsKey(absGravity)) {
            setting = createSetting() as Setting
            settings[absGravity] = setting
        } else setting = settings[absGravity] as Setting?

        setting?.degree = if (degree > 45) 45f else degree
        setting?.scrimColor = Color.TRANSPARENT
        setting?.drawerElevation = 0f
    }

    inner class Setting : AdvanceDrawerLayout.Setting() {
        var degree = 0f
    }

    companion object {
        private val TAG = Advance3DDrawerLayout::class.java.simpleName
    }
}