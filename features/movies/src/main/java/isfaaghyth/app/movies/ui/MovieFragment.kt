package isfaaghyth.app.movies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.Movie
import isfaaghyth.app.movies.R
import isfaaghyth.app.movies.di.DaggerMovieComponent
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject
import isfaaghyth.app.abstraction.util.session.GuestSessionResponse
import isfaaghyth.app.abstraction.util.DateUtil
import isfaaghyth.app.abstraction.util.session.SessionUtil


class MovieFragment : Fragment() {

    private fun contentView(): Int = R.layout.fragment_movie

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieViewModel

    private var movieData = arrayListOf<Movie>()
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(movieData)
    }

    lateinit var sessionUtil: SessionUtil

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(contentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        lstMovies.adapter = adapter

        getGuestSessionId()

        initObserver()
    }

    private fun getGuestSessionId() {
        activity?.let {
            sessionUtil = SessionUtil(it)
            val guestSessionExpiredTime = sessionUtil.getSessionExpiredTime()
            if (guestSessionExpiredTime == null || guestSessionExpiredTime.before(DateUtil.now())) {
                viewModel.getGuestSessionId()
            }
        }
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is LoaderState.ShowLoading -> toast("loading")
                is LoaderState.HideLoading -> toast("complete")
            }
        })

        viewModel.result.observe(this, Observer { movies ->
            movieData.addAll(movies)
            adapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer { error ->
            toast(error)
        })

        viewModel.guestSessionResult.observe(this, Observer {
            if (it.success) saveGuestSessionId(it)
        })
    }

    private fun initInjector() {
        DaggerMovieComponent.builder()
            .movieModule(MovieModule())
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    private fun saveGuestSessionId(guestSessionResponse: GuestSessionResponse) {
        sessionUtil.saveGuestSessionId(guestSessionResponse)
    }

}