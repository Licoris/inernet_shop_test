package com.example.spring_internet_shop.listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile({"devH2Db", "devPostgresql"})
@Component
public class StartupListener {
    @EventListener({ApplicationReadyEvent.class})
    public void applicationReadyEvent() {
        String url = "http://localhost:8080/show-products";

        System.setProperty("webdriver.edge.driver", "src/main/resources/webdrivers/msedgedriver.exe");
        WebDriver edgeDriver = new EdgeDriver();
        edgeDriver.get(url);
    }
}
