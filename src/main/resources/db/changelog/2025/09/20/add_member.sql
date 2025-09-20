CREATE TABLE member (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         full_name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(20) NOT NULL
);

CREATE INDEX idx_member_id ON
    member (id)