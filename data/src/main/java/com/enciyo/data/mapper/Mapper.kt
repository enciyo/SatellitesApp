package com.enciyo.data.mapper

interface Mapper<in I, out O> {
    fun mapTo(input: I): O
}