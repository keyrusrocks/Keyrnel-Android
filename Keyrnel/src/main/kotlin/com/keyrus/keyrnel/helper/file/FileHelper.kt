package com.keyrus.keyrnel.helper.file

import android.content.Context
import android.util.Log

import com.keyrus.keyrnel.BuildConfig

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Created by Paul Mougin on 12/01/2017.
 */

object FileHelper {

    private val TAG = FileHelper::class.java.simpleName

    /**
     * Convert an InputStream into a String
     * @param input the InputString to parse
     * @return the read string
     * @throws Exception
     */
    @Throws(Exception::class)
    fun convertStreamToString(input: InputStream): String {
        val reader = BufferedReader(InputStreamReader(input))
        val builder = StringBuilder()
        do {
            val line = reader.readLine()
            builder.append(line).append("\n")
        } while (line  != null)

        reader.close()
        return builder.toString()
    }

    /**
     * Read a string from a given file
     * @param context Not null App Context
     * @param filePath path to the file to read (from Asset folder)
     * @return the read String
     * @throws Exception
     */
    @Throws(Exception::class)
    fun getStringFromFile(context: Context, filePath: String): String {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "Open file at: $filePath")
        }

        val stream = context.resources.assets.open(filePath)
        val result = convertStreamToString(stream)

        //Make sure you close all streams.
        stream.close()
        return result
    }
}
