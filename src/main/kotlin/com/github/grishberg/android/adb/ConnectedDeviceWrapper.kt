package com.github.grishberg.android.adb

import java.util.concurrent.TimeUnit


interface ConnectedDeviceWrapper {
    /**
     * Device Serial number.
     */
    val serialNumber: String

    fun executeShellCommand(cmd: String, receiver: ShellOutReceiver)

    fun executeShellCommand(
        cmd: String, receiver: ShellOutReceiver,
        maxTimeToOutputResponse: Long,
        maxTimeUnits: TimeUnit
    )

    fun getClient(applicationName: String): ClientWrapper?
}
