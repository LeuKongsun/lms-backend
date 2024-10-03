INSERT INTO schedules(
    sch_description,
    sch_start_time,
    sch_end_time,
    sch_start_date,
    sch_end_date,
    sch_total_time,
    cou_id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('MONDAY', '18:00:00', '20:00:00', '2024-08-01', '2024-10-01', 40, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('THURSDAY', '18:00:00', '20:00:00', '2024-08-01', '2024-10-01', 40, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('MONDAY', '18:00:00', '20:00:00', '2024-08-01', '2024-10-01', 40, 3, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('TUESDAY', '10:00:00', '12:00:00', '2024-08-01', '2024-10-01', 40, 3, CURRENT_TIMESTAMP, null, 1, null, FALSE)
