package io.dotanuki.coroutines.testutils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectForTesting(scope: CoroutineScope = MainScope()): MutableList<T> {
    val emissions = mutableListOf<T>()
    scope.launch { toList(emissions) }
    return emissions
}