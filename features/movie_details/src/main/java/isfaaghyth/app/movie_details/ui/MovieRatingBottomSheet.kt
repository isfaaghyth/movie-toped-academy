package isfaaghyth.app.movie_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import isfaaghyth.app.movie_details.R
import kotlinx.android.synthetic.main.layout_rating_bottom_sheet.*

/**
 * @author by jessica on 2019-12-02
 */

class MovieRatingBottomSheet(val listener: BottomSheetListener): BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_rating_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bs_rating_button.setOnClickListener {
            val ratingStar = bs_rating_bar.rating
            if (ratingStar > 0) listener.onButtonClicked(ratingStar)
            else Toast.makeText(context, "Please input rating to proceed", Toast.LENGTH_SHORT).show()
        }
    }

    interface BottomSheetListener{
        fun onButtonClicked(stars: Float)
    }
}
