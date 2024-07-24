package com.igrowker.donatello.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Donatello",
                version = "1.0",
                description = "Este proyecto tiene como objetivo crear una plataforma SaaS que responda a las necesidades de los pequeños comercios de comida a nivel local, que buscan opciones para administrar sus negocios de forma accesible, intuitiva y a bajo costo. Tecnologias: React Js, Java, Spring, PostgreSQL, Swagger, Spring security, JWT, ects. Deploy front en netlify, backend java, backend python y base de datos en render.\n" +
                        "\n" +
                        "Repositorio Frontend=> https://github.com/igrowker/i002-donatello-front\n" +
                        "\n" +
                        "Repositorio Backend python=> https://github.com/igrowker/i002-donatello-back-python\n" +
                        "\n" +
                        "Repositorio Backend java=> https://github.com/igrowker/i002-donatello-back-java\n" +
                        "\n" +
                        "Documentacion => https://i002-donatello-back-java-latest-z9hn.onrender.com/docs/swagger-ui/index.html\n" +
                        "\n" +
                        "Deploy => https://dona-tello.netlify.app/\n"
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        description = "Add your token below",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
