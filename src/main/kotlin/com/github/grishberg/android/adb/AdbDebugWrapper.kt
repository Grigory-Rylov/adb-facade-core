package com.github.grishberg.android.adb

/**
 * Contains methods for
 * -recording java method sampling/tracing
 * -setting debug port
 * e.t.c
 */
interface AdbDebugWrapper {
    /**
     * Sets the handler to receive notifications when an HPROF dump succeeded or failed.
     * This method is deprecated, please register a client listener and listen for CHANGE_HPROF.
     */
    fun setMethodProfilingHandler(handler: MethodProfilingHandler)

    /**
     * Sets the debug port used by the first {@link Client}.
     * <p>Once a port is used, the next Client will use port + 1. Quitting applications will
     * release their debug port, and new clients will be able to reuse them.
     * <p>This must be called before {@link AndroidDebugBridge#init(boolean)}.
     */
    fun setDebugPortBase(port: Int)

    /**
     * Returns the debug port used by the selected [ClientWrapper].
     */
    fun getSelectedDebugPort(): Int

    /**
     * Set trace buffer size in mb.
     */
    fun setProfilerBufferSizeMb(sizeInMb: Int)
}
