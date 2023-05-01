CREATE TABLE USERS(
                       DNI VARCHAR(9) PRIMARY KEY,
                       name VARCHAR(255),
                       phone NUMBER,
                       mail VARCHAR(255)
);

CREATE TABLE PRODUCTS(
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(255),
                         description VARCHAR(255),
                         price FLOAT,
                         taxes FLOAT,
                         stock NUMBER
);

CREATE TABLE ORDERS(
                      id VARCHAR(36) PRIMARY KEY,
                      owner VARCHAR(9),
                      products VARCHAR,
                      debt NUMBER,
                      amount NUMBER,
                      date DATE,
                      state ENUM('Outstanding', 'Paid', 'Indicted', 'Sent', 'Delivered'),
                      FOREIGN KEY (owner) REFERENCES USERS(DNI),
                      FOREIGN KEY (products) REFERENCES PRODUCTS(id)
);

CREATE TABLE PAYMENT (
                         amount FLOAT,
                         type ENUM('Card', 'Cash', 'Cheque'),
                         PRIMARY KEY (amount)
);

CREATE TABLE CARD (
                     amount FLOAT,
                     number NUMBER(16),
                     expirationDate DATE,
                     typeCard VARCHAR(255),
                     FOREIGN KEY (amount) REFERENCES PAYMENT(amount),
                     PRIMARY KEY (amount)
);

CREATE TABLE CASH (
                      amount FLOAT,
                      typeCash VARCHAR(255),
                      FOREIGN KEY (amount) REFERENCES PAYMENT(amount),
                      PRIMARY KEY (amount)
);

CREATE TABLE CHEQUE (
                    amount FLOAT,
                    name VARCHAR(50),
                    bank VARCHAR(50),
                    FOREIGN KEY (amount) REFERENCES PAYMENT(amount),
                    PRIMARY KEY (amount)
);