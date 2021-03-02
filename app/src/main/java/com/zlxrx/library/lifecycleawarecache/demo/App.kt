package com.zlxrx.library.lifecycleawarecache.demo

import android.app.Application
import com.zlxrx.library.lifecycleawarecache.setCache

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setCache("userId", "284aa790-94e1-49b6-ad50-c685437eb92e")
    }
}