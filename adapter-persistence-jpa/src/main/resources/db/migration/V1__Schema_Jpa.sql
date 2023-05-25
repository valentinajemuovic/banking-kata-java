

CREATE TABLE bank_account (
                              id BIGINT PRIMARY KEY,
                              account_number VARCHAR(255) UNIQUE NOT NULL,
                              national_identity_number VARCHAR(255) NOT NULL,
                              first_name VARCHAR(255) NOT NULL,
                              last_name VARCHAR(255) NOT NULL,
                              opening_date DATE NOT NULL,
                              balance INT NOT NULL
);