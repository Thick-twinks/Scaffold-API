CREATE TABLE messages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    from_member_id UUID NOT NULL REFERENCES members(id),
    to_member_id UUID REFERENCES members(id),
    object_uuid UUID NOT NULL,
    content TEXT NOT NULL,
    document_id UUID REFERENCES documents(id),
    is_notification BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);