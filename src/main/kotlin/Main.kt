package me.koji

fun userTry() {
    readLine() ?.let { userGuess ->
        val userDigits = userGuess.split(" ")

        if (userGuess.length < 5) {
            println("Hey, did you forget to type a number? Here goes another try!")
            return userTry()
        }

        if (userDigits.any { it.length > 2 }) {
            println("Sorry, you can't input a three digits number!")
        }
    }
}

fun main() {
    println("Welcome to Lottery Guess, you will try to guess the five numbers of the lottery!")
    println("All of the five numbers can have 2 digits. Don't forget that!")
    println("So, what are your guess? Separate the numbers using SPACE.")

    userTry()
}