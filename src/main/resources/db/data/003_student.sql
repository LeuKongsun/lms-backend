INSERT INTO students(
    stu_firstname,
    stu_lastname,
    stu_gender,
    stu_phone,
    stu_type,
    stu_position,
    stu_from,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('student-1', 'stu', 'MALE', '123456','STUDY', 'student', 'RUPP', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-2', 'stu', 'FEMALE', '123457', 'STUDY', 'student', 'RUPP', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-3', 'stu', 'FEMALE', '123458', 'WORK', 'IT', 'EDC', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-4', 'stu', 'MALE', '123459', 'WORK', 'Backend', 'EDC', CURRENT_TIMESTAMP, null, 1, null, FALSE)
