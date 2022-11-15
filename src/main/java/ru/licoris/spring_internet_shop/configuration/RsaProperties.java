package ru.licoris.spring_internet_shop.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@EnableConfigurationProperties(RsaProperties.class)
@ConfigurationProperties(prefix = "rsa")
public record RsaProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}
