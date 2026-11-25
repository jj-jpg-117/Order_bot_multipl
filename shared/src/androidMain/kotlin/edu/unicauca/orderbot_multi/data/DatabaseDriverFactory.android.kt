package edu.unicauca.orderbot_multi.data

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.db.SqlDriver
import edu.unicauca.orderbot_multi.db.OrderBotDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        schema = OrderBotDatabase.Schema,
        context = context,
        name = "orderbot.db",
    )
}
