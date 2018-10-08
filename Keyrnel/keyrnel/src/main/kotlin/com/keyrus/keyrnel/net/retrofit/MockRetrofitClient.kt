package com.keyrus.keyrnel.net.retrofit

import android.content.Context
import android.net.Uri
import android.util.Log
import com.keyrus.keyrnel.BuildConfig
import retrofit.client.Client
import retrofit.client.Request
import retrofit.client.Response
import retrofit.mime.TypedByteArray
import java.io.IOException

/**
 * Class: MockRetrofit Client
 * Information: Mock okHTTP Client with a response read from a text file in the asset
 * ONLY for Retrofit 1.9
 *
 * Created by Paul on 26/04/2016.
 */
class MockRetrofitClient(private var  context: Context) : Client {

    var responseTime = 0
    var fileName: String? = null
    var fileExtension = ".json"

    /**
     * Complex Constructor
     * @param context: Non null context to reach application asset
     * @param fileName: file name in the asset folder
     * @param responseTime: delay time for the response in milliseconds
     */
    constructor(context: Context, fileName: String?, responseTime: Int) : this(context) {
        this.fileName = fileName
        this.responseTime = responseTime
    }

    /**
     * Client implementation
     * @param request
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun execute(request: Request): Response {
        val uri = Uri.parse(request.url)

        if (BuildConfig.DEBUG) {
            Log.d("MOCK SERVER", "fetching uri: " + uri.toString())
        }

        var filename: String?

        if (fileName?.isEmpty() != false) {
            filename = uri.path
            filename = filename!!.substring(filename.lastIndexOf('/') + 1).split(regex = "\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
        } else {
            filename = fileName
        }

        try {
            Thread.sleep(responseTime.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val assetFile = filename!!.toLowerCase() + fileExtension

        if (BuildConfig.DEBUG) {
            Log.d("MOCK SERVER", "open asset file: $assetFile")
        }


        val input = context.assets.open(assetFile)
        val size = input.available()
        val buffer = ByteArray(size)
        input.read(buffer)
        input.close()

        val responseString = String(buffer)
        return Response(request.url, 200, "nothing", listOf(), TypedByteArray("application/json", responseString.toByteArray()))
    }
}
