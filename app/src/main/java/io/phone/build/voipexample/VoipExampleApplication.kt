package io.phone.build.voipexample

import android.app.Application
import android.util.Log
import androidx.preference.PreferenceManager
import io.phone.build.voipexample.ui.call.IncomingCallActivity
import io.phone.build.voipexample.ui.call.CallActivity
import io.phone.build.voipsdkandroid.configuration.ApplicationSetup
import io.phone.build.voipsdkandroid.configuration.Auth
import io.phone.build.voipsdkandroid.configuration.AuthAssistant
import io.phone.build.voipsdkandroid.startAndroidPIL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VoipExampleApplication : Application() {
    private val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate() {
        super.onCreate()

        val licenceKeyAuth = AuthAssistant(accessToken = "V1RCa1MwNUZOVlZaTTFacFZqSjRNMWRYTlc1a1YxSjBUa1F4YVU1dFJteGFSR3hvV1dwa2FscEhXVFJPVkZGNlRXMU5lazFxUlROT1ZHUnBUa2RSTUU5RVJURk5NRFZWVmxSS1QyUjZNRGxaYWxwb1dsZFJOVmxYU1ROWk1sSnRUMFJWTUUxNlNtcE5la2w0VG5wVk0xbHFVbXRPUkdkNFRsUk9hazF0ZUROWk1HaExaRzFXU1dFelpFNVZla0kxVkZWU1NtUXdlSFJOV0VKcVVqQnZNRlJITldGa1Ywa3lXVmRXYTA5WFJtbE9NazVyV21wbk1VNUVUWGxaZWsxNVRWUmpNVTR5U1RCYVJGRTBUVlJWZWxRd1VrSmtNRFV6VUZReGFVNXRSbXhhUkd4b1dXcGthbHBIV1RST1ZGRjZUVzFOZWsxcVJUTk9WR1JwVGtkUk1FOUVSVEZOTVhCeFVWUkNUMDFyVlhkVU1WSlNUV3MxY1ZOWVpHRldSVVY2VkRCU1MySkZPVVZSVkVaUVVrZDBObGRXVWs1T1ZtdzJXa2RvVG1Gck1EbFphbHBvV2xkUk5WbFhTVE5aTWxKdFQwUlZNRTE2U21wTmVrbDRUbnBWTTFscVVtdE9SR2Q0VGxST2ExSXphRFk9", licencesKey = "trialabc")

        startAndroidPIL {
            preferences = preferences.copy(
                useApplicationProvidedRingtone = prefs.getBoolean(
                    "use_application_provided_ringtone",
                    false
                )
            )

            licesce = licenceKeyAuth

            ApplicationSetup(
                application = this@VoipExampleApplication,
                activities = ApplicationSetup.Activities(
                    call = CallActivity::class.java,
                    incomingCall = IncomingCallActivity::class.java
                ),
                middleware = null,
                logger = { message, _ -> Log.i("PIL-Logger", message) },
                userAgent = "Android SDK Kotlin 1.8.0",
                notifyOnMissedCall = true,
                onMissedCallNotificationPressed = null
            )
        }
    }

    private suspend fun initializeResources() {

    }
}