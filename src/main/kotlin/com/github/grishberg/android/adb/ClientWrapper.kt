package com.github.grishberg.android.adb

import java.io.IOException
import java.util.concurrent.TimeUnit


interface ClientWrapper {
    @Throws(IOException::class)
    fun startSamplingProfiler(samplingInterval: Int, timeUnit: TimeUnit)

    @Throws(IOException::class)
    fun stopSamplingProfiler()


    @Throws(IOException::class)
    fun startMethodTracer()

    @Throws(IOException::class)
    fun stopMethodTracer()

    fun startOpenGlTracing(): Boolean
    fun stopOpenGlTracing(): Boolean
    fun requestMethodProfilingStatus()

    /**
     * Makes the VM dump an HPROF file
     */
    fun dumpHprof()

    /**
     * Forces the client to execute its garbage collector.
     */
    fun executeGarbageCollector()
}

