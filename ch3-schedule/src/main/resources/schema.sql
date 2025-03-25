create table user
(
    id          int auto_increment comment 'PK'
        primary key,
    author      varchar(255) null comment '글쓴이',
    email       varchar(255) null comment '이메일',
    create_date datetime     null comment '생성날짜',
    update_date datetime     null comment '수정날짜'
);

create table schedule
(
    id          int auto_increment comment 'PK'
        primary key,
    todo        varchar(255) null comment '할일',
    create_date datetime     null comment '생성날짜',
    update_date datetime     null comment '수정날짜',
    password    varchar(255) null comment '비밀번호',
    user_id     int          not null comment 'user테이블 id',
    constraint fk_schedule_user
        foreign key (user_id) references user (id)
            on delete cascade
);

