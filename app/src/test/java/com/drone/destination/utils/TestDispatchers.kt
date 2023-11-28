package com.drone.destination.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatchers {
    private val testDispatcher = StandardTestDispatcher()
    val main: CoroutineDispatcher
        get() = testDispatcher
    val io: CoroutineDispatcher
        get() = testDispatcher
    val default: CoroutineDispatcher
        get() = testDispatcher
}