package tokopedia.app.data.mapper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author by jessica on 2019-12-02
 */

object DateMapper {

    private val DEFAULT_LOCALE = Locale("in", "ID")
    const val DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss zzz"

    fun stringToDate(format: String, input: String): Date {
        val fromFormat = SimpleDateFormat(format, DEFAULT_LOCALE)
        try {
            return fromFormat.parse(input)
        } catch (e: ParseException) {
            e.printStackTrace()
            throw RuntimeException("Date doesn't valid ($input) with format $format")
        }
    }

    fun now(): Date = Date()

}