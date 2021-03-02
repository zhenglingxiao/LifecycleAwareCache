package com.zlxrx.library.lifecycleawarecache.demo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.zlxrx.library.lifecycleawarecache.setActivityCache

class CustomizeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(Color.RED)
        setPadding(16, 16, 16, 16)
        setActivityCache("subject", "English")
        orientation = HORIZONTAL
        addView(
            TextView(context).apply {
                text = CustomizeView::class.java.simpleName
                setActivityCache("userId", "AABBCC")
            },
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                marginEnd = 16
            }
        )
        addView(SubCustomizeView(context))
    }
}