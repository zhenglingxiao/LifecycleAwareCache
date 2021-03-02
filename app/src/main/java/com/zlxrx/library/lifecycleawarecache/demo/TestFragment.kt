package com.zlxrx.library.lifecycleawarecache.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zlxrx.library.lifecycleawarecache.*

class TestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return TextView(context).apply {
            height = 160
            width = 160
            text = this@TestFragment.toString()
            setOnClickListener {
                Toast.makeText(
                    context,
                    getCache<String>("class"),
                    Toast.LENGTH_SHORT
                ).show()
            }
            setOnLongClickListener {
                Toast.makeText(
                    context,
                    containsCache("class").toString(),
                    Toast.LENGTH_SHORT
                ).show()
                true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setCache("class", toString())
        setCache("class_name", TestFragment::class.java.simpleName, CacheScope.ACTIVITY)
        super.onViewCreated(view, savedInstanceState)
    }
}