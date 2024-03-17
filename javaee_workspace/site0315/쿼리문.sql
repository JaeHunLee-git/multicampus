INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'상의');
INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'하의');
INSERT INTO topcategory(TOPCATEGORY_IDX, TOPNAME) values(seq_topcategory.nextval,'액세서리');

SELECT * FROM TOPCATEGORY ;
--상의 1
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '가디건',1);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '니트',1);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '점퍼',1);

--하의 2
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '청바지',2);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '스커트',2);

--액세서리 3
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '반지', 3);
INSERT INTO SUBCATEGORY(SUBCATEGORY_IDX, SUBNAME, TOPCATEGORY_IDX) values(seq_subcategory.nextval, '목걸이',3);

SELECT * FROM TOPCATEGORY t 
WHERE topcategory_idx=3;

select * from subcategory where topcategory_idx=3
   


insert into product(product_idx, product_name, price, brand,filename,subcategory_idx) 
values(seq_product.nextval, '내가디건', 30000, '폴햄','1710405148484.jpg', 1);

select * from product where product_idx=3;

DELETE product;



--공지 게시판 DDL 
CREATE TABLE  notice(
	notice_idx  NUMBER PRIMARY KEY 
	, title varchar2(100) 
	, writer varchar2(20)
	, content clob
	, regdate  DATE DEFAULT sysdate
	, hit NUMBER DEFAULT 0
);

CREATE SEQUENCE seq_notice 
INCREMENT BY 1
START WITH 1;

SELECT * FROM notice WHERE notice_idx=4;







