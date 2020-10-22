package com.github.grishberg.android.adb

/**
 * Handlers able to act on Method profiling info
 */
interface MethodProfilingHandler {
    /**
     * Called when a method tracing was successful.
     * @param remoteFilePath the device-side path of the trace file.
     * @param client the client that was profiled.
     */
    fun onSuccess(remoteFilePath: String, client: ClientWrapper)

    /**
     * Called when a method tracing was successful.
     * @param data the data containing the trace file, streamed from the VM
     * @param client the client that was profiled.
     */
    fun onSuccess(data: ByteArray, client: ClientWrapper)

    /**
     * Called when method tracing failed to start
     * @param client the client that was profiled.
     * @param message an optional (`null` ok) error message to be displayed.
     */
    fun onStartFailure(client: ClientWrapper, message: String)

    /**
     * Called when method tracing failed to end on the VM side
     * @param client the client that was profiled.
     * @param message an optional (`null` ok) error message to be displayed.
     */
    fun onEndFailure(client: ClientWrapper, message: String)
}
