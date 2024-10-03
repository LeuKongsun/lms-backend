INSERT INTO courses(
    cou_name,
    cou_description,
    cou_price,
    cou_discount,
    cou_image,
    cat_id,
    tea_id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('NextJs', 'NextJs description', 50.00, 10, 'null', 1, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Agular', 'Agular description', 100.00, 20, 'null', 1, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Spring Boot', 'Spring description', 12.00, 0,'null', 3, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Solving Problem', 'Problem description', 25.00, 0, 'null', 4, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE)
