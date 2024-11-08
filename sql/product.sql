
CREATE TABLE IF NOT EXISTS t_accounts (
    acc_id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY (INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
    acc_number varchar(255) NOT NULL,
    acc_type varchar(255) NOT NULL,
    client_id int4 NOT NULL,
    acc_initial_balance numeric(38, 2) NOT NULL,
    acc_status int4 NOT NULL,
    CONSTRAINT t_accounts_acc_type_check CHECK ((acc_type::text = ANY (ARRAY['SAVINGS', 'CHECKING']::text[]))),
    CONSTRAINT t_accounts_pkey PRIMARY KEY (acc_id),
    CONSTRAINT ukjobgs5fnb5b20spiammsqmkrw UNIQUE (acc_number)
);


CREATE TABLE IF NOT EXISTS t_movements (
    mov_id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY (INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1 NO CYCLE),
    mov_balance numeric(38, 2) NOT NULL,
    mov_date timestamp(6) NOT NULL,
    mov_type varchar(255) NOT NULL,
    mov_value numeric(38, 2) NOT NULL,
    mov_acc_id int4 NOT NULL,
    CONSTRAINT t_movements_mov_type_check CHECK ((mov_type::text = ANY (ARRAY['DEPOSIT', 'WITHDRAWAL']::text[]))),
    CONSTRAINT t_movements_pkey PRIMARY KEY (mov_id),
    CONSTRAINT fk20ouh6mxje5o6rlv3v21m11kc FOREIGN KEY (mov_acc_id) REFERENCES t_accounts(acc_id)
);
