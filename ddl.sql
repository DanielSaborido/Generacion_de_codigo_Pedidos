CREATE TABLE CLIENT(
                       DNI VARCHAR(9) PRIMARY KEY,
                       name VARCHAR(255),
                       phone INT,
                       mail VARCHAR(255)
);

CREATE TABLE ORDERS(
                      id VARCHAR(36) PRIMARY KEY,
                      owner VARCHAR(9),
                      orderPrice INT,
                      orderSize INT,
                      date DATE,
                      state VARCHAR(255)
);

CREATE TABLE PRODUCTS(
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(255),
                         description VARCHAR(255),
                         price INT,
                         taxes INT,
                         stock INT
);