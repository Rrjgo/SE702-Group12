package com.example.group12.models

import java.util.Date

data class HistoryRecordItem(
    val date: String,
    val pressureStatus: PressureStatus,
    val location: String
)