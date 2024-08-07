package io.phone.build.voipsdkandroid

import androidx.lifecycle.ProcessLifecycleOwner
import io.phone.build.voipsdkandroid.android.ApplicationStateListener
import io.phone.build.voipsdkandroid.configuration.ApplicationSetup
import io.phone.build.voipsdkandroid.configuration.Auth
import io.phone.build.voipsdkandroid.configuration.AuthAssistant
import io.phone.build.voipsdkandroid.configuration.Preferences
import io.phone.build.voipsdkandroid.di.initPilKoin
import io.phone.build.voipsdkandroid.exception.PILAlreadyInitializedException
import io.phone.build.voipsdkandroid.service.LicenceRequest
import io.phone.build.voipsdkandroid.service.api

class Builder internal constructor() {

    var preferences: Preferences = Preferences.DEFAULT
    var licesce: AuthAssistant? = null

    internal fun start(applicationSetup: ApplicationSetup): PIL {
        if (PIL.isInitialized) {
            throw PILAlreadyInitializedException()
        }

        initPilKoin(applicationSetup.application)

        val pil = PIL(applicationSetup)

        setupApplicationBackgroundListeners(pil)
        pil.preferences = this.preferences
        licesce?.let { pil.licenceConfig = it }
        /*auth?.let { pil.auth = it }*/

        return pil
    }

    private fun setupApplicationBackgroundListeners(pil: PIL) {
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationStateListener(pil))
    }
}

class InvalidLicenceException(
    message: String,
    val errorCode: Int? = null,
    val details: String? = null
) : Exception(message)

/**
 * Initialize the Android PIL, this should be called in your Application's onCreate method.
 *
 */
fun startAndroidPIL(init: Builder.() -> ApplicationSetup): PIL {
    val builder = Builder()

    val applicationSetup = init.invoke(builder)

    return builder.start(applicationSetup)
}
