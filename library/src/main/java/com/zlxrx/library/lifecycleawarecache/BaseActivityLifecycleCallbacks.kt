package com.zlxrx.library.lifecycleawarecache

import android.app.Activity
import android.app.Application
import android.os.Bundle

internal open class BaseActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityDestroyed(activity: Activity?) {
        // STUB
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        // STUB
    }

    override fun onActivityStarted(activity: Activity?) {
        // STUB
    }

    override fun onActivityResumed(activity: Activity?) {
        // STUB
    }

    override fun onActivityPaused(activity: Activity?) {
        // STUB
    }

    override fun onActivityStopped(activity: Activity?) {
        // STUB
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        // STUB
    }
}