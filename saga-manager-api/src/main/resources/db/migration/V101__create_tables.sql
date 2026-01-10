CREATE TABLE IF NOT EXISTS statuses
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions
(
    id BIGSERIAL PRIMARY KEY,
    tx_id VARCHAR(36) NOT NULL,
    execution_date TIMESTAMP NOT NULL,
    status_id BIGSERIAL NOT NULL,

    foreign key (status_id) references statuses (id)
    on delete restrict
    on update cascade
);