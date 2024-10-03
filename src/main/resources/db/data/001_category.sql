INSERT INTO categories(
                       cat_name,
                       cat_description,
                       created_at,
                       updated_at,
                       created_by,
                       updated_by,
                       deleted
)
VALUES
    ('Front End', 'Front End description', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Back End', 'Back End description', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Framework', 'Framework description', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Algorithm', 'Algorithm description', CURRENT_TIMESTAMP, null, 1, null, FALSE)