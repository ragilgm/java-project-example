CREATE TABLE IF NOT EXISTS `transaction` (
    id bigserial NOT NULL CONSTRAINT transaction_pkey PRIMARY KEY,
    payment_type VARCHAR(20) NOT NULL,
    amount NUMERIC(20, 4),
    created_at TIMESTAMP,
    created_by VARCHAR(50),
    updated_at TIMESTAMP,
    updated_by VARCHAR(50),
    deleted_at TIMESTAMP,
    deleted_by VARCHAR(50)
);