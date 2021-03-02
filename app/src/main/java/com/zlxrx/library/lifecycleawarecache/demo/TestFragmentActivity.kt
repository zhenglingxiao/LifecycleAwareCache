package com.zlxrx.library.lifecycleawarecache.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zlxrx.library.lifecycleawarecache.CacheScope
import com.zlxrx.library.lifecycleawarecache.LifecycleAwareCache.getCacheString
import kotlinx.android.synthetic.main.activity_test_fragment.*

class TestFragmentActivity : AppCompatActivity() {

    private val fragments = mutableListOf<TestFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_fragment)

        buttonAddFragment.setOnClickListener {
            fragments.add(TestFragment().apply {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragmentContainer, this)
                    .commit()
            })
            refreshCacheUI()
        }

        buttonRemoveFragment.setOnClickListener {
            fragments.removeLastOrNull()?.let {
                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
                refreshCacheUI()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshCacheUI()
    }

    private fun refreshCacheUI() {
        textViewCache.postDelayed({
            textViewCache.text = "This is " + TestFragmentActivity::class.java.simpleName +
                    "\n\nall fragments cache: " + getCacheString(CacheScope.FRAGMENT)
        }, 500)
    }
}
