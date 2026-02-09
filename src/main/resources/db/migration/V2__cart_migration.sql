CREATE TABLE cart (
    id          BINARY(16)   NOT NULL DEFAULT (UUID_TO_BIN(UUID())),
    user_id     BIGINT       NULL,               -- ← most important addition
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NULL ON UPDATE CURRENT_TIMESTAMP,
    -- expires_at  DATETIME     NULL,            -- optional - very useful for guest carts
    CONSTRAINT pk_cart PRIMARY KEY (id)
);


CREATE TABLE cart_item (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    cart_id     BINARY(16)   NOT NULL,
    product_id  BIGINT       NOT NULL,
    quantity    INT          NOT NULL DEFAULT 1,

    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME     NULL ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_cart_item         PRIMARY KEY (id),
    CONSTRAINT fk_cart_item_cart    FOREIGN KEY (cart_id)    REFERENCES cart(id)    ON DELETE CASCADE,
    CONSTRAINT fk_cart_item_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE RESTRICT
    -- ON DELETE RESTRICT / SET NULL / CASCADE — choose depending on your business rules
);