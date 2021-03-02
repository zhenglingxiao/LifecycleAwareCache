package com.zlxrx.library.lifecycleawarecache.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zlxrx.library.lifecycleawarecache.containsCache
import com.zlxrx.library.lifecycleawarecache.getCacheString
import com.zlxrx.library.lifecycleawarecache.setCache
import kotlinx.android.synthetic.main.activity_test.*

class TestActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        buttonSetCache.setOnClickListener {
            setCache("class_hash", toString())
            setCache("class_name", TestActivityB::class.java.simpleName)
            refreshCacheUI()
        }

        buttonStartActivity.text = "跳转到 A Activity"
        buttonStartActivity.setOnClickListener {
            startActivity(Intent(this, TestActivityA::class.java))
        }
        buttonStartActivity.setOnLongClickListener {
            Toast.makeText(
                this,
                containsCache("class_name").toString(),
                Toast.LENGTH_SHORT
            ).show()
            true
        }
    }

    override fun onResume() {
        super.onResume()
        refreshCacheUI()
    }

    private fun refreshCacheUI() {
        textViewCache.postDelayed({
            textViewCache.text = "This is " + TestActivityB::class.java.simpleName +
                    "\n\n all activities cache: " + getCacheString()
        }, 500)
    }
}
