package com.keyrus.keyrnel.net.gson.exclusion

/**
 * Annotation : GsonExclude
 * Information : Permit to remove a field in the Gson (de)serialization process. This annotation
 * must be used on model Class with the [AnnotationExclusionStrategy] in Gson
 * configuration. The keyword "transient" can't be used with Realm model, it's why this
 * annotation was developed.
 *
 * Created by efurnon on 01/03/2017.
 */
@kotlin.annotation.Retention
@Target(AnnotationTarget.FIELD)
annotation class GsonExclude
