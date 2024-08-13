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