package com.zlxrx.library.lifecycleawarecache.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zlxrx.library.lifecycleawarecache.getCacheString
import com.zlxrx.library.lifecycleawarecache.setCache
import kotlinx.android.synthetic.main.activity_test.*

class TestActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        buttonSetCache.setOnClickListener {
            setCache("class", toString())
            setCache("class_name", TestActivityA::class.java.simpleName)
            refreshCacheUI()
        }

        buttonStartActivity.setOnClickListener {
            startActivity(Intent(this, TestActivityB::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        refreshCacheUI()
    }

    private fun refreshCacheUI() {
        textViewCache.postDelayed({
            textViewCache.text = "This is " + TestActivityA::class.java.simpleName +
                    "\n\n all activities cache: " + getCacheString()
        }, 500)
    }
}
