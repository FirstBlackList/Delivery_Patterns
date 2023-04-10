package ru.netology.delivery_2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformationAboutUserData {
    private String name;
    private String city;
    private String phone;
}
