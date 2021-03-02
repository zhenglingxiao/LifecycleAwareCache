package com.zlxrx.library.lifecycleawarecache.hub

import android.app.Activity
import com.zlxrx.library.lifecycleawarecache.BaseActivityLifecycleCallbacks
import com.zlxrx.library.lifecycleawarecache.clearCache

internal class ActivityCacheHub : ComponentCacheHub<Activity>() {

    private val activityLifecycleCallbacks by lazy {
        object : BaseActivityLifecycleCallbacks() {
            override fun onActivityDestroyed(activity: Activity?) {
                activity?.clearCache()
            }
        }
    }

    override fun checkInit(entity: Activity) {
        entity.application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    override fun getKeyPrefix(entity: Activity): String {
        return "${entity.hashCode()}${getKeyDelimiter()}"
    }
}
