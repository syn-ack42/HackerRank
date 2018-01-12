package time_conversion

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val timeStr = sc.nextLine()
    val exp: String = "(\\d\\d):(\\d\\d):(\\d\\d)(AM|PM)"
    val p: Pattern = Pattern.compile(exp)
    val m: Matcher = p.matcher(timeStr)
    m.find()
    val h: Int = m.group(1).toInt()
    val min: Int = m.group(2).toInt()
    val sec: Int = m.group(3).toInt()
    val ampm: String = m.group(4)
    val mil_hours: Int = if ((h == 12) and (ampm == "AM")) 0
                            else if ((h == 12) and (ampm == "PM")) 12
                            else if (ampm == "AM") h
                            else h + 12
    print("%02d:%02d:%02d".format(mil_hours, min, sec))
}