package isfaaghyth.app.movie_details.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airbnb.deeplinkdispatch.DeepLink
import isfaaghyth.app.abstraction.base.BaseActivity
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.MOVIE_DETAIL
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.PARAM_MOVIE_ID
import isfaaghyth.app.abstraction.util.AppLink.MovieDetail.PARAM_TYPE
import isfaaghyth.app.abstraction.util.ext.hide
import isfaaghyth.app.abstraction.util.ext.load
import isfaaghyth.app.abstraction.util.ext.show
import isfaaghyth.app.abstraction.util.ext.toast
import isfaaghyth.app.abstraction.util.state.LoaderState
import isfaaghyth.app.data.entity.MovieDetail
import isfaaghyth.app.data.mapper.MovieDetailMapper
import isfaaghyth.app.movie_details.R
import isfaaghyth.app.movie_details.di.DaggerMovieDetailComponent
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject



@DeepLink(MOVIE_DETAIL)
class MovieDetailActivity: BaseActivity(), MovieRatingBottomSheet.BottomSheetListener {

    override fun contentView(): Int = R.layout.activity_movie_detail

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailViewModel

    private lateinit var bottomSheet: MovieRatingBottomSheet
    private var movieId: String = ""

    override fun initView() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MovieDetailViewModel::class.java)

        initParam()
        initObservable()

        rate_movie_button.setOnClickListener { showRatingBottomSheet() }
    }

    private fun initParam() {
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras?: Bundle()
            movieId = parameters.getString(PARAM_MOVIE_ID)?: ""

            when(parameters.getString(PARAM_TYPE)) {
                TYPE_MOVIE -> viewModel.getMovieDetail(movieId)
                TYPE_TV -> viewModel.getTVShowDetail(movieId)
                else -> finish()
            }
        }
    }

    private fun initObservable() {
        viewModel.state.observe(this, Observer {
            when (it) {
                is LoaderState.ShowLoading -> {
                    rootView.hide()
                    progressBar.show()
                }
                is LoaderState.HideLoading -> {
                    rootView.show()
                    progressBar.hide()
                }
            }
        })

        viewModel.movieDetail.observe(this, Observer { movie ->
            showDetail(MovieDetailMapper.mapFromMovie(movie))
        })

        viewModel.tvDetail.observe(this, Observer { tv ->
            showDetail(MovieDetailMapper.mapFromTVShow(tv))
        })

        viewModel.error.observe(this, Observer { toast(it) })

        viewModel.tvPostRatingStatus.observe(this, Observer { response ->
            showRatingSuccess(response.statusMessage)
        })
    }

    private fun showDetail(detail: MovieDetail) {
        imgBanner.load(banner)
        imgPoster.load(detail.posterPath)
        txtMovieName.text = title
        txtContent.text = detail.overview
        txtRating.text = detail.voteCount.toString()
        txtVote.text = detail.voteAverage.toString()
    }

    private fun showRatingBottomSheet() {
        if (!::bottomSheet.isInitialized || !bottomSheet.isVisible) {
            bottomSheet = MovieRatingBottomSheet(this)
            bottomSheet.show(supportFragmentManager, "exampleBottomSheet")
        }
    }

    private fun showRatingSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onButtonClicked(stars: Float) {
        if (bottomSheet.isVisible) bottomSheet.dismiss()
        viewModel.rateMovie(movieId, stars.toInt() * 2)
    }

    override fun initInjector() {
        DaggerMovieDetailComponent.builder()
            .movieDetailModule(MovieDetailModule())
            .build()
            .inject(this)
    }

    companion object {
        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
    }

}