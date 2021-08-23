create table market_order
(
    id          bigserial not null
        constraint market_order_pkey
            primary key,
    buyer_email varchar(255),
    date        timestamp,
    number      integer
);

alter table market_order
    owner to postgres;

create table product
(
    id      bigserial not null
        constraint product_pkey
            primary key,
    article varchar(255),
    name    varchar(255),
    price   double precision,
    state   varchar(255)
);

alter table product
    owner to postgres;

create table order_product
(
    market_order_id bigint not null
        constraint fkrhpsgm0bfhx0ke8exnbwvycqv
            references product,
    product_id      bigint not null
        constraint fkmfyddqsaavtg62pbfgs2kuuun
            references market_order
);

alter table order_product
    owner to postgres;