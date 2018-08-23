DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원정보테이블 */
/**********************************/
CREATE TABLE member(
		id                            		VARCHAR2(20)		 NOT NULL		 PRIMARY KEY,
		pw                            		VARCHAR2(20)		 NOT NULL,
		name                          		VARCHAR2(50)		 NOT NULL,
		gender                        		VARCHAR2(5)		 NOT NULL,
		email                         		VARCHAR2(50)		 NOT NULL,
		phone                         		VARCHAR2(13)		 NOT NULL,
		tel                           		VARCHAR2(13)		 NULL ,
		zip                           		VARCHAR2(5)		 NULL ,
		address                       		VARCHAR2(200)		 NULL ,
		birthday                      		DATE		 NOT NULL,
		joindate                      		DATE		 NOT NULL
		
		CONSTRAINT UK_member_email UNIQUE(email);
		CONSTRAINT UK_member_phone UNIQUE(phone);
);

COMMENT ON TABLE member is '회원정보테이블';
COMMENT ON COLUMN member.id is '회원아이디';
COMMENT ON COLUMN member.pw is '회원비밀번호';
COMMENT ON COLUMN member.name is '회원이름';
COMMENT ON COLUMN member.gender is '회원성별';
COMMENT ON COLUMN member.email is '회원메일주소';
COMMENT ON COLUMN member.phone is '회원연락처1(휴대폰)';
COMMENT ON COLUMN member.tel is '회원연락처2(집전화)';
COMMENT ON COLUMN member.zip is '도로명우편번호';
COMMENT ON COLUMN member.address is '도로명주소';
COMMENT ON COLUMN member.birthday is '생년월일';
COMMENT ON COLUMN member.joindate is '가입일';


