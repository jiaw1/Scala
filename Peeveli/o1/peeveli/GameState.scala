package o1.peeveli

import GameState.Unrevealed


/** Each instance of class `GameState` represents a single state within the Peeveli variant of
  * Hangman: What does the (partially visible) target word look like to the guesser? How many
  * incorrect guesses can the guesser still make? Which guesses have already been made? Moreover,
  * our dishonest hangman needs an additional piece of information: Which words are still credible
  * solutions given the earlier guesses?
  *
  * Chapter 9.3 of the ebook has a detailed discussion of the internal logic of the Peeveli game.
  *
  * While a player plays a game of Peeveli, the game will move from one state to another. Even so,
  * each `GameState` object is immutable. Each successive state is represented by a new `GameState`
  * object, which is generated by calling the current state's `guessLetter` method.
  *
  * @param missesAllowed      the number of incorrect guesses that the guesser can still make before
  *                           losing the game. A negative number means that the game is over.
  * @param previousGuesses    a string that contains all the previously guessed characters in order
  * @param visibleWord        the version of the target word that is visible to the guesser. In a
  *                           state that represents the beginning of the game, this will consist
  *                           of unrevealed characters only (e.g., `"_____"`; see [[GameState$ Unrevealed]]).
  *                           In later states, more and more characters will be visible (e.g., `"C___O"`).
  * @param possibleSolutions  all the words in the game's vocabulary that match the `visibleWord`
  *                           parameter and are therefore plausible correct solutions */
class GameState(val missesAllowed: Int, val previousGuesses: String, val visibleWord: String, val possibleSolutions: Vector[String])  {


  /** Creates a new `GameState` that represents the initial state of a new game of Peeveli.
    * All the letters of the target word are unrevealed.
    *
    * Note to students: This is an additional constructor for the class (see optional materials
    * in Chapter 4.1). You don???t need to use it.
    *
    * @param missesAllowed  the number of incorrect guesses the guesser is allowed to make
    * @param length         the number of characters in the target word that the guesser will look for
    * @param vocabulary     a collection of known words; all words of exactly `length` characters in
    *                       the vocabulary are potential target words */
  def this(missesAllowed: Int, length: Int, vocabulary: Vector[String]) = {
    this(missesAllowed, "", Unrevealed.toString * length, vocabulary.map( _.toUpperCase )) // This means: pass these parameters to the "default constructor" defined in the class header.
  }


  /** Returns the length of the target word. */
  def wordLength = this.visibleWord.length


  /** Returns the number of all known words that are (still) possible solutions to this
    * game of Peeveli, given the guesses that have already been made. */
  def numberOfSolutions = this.possibleSolutions.size


  /** Returns `true` if the player has missed with more guesses than allowed and has therefore
    * lost the game; returns `false` otherwise. */
  def isLost = this.missesAllowed < 0


  /** Returns `true` if the guesser has won the game, that is, if they haven't missed too many
    * times and all the letters in the target word are visible. Returns `false` otherwise. */
  def isWon = !this.isLost && !this.visibleWord.contains(Unrevealed)


  /** Returns a version of the currently visible target word so that additional characters are
    * revealed as indicated by the given pattern. For example, if `visibleWord` is `"C___O"`
    * and the pattern is `"__LL_"`, returns `"C_LLO"`. This method assumes that it receives
    * a parameter of equal length as the target word. */
  private def reveal(patternToReveal: String) = {
    def revealIfInPattern(index: Int) =
      if (patternToReveal(index) == Unrevealed) this.visibleWord(index) else patternToReveal(index)
    this.visibleWord.indices.map(revealIfInPattern).mkString
  }



  /** Returns a new `GameState` that follows this current one given that the guesser guesses a
    * particular letter. The rationale behind moving from one state to another is described in
    * Chapter 9.3 of the ebook.
    *
    * The next `GameState` will certainly have one more letter in [[previousGuesses]] than the
    * present one. In addition, it may have more visible letters in the target word, fewer misses
    * allowed, and/or fewer potential solutions remaining.
    *
    * The player will always spend a missed solution attempt if the guess did not reveal any new
    * letters. This happens even if the player had already guessed the same letter before.
    *
    * @param guess  a guessed letter; this can be in either case but is always interpreted as an upper-case character
    * @return the state of the game after the newest guess */
  def guessLetter(guess: Char) = {
    def wordGroup(word: String)  = word.map( l => if (l == guess.toUpper) l else Unrevealed ) // E.g., the word "CELLO" belongs in the group "__LL_", given a guess of 'L'.
    val wordsByGroup             = this.possibleSolutions.groupBy(wordGroup)
    val (chosenGroup, wordsLeft) = wordsByGroup.maxBy( _._2.size )
    val newVisibleWord           = this.reveal(chosenGroup)
    val guessesSpent             = if (newVisibleWord == this.visibleWord) 1 else 0
    new GameState(this.missesAllowed - guessesSpent, this.previousGuesses + guess.toUpper, newVisibleWord, wordsLeft)
  }


  // This implementation also covers the optional part: if the word groups are of equal size, it chooses the less revealing group.
  private def guessLetter_improved(guess: Char) = {
    def wordGroup(word: String)  = word.map( l => if (l == guess.toUpper) l else Unrevealed )
    def unrevealedCount(group: String) = this.reveal(group).count( _ == Unrevealed )
    val wordsByGroup             = this.possibleSolutions.groupBy(wordGroup)
    val (chosenGroup, wordsLeft) = wordsByGroup.maxBy { case (group, wordsInGroup) => (wordsInGroup.size, unrevealedCount(group)) }
    val newVisibleWord           = this.reveal(chosenGroup)
    val guessesSpent             = if (newVisibleWord == this.visibleWord) 1 else 0
    new GameState(this.missesAllowed - guessesSpent, this.previousGuesses + guess.toUpper, newVisibleWord, wordsLeft)
  }


  /** Returns a string description of the game state. */
  override def toString =
    this.visibleWord + ", " +
      "missed guesses allowed: " + this.missesAllowed + ", " +
      "guesses made: " + (if (this.previousGuesses.isEmpty) "none" else this.previousGuesses) + ", " +
      "solutions left: " + this.numberOfSolutions

}


/** This companion object just provides a single constant.
  * @see class [[GameState]] */
object GameState {

  /** the character that is used by Peeveli to signify unrevealed letters */
  val Unrevealed = '_'

}
