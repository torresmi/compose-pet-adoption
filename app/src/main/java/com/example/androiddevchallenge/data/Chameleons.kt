package com.example.androiddevchallenge.data

import com.github.javafaker.Faker
import java.util.*

private val faker = Faker.instance(Random(0))
private const val AMOUNT = 10

val CHAMELEONS = generateSequence {
    Chameleon(
        id = UUID.randomUUID(),
        name = faker.lordOfTheRings().character()
    )
}
    .take(AMOUNT)
    .associateBy { it.id }

data class Chameleon(
    val id: UUID,
    val name: String
)
