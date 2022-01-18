package com.youmeal.registration.rest.dtos

import javax.validation.constraints.NotNull

class LocationDTO(
    @NotNull
    var latitude: Double? = null,
    @NotNull
    var longitude: Double? = null
)
