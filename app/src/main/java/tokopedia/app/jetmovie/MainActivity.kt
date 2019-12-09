package tokopedia.app.jetmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tokopedia.app.abstraction.ui.ViewPagerAdapter
import tokopedia.app.movies.ui.MovieFragment
import tokopedia.app.tvshows.ui.TVShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPagerSetup()
    }

    private fun viewPagerSetup() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MovieFragment(), "Movie")
        adapter.addFragment(TVShowFragment(), "TV")
        viewpagerMain?.adapter = adapter
        tabLayoutMain?.setupWithViewPager(viewpagerMain)
    }

}
