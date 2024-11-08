use schedule;

CREATE TABLE schedule
(
    id INTEGER AUTO_INCREMENT PRIMARY KEY COMMENT '아이디',
    name VARCHAR(20) NOT NULL COMMENT '작성자명',
    title VARCHAR(100) NOT NULL COMMENT '제목',
    body TEXT NOT NULL COMMENT '내용',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
);

-- 일정 생성 query 작성
INSERT INTO schedule(name, title, body) VALUES ('쓰니1','데이트 일정','강원도가서 바다보고 밥먹기');
INSERT INTO schedule(name, title, body) VALUES ('쓰니2','등산 일정','설악산 등산');
INSERT INTO schedule(name, title, body) VALUES ('쓰니3','운동 일정','춘천에서 자전거 타기');

-- 전체 일정 조회 query 작성
SELECT * FROM schedule;

-- 선택 일정 조회하는 query 작성
SELECT * FROM schedule WHERE id = 2;

-- 선택 일정 수정하는 query 작성
UPDATE schedule SET body = '한라산 등산' WHERE id = 2;

-- 선택 일정 삭제하는 query 작성
DELETE FROM schedule WHERE id = 2;
