package com.enciyo.domain.model

import java.lang.Exception


data class SerializationException(
    override val message: String?,
) : Exception(message)