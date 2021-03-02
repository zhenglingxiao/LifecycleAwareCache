package com.zlxrx.library.lifecycleawarecache.demo

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.zlxrx.library.lifecycleawarecache.getCacheString
import kotlinx.android.synthetic.main.activity_test_view.*

class TestViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view)
        buttonRemoveView.setOnClickListener {
            (customizeView.parent as ViewGroup).removeView(customizeView)
            refreshCacheUI()
        }
    }

    override fun onResume() {
        super.onResume()
        refreshCacheUI()
    }

    private fun refreshCacheUI() {
        textViewCache.postDelayed({
            textViewCache.text = "This is " + TestViewActivity::class.java.simpleName +
                    "\n\nall activities cache: " + getCacheString()
        }, 500)
    }
}
