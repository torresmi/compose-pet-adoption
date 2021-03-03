package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R
import com.github.javafaker.Faker
import java.util.*

private val faker = Faker.instance(Random(0))
private const val AMOUNT = 10

private val IMAGES = arrayOf(
    R.drawable.ic_chameleon_one,
    R.drawable.ic_chameleon_two,
    R.drawable.ic_chameleon_three,
    R.drawable.ic_chameleon_four,
    R.drawable.ic_chameleon_five,
    R.drawable.ic_chameleon_six,
    R.drawable.ic_chameleon_seven,
    R.drawable.ic_chameleon_eight,
)

val CHAMELEONS = generateSequence {
    Chameleon(
        id = UUID.randomUUID(),
        name = faker.lordOfTheRings().character(),
        location = faker.lordOfTheRings().location(),
        species = faker.options().option(Species::class.java),
        gender = faker.options().option(Gender::class.java),
        image = R.drawable.ic_chameleon_seven
    )
}
    .take(AMOUNT)
    .withIndex()
    .map { assignImage(it.index, it.value) }
    .associateBy { it.id }

private fun assignImage(index: Int, chameleon: Chameleon): Chameleon {
    val newImage = IMAGES[index % IMAGES.size]
    return chameleon.copy(image = newImage)
}

data class Chameleon(
    val id: UUID,
    val name: String,
    val location: String,
    val species: Species,
    @DrawableRes val image: Int,
    val gender: Gender
)

enum class Species {
    VEILED,
    MELLER,
    PANTHER,
    FOUR_HORNED,
    OUSTALET,
    CARPET,
    SENEGAL,
    JACKSON,
    FISCHER,
    NOSY_BE,
    PYGMY;

    fun prettyPrint(): String {
        val locale = Locale.getDefault()
        return name
            .split("_")
            .joinToString(separator = " ") {
                it.toLowerCase(locale)
                    .capitalize(locale)
            }
    }
}

enum class Gender {
    MALE,
    FEMALE;

    fun prettyPrint(): String {
        val locale = Locale.getDefault()
        return name
            .toLowerCase(locale)
            .capitalize(locale)
    }
}
