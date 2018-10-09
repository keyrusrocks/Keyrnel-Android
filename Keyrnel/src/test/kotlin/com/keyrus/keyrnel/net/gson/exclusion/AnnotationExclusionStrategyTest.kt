package com.keyrus.keyrnel.net.gson.exclusion

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import org.junit.Test

/**
 * Created by Paul Mougin on 09/10/2018.
 */
class AnnotationExclusionStrategyTest {
    /**
     * Class : TestModel
     * Information : Class used for the test
     */
    private inner class TestModel(internal var id: Int, @field:SerializedName("n") internal var name: String, @field:GsonExclude internal var description: String, @field:GsonExclude internal var email: String)

    /**
     * Test Gson Fields Exclusion
     */
    @Test
    fun testExcludeGson() {
        val gson = GsonBuilder()
                .setExclusionStrategies(AnnotationExclusionStrategy())
                .create()

        val model = TestModel(1, "Name", "Desc", "email@a.fr")

        val json = gson.toJson(model)

        assert("{\"id\":1,\"n\":\"Name\"}" == json)

        val result = gson.fromJson("{\"id\":19,\"n\":\"Good\",\"description\":\"Pretty small but good enough\",\"email\":\"a.a@a.a\"}", TestModel::class.java)

        assert(19L == result.id.toLong())
        assert("Good" == result.name)
        assert("Pretty small but good enough" != result.description)
        assert("a.a@a.a" != result.email)
    }
}