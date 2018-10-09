package com.keyrus.keyrnel.extensions

import java.text.Normalizer
import java.util.regex.Pattern

/**
 * Created by Paul Mougin on 08/10/2018.
 */

private const val EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
private val EMAIL_ADDRESS_PATTERN = Pattern.compile(EMAIL_REGEX)!!

fun String.isValidEmail(): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

fun String.removeAccent(): String {
     return Normalizer.normalize(this, Normalizer.Form.NFD).replace("\\p{M}".toRegex(), "")
}