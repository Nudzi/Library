CREATE TABLE IF NOT EXISTS public.members
(
    id BIGSERIAL NOT NULL,
    first_name CHARACTER VARYING(100) NOT NULL,
    last_name CHARACTER VARYING(100) NOT NULL,
    email_address CHARACTER VARYING(255) NOT NULL,
    contact_number INT NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT false,
    created_date TIMESTAMP with time zone NOT NULL DEFAULT now(),
    updated_date TIMESTAMP with time zone,

    CONSTRAINT pk_members PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS member_role (
    member_id BIGINT NOT NULL,
    CONSTRAINT "fk_member_role->members" FOREIGN KEY (member_id)
    REFERENCES members (id) ON UPDATE CASCADE ON DELETE CASCADE,
    role VARCHAR(100) NOT NULL,

    CONSTRAINT pk_member_role PRIMARY KEY (member_id, role)
);

CREATE TABLE IF NOT EXISTS books (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    title VARCHAR (100) NOT NULL,
    isbn INT NOT NULL,
    genre VARCHAR (100) NULL,
    total_copies INT NULL,
    available_copies INT NULL,
    publisher VARCHAR (250) NULL,
    publish_year TIMESTAMP NULL,

    CONSTRAINT pk_books PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS transactions (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    member_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date TIMESTAMP with time zone NOT NULL DEFAULT now(),
    due_date TIMESTAMP with time zone NULL,
    return_date TIMESTAMP with time zone NULL,

    CONSTRAINT "fk_transactions->members" FOREIGN KEY (member_id)
    REFERENCES members (id) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT "fk_transactions->books" FOREIGN KEY (book_id)
    REFERENCES books (id) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS fines (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    member_id BIGINT NOT NULL,
    fine_ammount DECIMAL NOT NULL,
    reason VARCHAR (250) NOT NULL,
    payment_status VARCHAR (100) NULL,

    CONSTRAINT "fk_fines->members" FOREIGN KEY (member_id)
    REFERENCES members (id) ON UPDATE CASCADE ON DELETE CASCADE,

    CONSTRAINT pk_fines PRIMARY KEY (id)
);
