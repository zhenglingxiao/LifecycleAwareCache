package com.zlxrx.library.lifecycleawarecache.hub

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zlxrx.library.lifecycleawarecache.BaseActivityLifecycleCallbacks
import com.zlxrx.library.lifecycleawarecache.clearCache

internal class ApplicationCacheHub : BaseCacheHub<Application>() {

    private val aliveActivities = ArrayList<Activity>()

    private val activityLifecycleCallbacks by lazy {
        object : BaseActivityLifecycleCallbacks() {

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.let {
                    aliveActivities.add(it)
                }
            }

            override fun onActivityDestroyed(activity: Activity?) {
                activity?.let {
                    aliveActivities.remove(it)
                }
            }
        }
    }

    override fun clear(entity: Application?) {
        super.clear(entity)
        aliveActivities.forEach {
            it.clearCache()
        }
    }

    override fun checkInit(entity: Application) {
        entity.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}