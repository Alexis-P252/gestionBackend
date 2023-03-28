package com.PreLab.ApiAlmacen;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@OpenAPIDefinition(info = @Info(title = "Almacen API", version = "1.0", description = "Documentacion Api Almacen"))
@SecurityScheme(name = OpenApiConfig.SECURITY_SCHEME_NAME, scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER )
public class OpenApiConfig {

    public static final String SECURITY_SCHEME_NAME = "Authorization";

}