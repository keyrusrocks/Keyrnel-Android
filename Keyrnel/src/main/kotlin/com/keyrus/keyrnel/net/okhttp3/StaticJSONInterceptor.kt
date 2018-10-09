package com.keyrus.keyrnel.net.okhttp3

import android.content.Context
import android.util.Log

import com.keyrus.keyrnel.BuildConfig
import com.keyrus.keyrnel.helper.file.FileHelper
import okhttp3.*

import java.io.IOException

/**
 * Created by Paul Mougin on 12/01/2017.
 *
 * Short-circuits an HTTP request and return a static JSON from the assets folder.
 * By default, the JSON file corresponding to the "http://foo_url/foo_value/test" is the file test.json under the folder assets.
 *
 */
class StaticJSONInterceptor(private val context: Context) : okhttp3.Interceptor {

    companion object {
        private val TAG = StaticJSONInterceptor::class.java.simpleName
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response

        // Get Request URI.
        val uri = chain.request().url().uri()

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "fetching uri: " + uri.toString())
        }

        var filename = uri.path
        filename = filename.substring(filename.lastIndexOf('/') + 1).split(regex = "\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]

        val assetFile = filename.toLowerCase() + ".json"
        var data: String? = null

        try {
            data = FileHelper.getStringFromFile(context, assetFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (data?.isEmpty() != false) {
            response = Response.Builder()
                    .code(500)
                    .message("There is no such file as $assetFile in the assets folder.")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse("application/json"), ""))
                    .build()
        } else {
            response = Response.Builder()
                    .code(200)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .addHeader("Content-Type", "application/json")
                    .body(ResponseBody.create(MediaType.parse("application/json"), data))
                    .build()
        }

        return response
    }
}
