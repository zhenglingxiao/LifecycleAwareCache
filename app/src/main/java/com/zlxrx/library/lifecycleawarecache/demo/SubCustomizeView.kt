package com.zlxrx.library.lifecycleawarecache.demo

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.zlxrx.library.lifecycleawarecache.containsActivityCache
import com.zlxrx.library.lifecycleawarecache.getActivityCache

class SubCustomizeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setBackgroundColor(Color.BLUE)
        addView(TextView(context).apply {
            text = SubCustomizeView::class.java.simpleName + " 点击获取缓存数据"
            setOnClickListener {
                Toast.makeText(
                    context,
                    getActivityCache<String>("subject") + ", " + getActivityCache<String>("userId"),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setOnLongClickListener {
                Toast.makeText(
                    context,
                    containsActivityCache("subject").toString(),
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
        })
    }
}