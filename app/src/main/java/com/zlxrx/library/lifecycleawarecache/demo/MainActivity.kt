package com.zlxrx.library.lifecycleawarecache.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zlxrx.library.lifecycleawarecache.clearCache
import com.zlxrx.library.lifecycleawarecache.containsCache
import com.zlxrx.library.lifecycleawarecache.getCacheString
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonActivityTest.setOnClickListener {
            startActivity(Intent(this, TestActivityA::class.java))
        }

        buttonFragmentTest.setOnClickListener {
            startActivity(Intent(this, TestFragmentActivity::class.java))
        }

        buttonViewTest.setOnClickListener {
            startActivity(Intent(this, TestViewActivity::class.java))
        }

        buttonClearAll.setOnClickListener {
            application.clearCache()
            refreshCacheUI()
        }

        buttonClearAll.setOnLongClickListener {
            Toast.makeText(
                this,
                application.containsCache("userId").toString(),
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
            textViewCache.text = "This is " + MainActivity::class.java.simpleName +
                    "\n\napplication cache: " + application.getCacheString() +
                    "\n\nall activities cache: " + getCacheString()
        }, 500)
    }
}
