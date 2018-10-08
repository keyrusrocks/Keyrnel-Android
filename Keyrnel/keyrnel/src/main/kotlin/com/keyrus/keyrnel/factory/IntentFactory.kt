package com.keyrus.keyrnel.factory

import android.content.Intent
import android.net.Uri

/**
 * Created by Paul Mougin on 08/10/2018.
 */
object IntentFactory {

    /**
     * Create an intent to call a phone number
     */
    fun phoneCall(phoneNumber: String): Intent {
        return Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
    }

    /**
     * Create an intent to send mail to a list of addresses with a subject
     */
    fun composeEmail(addresses: Array<String>, subject: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        return intent
    }

    /**
     * Create an intent to launch a web page
     */
    fun launchWebPage(url: String): Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}