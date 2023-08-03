package com.proeffico.einvoiceService.config;

import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyClass implements Serializable {
    private String name;
    private int age;

    // Constructors, getters, and setters

    // Your methods and business logic
}
