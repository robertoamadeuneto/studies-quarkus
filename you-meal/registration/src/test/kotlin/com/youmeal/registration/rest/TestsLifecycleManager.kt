package com.youmeal.registration.rest

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer

class TestsLifecycleManager : QuarkusTestResourceLifecycleManager {

    private val postgresSqlContainer: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>("postgres:14.1")

    override fun start(): MutableMap<String, String> {
        postgresSqlContainer.start()

        return mutableMapOf(
            "quarkus.datasource.jdbc.url" to postgresSqlContainer.jdbcUrl,
            "quarkus.datasource.username" to postgresSqlContainer.username,
            "quarkus.datasource.password" to postgresSqlContainer.password
        )
    }

    override fun stop() {
        if (postgresSqlContainer.isRunning) {
            postgresSqlContainer.stop()
        }
    }
}
