create table category
(
    category_id   bigint       not null
        primary key,
    category_type varchar(255) not null
);



create table products
(
    product_id          bigint         not null
        primary key,
    product_description text           not null,
    product_model       varchar(255)   not null,
    product_number      bigint         not null,
    product_price       numeric(19, 2) not null,
    category_id         bigint         not null,
    foreign key (category_id)
        references category (category_id)
);

insert into category(category_id, category_type)
VALUES (1, 'IPHONE');

insert into products(product_id,
                     product_description,
                     product_model,
                     product_number,
                     product_price,
                     category_id)
VALUES (153, 'Test', '12', 13, 1399.99, 1);



