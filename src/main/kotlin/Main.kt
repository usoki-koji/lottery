package me.koji

import java.time.Instant
import kotlin.random.Random

// ~~text~~
val wrongNumber = "\u001B[9m"

fun random2DigitsNumber(random: Random, prohibited: Collection<Int>) : Int {
    val randomInt = random.nextInt(0, 99)

    return if (!prohibited.contains(randomInt))
        randomInt
    else
        random2DigitsNumber(random, prohibited)
}

fun userTry() {
    val random = Random(Instant.now().toEpochMilli());

    val lotteryNumbers = linkedSetOf<Int>()

    repeat(5) { lotteryNumbers.add(random2DigitsNumber(random, lotteryNumbers)) }

    //Debug
    println(lotteryNumbers)

    readLine() ?.let { userGuess ->
        val userDigits = userGuess.split(" ")

        if (userGuess.length < 5) {
            println("Hey, did you forget to type a number? Here goes another try!")
            return userTry()
        }

        if (userDigits.any { it.length > 2 }) {
            println("Sorry, you can't input a three digits number. Here, try again.")

            return userTry()
        }

        val intzedDigits = userDigits.map { it.toInt() }

        val correctNumbers = MutableList(5) { false }

        for (i in 0..4) {
            correctNumbers[i] = (intzedDigits[i] == lotteryNumbers.elementAt(i))
        }

        if (correctNumbers.all { !it }) {
            println("Sorry you missed all the numbers.")
            println("Want try again? (Press enter)")

            readLine()

            println("Well, what your guess?")

            userTry()
        } else {
            println("You guessed ${correctNumbers.count { it }}!")

            val stringBuilder = StringBuilder()

            for (i in 0..4) {
                val stringedInt = (intzedDigits[i].toString())
                stringBuilder.append(if (correctNumbers[i]) stringedInt else (wrongNumber + stringedInt + "\u001B[0m") /* Reset */)
            }
        }
    }
}

fun main() {
    println("Welcome to Lottery Guess, you will try to guess the five numbers of the lottery!")
    println("All of the five numbers can have 2 digits. Don't forget that!")
    println("So, what are your guess? Separate the numbers using SPACE.")

    userTry()
}