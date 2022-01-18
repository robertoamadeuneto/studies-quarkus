package com.youmeal.registration.rest

import com.github.database.rider.cdi.api.DBRider
import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.configuration.Orthography
import com.github.database.rider.core.api.dataset.DataSet
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.apache.http.HttpStatus
import org.approvaltests.Approvals
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(TestsLifecycleManager::class)
@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
class RestaurantResourceTest {

    @Test
    @DataSet("/restaurant-resource/should-list-all.yml")
    fun shouldListAll() {
        val result = given()
            .`when`()
            .headers("Authorization", "Bearer ${TokenGenerator.generate()}")
            .get("/restaurants")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .asString()
        Approvals.verifyJson(result)
    }
}
