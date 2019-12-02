package isfaaghyth.app.abstraction.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author by jessica on 2019-12-02
 */

class DateUtil {
    companion object {

        val DEFAULT_LOCALE = Locale("in", "ID")
        val DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss zzz"

        fun stringToDate(format: String, input: String): Date {
            val fromFormat = SimpleDateFormat(format, DEFAULT_LOCALE)
            try {
                return fromFormat.parse(input)
            } catch (e: ParseException) {
                e.printStackTrace()
                throw RuntimeException("Date doesnt valid ($input) with format $format")
            }
        }

        fun now(): Date = Date()
    }

}