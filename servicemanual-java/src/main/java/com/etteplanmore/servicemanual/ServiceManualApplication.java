package com.etteplanmore.servicemanual;

import java.util.Arrays;
import java.util.List;

import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceRepository;
import com.etteplanmore.servicemanual.factorydevice.FactoryDevice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceManualApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ServiceManualApplication.class, args);
    }

    

}