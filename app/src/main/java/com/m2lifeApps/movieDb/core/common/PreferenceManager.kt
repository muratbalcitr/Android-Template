package com.m2lifeApps.movieDb.core.common

import android.content.Context
import com.m2lifeApps.movieDb.core.platform.ProjectApplication.Companion.appContext
import java.util.*
import javax.inject.Inject

/**
 * @user:murat.balci
 */

class PreferenceManager @Inject constructor(val context: Context) {

    companion object {
        val packageName = appContext.packageName
        private const val PREFS = "namazvakti.prefs"
    }

    val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    val languageName: String?
        get() {
            val language: String = Locale.getDefault().language // tr, en, ...
            return if ((language == "tr")) "tr" else "en-US"
        }
/*

    */
/**
     * LATLNG
     *//*

    var latLng: DeviceLocation?
        get() = prefs.get(LOCATION)
        set(value) {
            prefs.set(LOCATION, value)
        }
*/

}
