set search_path = "Eurder";

CREATE TABLE postal_code
(
    id                 UUID PRIMARY KEY NOT NULL,
    postal_code_number varchar(8)       NOT NULL,
    city               varchar(128)     NOT NULL
);

CREATE TABLE address
(
    id                UUID PRIMARY KEY NOT NULL,
    street_name       varchar(256)     NOT NULL,
    street_number     varchar(8)       NOT NULL,
    fk_postal_code_id UUID             NOT NULL,
    CONSTRAINT fk_postal_code_id foreign key (fk_postal_code_id) references postal_code (ID)
);

CREATE TABLE customer
(
    id            UUID PRIMARY KEY    NOT NULL,
    first_name    VARCHAR(256)        NOT NULL,
    last_name     VARCHAR(256)        NOT NULL,
    email         VARCHAR(320) UNIQUE NOT NULL,
    fk_address_id UUID                NOT NULL,
    phone_number  VARCHAR(15)         NOT NULL,
    CONSTRAINT fk_address_id foreign key (fk_address_id) references address (ID)
);

CREATE TABLE item
(
    id           UUID PRIMARY KEY NOT NULL,
    name         VARCHAR(128)     NOT NULL,
    description  VARCHAR(256)     NOT NULL,
    price        DOUBLE PRECISION NOT NULL,
    stock_amount INTEGER          NOT NULL
);

CREATE TABLE "order"
(
    id             UUID PRIMARY KEY NOT NULL,
    total_price    DOUBLE PRECISION NOT NULL,
    fk_customer_id UUID             NOT NULL,
    CONSTRAINT fk_customer_id FOREIGN KEY (fk_customer_id) REFERENCES customer (id)
);

CREATE TABLE item_group
(
    id            UUID PRIMARY KEY         NOT NULL,
    fk_item_id    UUID                     NOT NULL,
    fk_order_id   UUID                     NOT NULL,
    amount        INTEGER                  NOT NULL,
    group_price   DOUBLE PRECISION         NOT NULL,
    shipping_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_item_id FOREIGN KEY (fk_item_id) REFERENCES item (id),
    CONSTRAINT fk_order_id FOREIGN KEY (fk_order_id) REFERENCES "order" (id)
);

