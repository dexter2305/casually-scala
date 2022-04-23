object SubstringSearch extends App {

  def naiveSubstringSearch(text: String, pattern: String): Int =
    text.indices.find { textIndex =>
      pattern.indices.forall { patternIndex =>
        textIndex + patternIndex <= text.length() &&
        text(textIndex + patternIndex) == pattern(patternIndex)
      }
    }.getOrElse(-1)


    val text = "India is my country. I am proud of it."
    val pattern = "countres"
    println(s"naive substring search (fp): ${naiveSubstringSearch(text, pattern)}")

}
