package com.github.grishberg.android.adb

interface DeviceChangedListener {
    /**
     * Sent when the a device is connected to the [AndroidDebugBridge].
     *
     *
     * This is sent from a non UI thread.
     * @param device the new device.
     */
    fun deviceConnected(device: ConnectedDeviceWrapper)

    /**
     * Sent when the a device is connected to the [AndroidDebugBridge].
     *
     *
     * This is sent from a non UI thread.
     * @param device the new device.
     */
    fun deviceDisconnected(device: ConnectedDeviceWrapper)

    /**
     * Sent when a device data changed, or when clients are started/terminated on the device.
     *
     *
     * This is sent from a non UI thread.
     * @param device the device that was updated.
     * @param changeMask the mask describing what changed. It can contain any of the following
     * values: [IDevice.CHANGE_BUILD_INFO], [IDevice.CHANGE_STATE],
     * [IDevice.CHANGE_CLIENT_LIST]
     */
    fun deviceChanged(device: ConnectedDeviceWrapper, changeMask: Int)
}
