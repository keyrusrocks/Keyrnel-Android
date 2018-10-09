package com.keyrus.keyrnel.extensions

import org.junit.Test

/**
 * Created by Paul Mougin on 09/10/2018.
 */
class StringKtTest {

    /**
     * According to RFC 2822, there is a link to valid or invalid email addresses
     * https://blogs.msdn.microsoft.com/testing123/2009/02/06/email-address-test-cases/
     */
    @Test
    fun testIsValidEmail() {
        // Invalid Emails
        val invalidEmails = listOf(
                "plainaddress",
                "#@%^%#\$@#\$@#.com",
                "@domain.com",
                "Joe Smith <email@domain.com>",
                "email.domain.com",
                "email@domain@domain.com",
                ".email@domain.com",
                "email.@domain.com",
                "email..email@domain.com",
                "あいうえお@domain.com",
                "email@domain.com (Joe Smith)",
                "email@domain",
//                "email@-domain.com",
//                "email@domain.web",
                "email@111.222.333.44444",
                "email@domain..com")
        invalidEmails.forEach {
            assert(!it.isValidEmail()) { "$it is a valid email address" }
        }

        // Valid Emails
        val validEmails = listOf(
                "email@domain.com",
                "firstname.lastname@domain.com",
                "email@subdomain.domain.com",
                "firstname+lastname@domain.com",
//                "email@123.123.123.123",
//                "email@[123.123.123.123]",
//                "\"email\"@domain.com",
                "1234567890@domain.com",
                "email@domain-one.com",
                "_______@domain.com",
                "email@domain.name",
                "email@domain.co.jp",
                "firstname-lastname@domain.com")
        validEmails.forEach {
            assert(it.isValidEmail()) { "$it is not a valid email address" }
        }
    }

    @Test
    fun testRemoveAccent() {
        // Empty
        assert("" == "".removeAccent())
        // Accent
        assert("orcpzsiayd" == "orčpžsíáýd".removeAccent())
    }
}