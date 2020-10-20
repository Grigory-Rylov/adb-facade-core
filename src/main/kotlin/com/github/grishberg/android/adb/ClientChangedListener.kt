package com.github.grishberg.android.adb

/**
 * Classes which implement this interface provide methods that deal
 * with [ClientWrapper]  changes.
 */
interface ClientChangedListener {
    /**
     * Sent when an existing client information changed.
     *
     *
     * This is sent from a non UI thread.
     * @param client the updated client.
     * @param changeMask the bit mask describing the changed properties. It can contain
     * any of the following values: [Client.CHANGE_INFO],
     * [Client.CHANGE_DEBUGGER_STATUS], [Client.CHANGE_THREAD_MODE],
     * [Client.CHANGE_THREAD_DATA], [Client.CHANGE_HEAP_MODE],
     * [Client.CHANGE_HEAP_DATA], [Client.CHANGE_NATIVE_HEAP_DATA]
     */
    fun clientChanged(client: ClientWrapper, changeMask: Int)
}
