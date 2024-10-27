package store;

import java.time.LocalDateTime;
import java.util.ArrayList;

/*
 * CREATE TABLE Customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    admin BOOLEAN NOT NULL,
    password VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(10) UNIQUE,
    billing_address VARCHAR(200) NOT NULL,
    shipping_address VARCHAR(200) NOT NULL,
    card_id INT DEFAULT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (card_id) REFERENCES Credit_Card(card_id) ON DELETE SET NULL
);
 */

public class Customer {
    private int id;
    private boolean isAdmin;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String shippingAddress;
    private LocalDateTime creationDate;


}
