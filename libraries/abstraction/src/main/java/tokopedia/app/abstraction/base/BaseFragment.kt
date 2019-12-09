package tokopedia.app.abstraction.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tokopedia.app.abstraction.util.ext.toast
import tokopedia.app.abstraction.util.view.KeyboardUtils

abstract class BaseFragment: Fragment(), BaseView  {

    /**
     * lifecycle method
     * @method contentView(): @return resLayoutId
     * @method initView()depe
     */
    abstract fun contentView(): Int
    abstract fun initObservable()
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onMessage(message: String?) {
        toast(message)
    }

    override fun onMessage(stringResId: Int) {
        onMessage(getString(stringResId))
    }

    /**
     * hide keyboard layout
     */
    override fun hideKeyboard() {
        return KeyboardUtils().hide(activity!!)
    }

}