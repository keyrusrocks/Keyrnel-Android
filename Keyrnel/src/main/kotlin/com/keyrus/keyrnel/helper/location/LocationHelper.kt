package com.keyrus.keyrnel.helper.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log

/**
 * Created by Paul Mougin on 01/02/2017.
 */

object LocationHelper {

    private val TAG = LocationHelper::class.java.simpleName

    /**
     * Return the [Address] from a given zip code with [Geocoder] object.
     * This method is not async ! Do not execute it in the main thread.
     * @param context Application context.
     * @param locationName Name of the wanted location.
     * @return the [Address] of the location name or null if does not match.
     */
    fun getAddressFromLocationName(context: Context, locationName: String): Address? {
        val geocoder = Geocoder(context)
        return try {
            val addresses = geocoder.getFromLocationName(locationName, 1)
            if (addresses != null && !addresses.isEmpty()) {
                addresses[0]
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, e.localizedMessage)
            null
        }

    }
}
