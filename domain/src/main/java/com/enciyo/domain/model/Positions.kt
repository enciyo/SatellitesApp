package com.enciyo.domain.model

data class PositionsResult(
    val list: List<Positions>,
)

data class Positions(
    val id: String,
    val positions: List<Position>,
)

data class Position(
    val posX: Long,
    val posY: Long,
)