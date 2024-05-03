package io.phone.build.voipsdkandroid.helpers

import io.phone.build.voiplib.model.Call

internal val Call.identifier: String
    get() = linphoneCall.hashCode().toString()