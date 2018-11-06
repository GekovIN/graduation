package ru.gekov.model;

import java.math.BigDecimal;
import java.util.Date;

public class Meal extends AbstractBaseEntity {
    private Date date;
    private String description;
    private BigDecimal amount;
    private Restaurant restaurant;
}
