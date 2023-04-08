drop table like_alcohol;
drop table review;
drop table scrap_alcohol;
drop table alcohol;
drop table comment;
drop table like_post;
drop table preference;
drop table scrap_post;
drop table post;
drop table user;
drop table hibernate_sequence;


CREATE TABLE `certified` (
	`id`	bigint AUTO_INCREMENT,
	`email`	VARCHAR(45)	NOT NULL,
	`token`	VARCHAR(100)	NOT NULL,
	`expirationDate`	TIMESTAMP,
	`expired`	TINYINT(1)	NOT NULL	DEFAULT 1	COMMENT '1:만료, 0:미만료',
  `roles`	VARCHAR(255),
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `user` (
	`id`	bigint AUTO_INCREMENT,
	`email`	VARCHAR(45)	NOT NULL,
	`password`	VARCHAR(80)	NOT NULL,
	`username`	VARCHAR(20)	NOT NULL,
	`status`	TINYINT(1)	NOT NULL	DEFAULT 1	COMMENT '1:사용, 0:탈퇴',
  `roles`	VARCHAR(255),
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `post` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	NOT NULL	COMMENT '회원번호',
	`title`	VARCHAR(50)	NOT NULL	COMMENT '게시글 제목',
	`content`	TEXT	NOT NULL	COMMENT '내용',
	`image`	VARCHAR(100)	NULL,
	`hit`	bigint	COMMENT '조회수',
	`alcohol_name`	VARCHAR(20)	NULL COMMENT '술이름',
	`alcohol_type`	VARCHAR(10)	NULL COMMENT '주종',
	`flavor`	VARCHAR(20)	NULL COMMENT '맛',
	`volume`	VARCHAR(5) NULL COMMENT '도수',
	`price`	INT NULL	COMMENT '가격',
	`body`	INT	NULL	COMMENT '바디',
	`sugar`	INT	NULL	COMMENT '당도',
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`status`	TINYINT(1)	NOT NULL	DEFAULT 1	COMMENT '1:사용, 0:삭제',
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE `comment` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	INT	NOT NULL COMMENT '회원번호',
	`post_id`	INT	NOT NULL COMMENT '게시글번호',
	`content`	TEXT	NULL,
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `like_post` (
	`id`	bigint AUTO_INCREMENT,
	`post_id`	bigint	COMMENT '게시글번호',
	`user_id`	bigint	COMMENT '회원번호',
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `scrap_post` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	COMMENT '회원번호',
	`post_id`	bigint	COMMENT '게시글번호',
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`name`	VARCHAR(50)	NOT NULL,
	`type`	VARCHAR(10)	NOT NULL	COMMENT '주종',
	`category`	VARCHAR(10)	NOT NULL	COMMENT '종류',
	`volume`	VARCHAR(5) NOT NULL	COMMENT '도수',
	`flavor`	VARCHAR(100) NULL	COMMENT '맛',
	`size`	VARCHAR(10)	NULL	COMMENT '용량',
	`price`	INT	NULL	COMMENT '가격',
	`food`	VARCHAR(100)	NULL	COMMENT '시즈닝',
	`nation`	VARCHAR(20)	NULL	COMMENT '원산지',
	`body`	INT	NULL	COMMENT '바디',
	`sugar`	INT	NULL	COMMENT '당도',
	`tannins`	INT	NULL	COMMENT '타닌',
	`acidity`	INT	NULL	COMMENT '산도',
	`image`	VARCHAR(100)	NULL,
	`hit`	bigint	COMMENT '조회수',
	`info` VARCHAR(200) NULL COMMENT '상세설명',
	PRIMARY KEY (id)
);

CREATE TABLE `like_alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`alcohol_id`	bigint	COMMENT '술번호',
	`user_id`	bigint	COMMENT '회원번호',
	FOREIGN KEY (alcohol_id) REFERENCES `alcohol` (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `scrap_alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	NULL	COMMENT '회원번호',
	`alcohol_id`	bigint	NOT NULL	COMMENT '술번호',
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	FOREIGN KEY (alcohol_id) REFERENCES `alcohol` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `review` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint COMMENT '회원번호',
	`alcohol_id`	bigint COMMENT '술번호',
	`review`	VARCHAR(100)	NOT NULL COMMENT '한줄리뷰',
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `preference` (
	`user_id`	bigint NOT NULL COMMENT '회원번호',
	`type`	VARCHAR(10)	NOT NULL COMMENT '주종',
	`volume`	VARCHAR(5)	NULL COMMENT '도수',
	`sugar`	INT	NULL COMMENT '당도',
	`flavor`	VARCHAR(100)	NULL COMMENT '맛',
	`price`	VARCHAR(20)	NULL COMMENT '가격',
	`recommendation` VARCHAR(200)	NULL COMMENT '추천술아이디',
	FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE `certified` (
	`id`	bigint AUTO_INCREMENT,
	`email`	VARCHAR(45)	NOT NULL,
	`token`	VARCHAR(100)	NOT NULL,
	`expiration_date`	TIMESTAMP,
	`expired`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '1:만료, 0:미만료',
	`verified`	TINYINT(1)	NOT NULL	DEFAULT 0	COMMENT '1:인증, 0:미인증',
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `user` (
	`id`	bigint AUTO_INCREMENT,
	`email`	VARCHAR(45)	NOT NULL,
	`password`	VARCHAR(80)	NOT NULL,
	`username`	VARCHAR(20)	NOT NULL,
	`status`	TINYINT(1)	NOT NULL	DEFAULT 1	COMMENT '1:사용, 0:탈퇴',
  `roles`	VARCHAR(255),
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `post` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	NOT NULL	COMMENT '회원번호',
	`title`	VARCHAR(50)	NOT NULL	COMMENT '게시글 제목',
	`content`	TEXT	NOT NULL	COMMENT '내용',
	`image`	VARCHAR(100)	NULL,
	`hit`	bigint	COMMENT '조회수',
	`alcohol_nm`	VARCHAR(20)	NULL COMMENT '술이름',
	`alcohol_type`	VARCHAR(10)	NULL COMMENT '주종',
	`flavor`	VARCHAR(20)	NULL COMMENT '맛',
	`volume`	VARCHAR(5) NULL COMMENT '도수',
	`price`	INT NULL	COMMENT '가격',
	`body`	INT	NULL	COMMENT '바디',
	`sugar`	INT	NULL	COMMENT '당도',
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`status`	TINYINT(1)	NOT NULL	DEFAULT 1	COMMENT '1:사용, 0:삭제',
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE `comment` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	INT	NOT NULL COMMENT '회원번호',
	`post_id`	INT	NOT NULL COMMENT '게시글번호',
	`content`	TEXT	NULL,
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `like_post` (
	`id`	bigint AUTO_INCREMENT,
	`post_id`	bigint	COMMENT '게시글번호',
	`user_id`	bigint	COMMENT '회원번호',
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `scrap_post` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	COMMENT '회원번호',
	`post_id`	bigint	COMMENT '게시글번호',
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	FOREIGN KEY (post_id) REFERENCES `post` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`name`	VARCHAR(50)	NOT NULL,
	`type`	VARCHAR(10)	NOT NULL	COMMENT '주종',
	`category`	VARCHAR(10)	NOT NULL	COMMENT '종류',
	`volume`	VARCHAR(5) NOT NULL	COMMENT '도수',
	`flavor`	VARCHAR(100) NULL	COMMENT '맛',
	`size`	VARCHAR(10)	NULL	COMMENT '용량',
	`price`	INT	NULL	COMMENT '가격',
	`food`	VARCHAR(100)	NULL	COMMENT '시즈닝',
	`nation`	VARCHAR(20)	NULL	COMMENT '원산지',
	`body`	INT	NULL	COMMENT '바디',
	`sugar`	INT	NULL	COMMENT '당도',
	`tannins`	INT	NULL	COMMENT '타닌',
	`acidity`	INT	NULL	COMMENT '산도',
	`image`	VARCHAR(100)	NULL,
	`hit`	bigint	COMMENT '조회수',
	`info` VARCHAR(200) NULL COMMENT '상세설명',
	PRIMARY KEY (id)
);

CREATE TABLE `like_alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`alcohol_id`	bigint	COMMENT '술번호',
	`user_id`	bigint	COMMENT '회원번호',
	FOREIGN KEY (alcohol_id) REFERENCES `alcohol` (id),
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `scrap_alcohol` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint	NULL	COMMENT '회원번호',
	`alcohol_id`	bigint	NOT NULL	COMMENT '술번호',
	FOREIGN KEY (user_id) REFERENCES `user` (id),
	FOREIGN KEY (alcohol_id) REFERENCES `alcohol` (id),
	PRIMARY KEY (id)
);

CREATE TABLE `review` (
	`id`	bigint AUTO_INCREMENT,
	`user_id`	bigint COMMENT '회원번호',
	`alcohol_id`	bigint COMMENT '술번호',
	`review`	VARCHAR(100)	NOT NULL COMMENT '한줄리뷰',
	`created_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`modified_date`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE `preference` (
	`user_id`	bigint NOT NULL COMMENT '회원번호',
	`type`	VARCHAR(10)	NOT NULL COMMENT '주종',
	`volume`	VARCHAR(5)	NULL COMMENT '도수',
	`sugar`	INT	NULL COMMENT '당도',
	`flavor`	VARCHAR(100)	NULL COMMENT '맛',
	`price`	VARCHAR(20)	NULL COMMENT '가격',
	`recommendation` VARCHAR(200)	NULL COMMENT '추천술아이디',
	FOREIGN KEY (user_id) REFERENCES `user` (id)
);