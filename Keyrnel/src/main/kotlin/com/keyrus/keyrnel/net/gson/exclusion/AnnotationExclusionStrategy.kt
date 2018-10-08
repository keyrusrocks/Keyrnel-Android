package com.keyrus.keyrnel.net.gson.exclusion

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

/**
 * Class : AnnotationExclusionStrategy
 * Information : Exclude all fields from Gson (de)serialization process which have the
 * [GsonExclude] annotation.
 *
 * Usage : new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
 *
 * Created by efurnon on 01/03/2017.
 */
class AnnotationExclusionStrategy : ExclusionStrategy {
    /**
     * Check if the [GsonExclude] annotation exist on a field
     * @param f attributes that describe the current field
     * @return true if the field must be ignored, or false otherwise
     */
    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(GsonExclude::class.java) != null
    }

    /**
     * Method not implemented (used to exclude the (de)serialization of some classes)
     * @param clazz the current class
     * @return false
     */
    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}