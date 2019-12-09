package tokopedia.app.abstraction.util.thread

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}