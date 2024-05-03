package io.phone.build.voipexample

import android.app.Application
import android.util.Log
import androidx.preference.PreferenceManager
import io.phone.build.voipexample.ui.call.IncomingCallActivity
import io.phone.build.voipexample.ui.call.CallActivity
import io.phone.build.voipsdkandroid.configuration.ApplicationSetup
import io.phone.build.voipsdkandroid.configuration.Auth
import io.phone.build.voipsdkandroid.startAndroidPIL

class VoipExampleApplication : Application() {
    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate() {
        super.onCreate()

        val username = prefs.getString("username", "") ?: ""
        val password = prefs.getString("password", "") ?: ""
        val domain = prefs.getString("domain", "") ?: ""
        val port = (prefs.getString("port", "0") ?: "0").toInt()
        val proxy = prefs.getString("proxy", "") ?: ""
        val transport = prefs.getString("transport", "") ?: ""

        val userAuth = Auth(
            username = username,
            password = password,
            domain = domain,
            port = port,
            proxy = proxy,
            transport = transport,
            secure = true
        )

        startAndroidPIL {
            preferences = preferences.copy(
                useApplicationProvidedRingtone = prefs.getBoolean(
                    "use_application_provided_ringtone",
                    false
                )
            )
            auth = if (userAuth.isValid) userAuth else null

            ApplicationSetup(
                application = this@VoipExampleApplication,
                activities = ApplicationSetup.Activities(
                    call = CallActivity::class.java,
                    incomingCall = IncomingCallActivity::class.java
                ),
                middleware = null,
                logger = { message, _ -> Log.i("PIL-Logger", message) },
                userAgent = "Android-PIL-Example-Application",
                notifyOnMissedCall = true,
                onMissedCallNotificationPressed = null
            )
        }
    }
}