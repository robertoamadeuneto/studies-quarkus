package com.youmeal.registration.rest

import io.smallrye.jwt.build.Jwt
import org.eclipse.microprofile.jwt.Claims

object TokenGenerator {

    fun generate(): String {
        return Jwt.issuer("https://github.com/robertoamadeuneto")
            .upn("robertoamadeuneto@gmail.com")
            .groups(mutableSetOf("proprietary"))
            .claim(Claims.birthdate.name, "2022-02-07")
            .sign();
    }
}
