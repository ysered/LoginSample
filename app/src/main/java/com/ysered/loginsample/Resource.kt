package com.ysered.loginsample

/**
 * Defines states and payload to be exchanged between view models and views.
 */
sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Success<T>(val payload: T) : Resource<T>()
    class Error<T> : Resource<T>()
}
