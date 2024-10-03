INSERT INTO teachers(
    tea_code,
    tea_firstname,
    tea_lastname,
    tea_gender,
    tea_salary,
    tea_hire_date,
    tea_stop_work,
    tea_email,
    tea_phone,
    tea_address,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('KGA0001', 'Tola', 'Nu', 'MALE', 100.00, '2024-06-01', false, 'Tola@gmail.com', '', 'address', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('KGA0002', 'Seyha', 'Nu', 'FEMALE', 100.00, '2024-06-01', false, 'Seyha@gmail.com', '', 'address', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('KGA0003', 'Kanha', 'Nu', 'FEMALE', 100.00, '2024-06-01', false, 'Kanha@gmail.com', '', 'address', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('KGA0004', 'Somnang', 'Nu', 'MALE', 100.00, '2024-06-01', false, 'Somnang@gmail.com', '', 'address', CURRENT_TIMESTAMP, null, 1, null, FALSE)
