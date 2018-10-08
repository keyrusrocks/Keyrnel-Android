package com.keyrus.keyrnel.net

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import javax.net.ssl.HttpsURLConnection

/**
 * Class: RequestSenderUtils
 * Information: Send requests helper with sending data and receiving data management
 * Usage : Create a RequestSenderUtils instance in a AsyncTask and call the sendRequest method with the good parameters
 * All the results are stored in instance variable :
 * - responseCode : code of the HTTP response (200 : OK, 404 : Not Found, ...)
 * - responseContent : the data received in String Type, the conversion must be part of the AsyncTask
 * - occurredException : not null if an exception occurred during the sending
 *
 * Created by Emmanuel Furnon on 21/10/2015.
 */
class RequestSenderUtils {
    var responseCode: Int = 0
        private set
    var responseContent: String? = null
        private set
    var occurredException: Exception? = null
        private set

    /**
     * Send a request to a server and store the results in instance variable (responseCode, responseContent, occurredException)
     * @param method        The http method to use (GET, POST, ...).
     * @param url           The url of the server
     * @param dataToSend    Data to send in the body of the request (ignored if the method is GET => unsupported)
     * @param headersParam  All parameters to put in the header of the request (Structure : List of HashMaps)
     * (Example : [{'header':'header1', 'value':'v1'}, {'header':'Content-Type', 'value':'application/json'}])
     */
    fun sendRequest(method: String?, url: String?, dataToSend: String, headersParam: List<HashMap<String, String>>) {
        if (method == null || url == null) {
            this.responseContent = null
            return
        }

        this.responseContent = ""
        this.occurredException = null
        val urlAddress: URL
        var urlConnection: HttpURLConnection? = null
        try {
            urlAddress = URL(url)

            urlConnection = urlAddress.openConnection() as HttpURLConnection

            urlConnection.requestMethod = method

            for (i in headersParam.indices) {
                val param = headersParam[i]

                if (param.containsKey("header") && param.containsKey("value")) {
                    urlConnection.setRequestProperty(param["header"], param["value"])
                }
            }

            // GET method doesn't support sending data
            if (method != "GET") {
                urlConnection.doOutput = true

                val outputInBytes = dataToSend.toByteArray(charset("UTF-8"))
                val os = urlConnection.outputStream
                os.write(outputInBytes)
                os.close()
            }

            this.responseCode = urlConnection.responseCode

            if (this.responseCode == HttpsURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(urlConnection.inputStream))
                do {
                    val line = br.readLine()
                    this.responseContent += line
                } while (line != null)
            }
        } catch (e: Exception) {
            this.responseContent = null
            this.occurredException = e
        } finally {
            urlConnection?.disconnect()
        }
    }
}
