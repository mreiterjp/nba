package com.example.nba.domain.entity

import com.example.nba.data.model.Team
import kotlin.random.Random

data class TeamEntity(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String,
) {
    companion object {
        fun generateFakeTeamEntity(id: Int): TeamEntity {
            return TeamEntity(
                id = Random.nextInt(1, 30),
                abbreviation = "abbr $id",
                city = "City$id",
                conference = listOf("East", "West").random(),
                division = "Division$id",
                fullName = "Team Full Name: $id",
                name = "name $id"
            )
        }
    }
}

fun Team.toEntity(): TeamEntity {
    return TeamEntity(
        id = this.id,
        abbreviation = this.abbreviation,
        city = this.city,
        conference = this.conference,
        division = this.division,
        fullName = this.fullName,
        name = this.name
    )
}

