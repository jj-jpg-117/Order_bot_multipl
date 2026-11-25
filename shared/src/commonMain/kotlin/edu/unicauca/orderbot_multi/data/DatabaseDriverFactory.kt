package edu.unicauca.orderbot_multi.data

import app.cash.sqldelight.db.SqlDriver

/** Fábrica del driver SQLite de cada plataforma. */
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
