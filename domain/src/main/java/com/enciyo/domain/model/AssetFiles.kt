package com.enciyo.domain.model


enum class AssetFiles(val fileName: String) {
    LIST("satellite_list.json"),
    DETAIL("satellite_detail.json"),
    POSITION("positions.json")
}