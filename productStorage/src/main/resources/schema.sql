create table if not exists product
(
    id       INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(50) NOT NULL,
    price    DECIMAL(12, 2) CHECK ( price > 0 ),
    amount   INT CHECK ( amount > -1 ),
    reserved INT CHECK ( reserved <= product.amount )
);