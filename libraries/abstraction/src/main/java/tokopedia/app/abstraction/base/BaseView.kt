package tokopedia.app.abstraction.base

interface BaseView {
    fun onMessage(message: String?)
    fun onMessage(stringResId: Int)
    fun hideKeyboard()
}