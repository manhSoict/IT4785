package com.example.myapplication

data class EmailItem(
    val initial: String,
    val sender: String,
    val subject: String,
    val preview: String,
    val time: String,
    val isStarred: Boolean = false,
    val backgroundColor: Int
)
