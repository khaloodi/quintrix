fun main() {
    var str = "Hi!"
    println(replace(str))

}

fun replace(s: String): String {
    val vowels = "aeiouAEIOU"
    var new_string = ""

    for (letter in s) {
        if (vowels.contains(letter)) {
            new_string = s.replace(letter, '!')
        }
    }
    return new_string
}

/** Solutions:
 *
 &  fun replace(s: String): String {
        val re = Regex("[aeiouAEIOU]")
        return re.replace(s, "!")
    }

 *
 *  fun replace(s: String): String {
        return "[aeuoi]".toRegex(RegexOption.IGNORE_CASE).replace(s, "!")
    }

 *
 *  fun replace(s: String) = s.map { if ("aeiouAEIOU".contains(it)) "!" else it }.joinToString("")

 *
 * fun replace(s: String) = s.map { if (it in "aeiouAEIOU") '!' else it}.joinToString("")

 *
 * fun replace(s: String): String = Regex("[aeiouAEIOU]").replace(s, "!")

 *
 * fun replace(s: String) = s.replace(Regex("[aeiouAEIOU]"), "!")

 *
 * fun replace(s: String): String = s.replace(Regex("a|e|i|o|u", RegexOption.IGNORE_CASE), "!")

 */