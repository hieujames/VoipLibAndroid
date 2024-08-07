package io.phone.build.voipsdkandroid.audio

data class AudioState(
    val currentRoute: AudioRoute,
    val availableRoutes: Array<AudioRoute>,
    val bluetoothDeviceName: String?,
    val isMicrophoneMuted: Boolean,
    val bluetoothRoutes: Array<BluetoothAudioRoute>,
)
