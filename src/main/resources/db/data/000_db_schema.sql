create table categories
(
    cat_id  bigserial   primary KEY,
    cat_name varchar(30) NOT NULL,
    cat_description varchar(30) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table teachers
(
    tea_id  bigserial   primary KEY,
    tea_code varchar(7) NULL,
    tea_firstname varchar(10),
    tea_lastname varchar(20),
    tea_gender varchar(20),
    tea_email varchar(20) NULL,
    tea_phone varchar(20) NULL,
    tea_address varchar(50) NULL,
    tea_salary DECIMAL(10, 2) NULL,
    tea_hire_date DATE,
    tea_stop_work BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table students
(
    stu_id  bigserial   primary KEY,
    stu_firstname varchar(10),
    stu_lastname varchar(20),
    stu_gender varchar(20),
    stu_phone   varchar(11) NULL,
    stu_type   varchar(5) NULL,
    stu_position   varchar(20) NULL,
    stu_from   varchar(20) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table courses
(
    cou_id  bigserial   primary KEY,
    cou_name varchar(20),
    cou_description varchar(255),
    cou_price decimal(10, 2),
    cou_discount decimal(10, 2),
    cou_image  varchar(255) NULL,
    cat_id  bigint
    constraint fk_course_category
    references categories,
    tea_id  bigint
    constraint fk_course_teacher
    references teachers,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table schedules
(
    sch_id  bigserial   primary KEY,
    sch_description  varchar(255) NOT NULL,
    sch_start_time TIME,
    sch_end_time TIME,
    sch_start_date DATE,
    sch_end_date DATE,
    sch_total_time DECIMAL(10, 2),
    cou_id  bigint
    constraint fk_schedule_course
    references courses,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);
