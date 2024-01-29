package com.example.nba.domain.entity

import com.example.nba.data.model.Player
import com.example.nba.domain.entity.PlayerEntity.Companion.getAvatarUrl
import timber.log.Timber
import kotlin.random.Random

data class PlayerEntity(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val heightFeet: Int,
    val heightInches: Int,
    val weightPounds: Int,
    val avatarUrl: String?,
    val teamEntity: TeamEntity? = null,
) {
    companion object {
        fun getAvatarUrl(id: Int): String {
            return "https://picsum.photos/id/$id/60.jpg"
        }

        fun generateFakePlayerEntity(playerId: Int? = null): PlayerEntity {
            val id = playerId ?: Random.nextInt(1, 1000)
            val playerEntity = PlayerEntity(
                id = id,
                firstName = "Name $id",
                lastName = "Surname $id",
                position = listOf("F", "G", "C").random(),
                heightFeet = Random.nextInt(5, 8),
                heightInches = Random.nextInt(0, 12),
                weightPounds = Random.nextInt(150, 250),
                teamEntity = TeamEntity.generateFakeTeamEntity(id),
                avatarUrl = getAvatarUrl(id),
            )
            Timber.d("FakePlayer: $playerEntity")
            return playerEntity
        }
    }
}

fun Player.toEntity(): PlayerEntity {
    return PlayerEntity(
        id = this.id,
        firstName = this.firstName?:"",
        lastName = this.lastName?:"",
        position = this.position?:"",
        heightFeet = this.heightFeet?:0,
        heightInches = this.heightInches?:0,
        weightPounds = this.weightPounds?:0,
        avatarUrl = getAvatarUrl(this.id),
        teamEntity = team?.toEntity()
    )
}