package com.youmeal.registration.rest.dtos

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.Size

class DishDTO(
    @Size(min = 3, max = 30)
    var name: String?,
    var description: String?,
    @Min(0.1.toLong())
    var price: BigDecimal?
)
