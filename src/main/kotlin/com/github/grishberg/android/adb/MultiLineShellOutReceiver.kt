/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.grishberg.android.adb


internal abstract class MultiLineShellOutReceiver : ShellOutReceiver {
    private var trimLines = true

    /** unfinished message line, stored for next packet  */
    private var unfinishedLine: String? = null
    private val array: MutableCollection<String> = ArrayList()

    /**
     * Set the trim lines flag.
     *
     * @param trim whether the lines are trimmed, or not.
     */
    fun setTrimLine(trim: Boolean) {
        trimLines = trim
    }

    /* (non-Javadoc)
     * @see com.android.ddmlib.adb.IShellOutputReceiver#addOutput(byte[], int, int)
     */
    override fun addOutput(data: ByteArray, offset: Int, length: Int) {
        if (!isCancelled()) {
            var s = String(data, offset, length, Charsets.UTF_8)

            // ok we've got a string
            // if we had an unfinished line we add it.
            if (unfinishedLine != null) {
                s = unfinishedLine + s
                unfinishedLine = null
            }

            // now we split the lines
            array.clear()
            var start = 0
            do {
                var index = s.indexOf('\n', start) // $NON-NLS-1$

                // if \n was not found, this is an unfinished line
                // and we store it to be processed for the next packet
                if (index == -1) {
                    unfinishedLine = s.substring(start)
                    break
                }

                // we found a \n, in older devices, this is preceded by a \r
                var newlineLength = 1
                if (index > 0 && s[index - 1] == '\r') {
                    index--
                    newlineLength = 2
                }

                // extract the line
                var line = s.substring(start, index)
                if (trimLines) {
                    line = line.trim { it <= ' ' }
                }
                array.add(line)

                // move start to after the \r\n we found
                start = index + newlineLength
            } while (true)
            if (!array.isEmpty()) {
                // at this point we've split all the lines.
                // make the array
                val lines = array.toTypedArray()

                // send it for final processing
                processNewLines(lines)
            }
        }
    }

    /* (non-Javadoc)
     * @see com.android.ddmlib.adb.IShellOutputReceiver#flush()
    */
    override fun flush() {
        if (unfinishedLine != null) {
            processNewLines(arrayOf(unfinishedLine!!))
        }
        done()
    }

    /**
     * Terminates the process. This is called after the last lines have been through [ ][.processNewLines].
     */
    fun done() {
        // do nothing.
    }

    /**
     * Called when new lines are being received by the remote process.
     *
     *
     * It is guaranteed that the lines are complete when they are given to this method.
     *
     * @param lines The array containing the new lines.
     */
    abstract fun processNewLines(lines: Array<String>)
}
