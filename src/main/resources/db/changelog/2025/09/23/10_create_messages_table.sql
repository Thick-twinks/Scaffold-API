CREATE TABLE messages (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    from_member_id UUID NOT NULL,
    to_member_id UUID,
    object_uuid UUID NOT NULL,
    content TEXT NOT NULL,
    document_id UUID,
    is_notification BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_message_to_member FOREIGN KEY (to_member_id) REFERENCES member(id),
    CONSTRAINT fk_message_from_member FOREIGN KEY (from_member_id) REFERENCES member(id),
    CONSTRAINT fk_message_document FOREIGN KEY (document_id) REFERENCES documents(id)

);