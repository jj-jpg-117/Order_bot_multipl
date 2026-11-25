package edu.unicauca.orderbot_multi.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import edu.unicauca.orderbot_multi.db.OrderBotDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = OrderBotDatabase.Schema,
        name = "orderbot.db",
    )
}
