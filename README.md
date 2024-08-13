# shcedule-management
<p>주특기 입문 주차 개인과제입니다.</p>
<b>Goal: 나만의 일정 관리 앱 서버 만들기</b>

## ERD

![schedule-erd](https://github.com/user-attachments/assets/46875cd1-0edb-4b91-ba10-76d4f127eba4)


## API 명세서
https://documenter.getpostman.com/view/26350607/2sA3s6Dp6B#intro

## schedule.sql

```
CREATE SCHEMA schedule;

USE schedule;


CREATE TABLE `schedule` (
                            `id`	bigint	NOT NULL AUTO_INCREMENT,
                            `to_do`	varchar(255)	    NOT NULL,
                            `created_at`	datetime(6)	NOT NULL,
                            `modified_at`	datetime(6)	NOT NULL,
                            `password`	varchar(255)	NOT NULL,
                            `manager_id`	bigint	    NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `manager` (
                           `id`	bigint	NOT NULL AUTO_INCREMENT,
                           `name`	varchar(10)	        NOT NULL,
                           `email`	varchar(255)	    NOT NULL,
                           `created_at`	datetime(6)	    NOT NULL,
                           `modified_at`	datetime(6)	NOT NULL,
                           PRIMARY KEY (`id`)
);

ALTER TABLE `schedule` ADD CONSTRAINT `fk_manager` FOREIGN KEY (`manager_id`) REFERENCES `manager`(`id`)
```