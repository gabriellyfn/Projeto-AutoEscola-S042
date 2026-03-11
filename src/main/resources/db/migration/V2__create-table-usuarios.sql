CREATE TABLE usuarios
(
    id            BIGINT       NOT NULL auto_increment,
    login         VARCHAR(100) NOT NULL UNIQUE,
    senha         VARCHAR(255) NOT NULL,
    ativo         TINYINT      NOT NULL DEFAULT 1,
    perfil        VARCHAR(15)  NOT NULL DEFAULT "USER",

    PRIMARY KEY (id)
);
