insert into course (id,name,updated_time, created_time) values (10001,'ZJpaIn50Steps',sysdate(),sysdate());
insert into course (id,name,updated_time, created_time) values (10002,'Spring_Boot_Course',sysdate(),sysdate());
insert into course (id,name,updated_time, created_time) values (10003,'Spring_Validation_Course',sysdate(),sysdate());

insert into passport (id,number) values (40001 , '21345');
insert into passport (id,number) values (40002 , '54321');
insert into passport (id,number) values ( 40003, '45678');
insert into passport (id,number) values (40004 , '54321');
insert into passport (id,number) values ( 40005, '32546');

insert into student (id,name,passport_id, is_deleted) values (20001 , 'Tomek',40001,false);
insert into student (id,name,passport_id, is_deleted) values (20002 , 'Marlena',40002,false);
insert into student (id,name,passport_id, is_deleted) values ( 20003, 'Iga',40003,false);
insert into student (id,name,passport_id, is_deleted) values (20004 , 'Michal',40004,false);
insert into student (id,name,passport_id, is_deleted) values ( 20005, 'Kamil',40005,false);



insert into review (id,rate,description,course_id,student_id) values ( 50001, 5, 'Great Course',10001,20001);
insert into review (id,rate,description,course_id,student_id) values ( 50002, 4, 'Good Course',10001,20003);
insert into review (id,rate,description,course_id,student_id) values ( 50003, 3, 'Average Course,',10002,20004);

insert into STUDENT_COURSE (COURSE_ID,STUDENT_ID) values (10001	,20001);
insert into STUDENT_COURSE (COURSE_ID,STUDENT_ID) values ( 10001,20002);
insert into STUDENT_COURSE (COURSE_ID,STUDENT_ID) values ( 10002,20002);
insert into STUDENT_COURSE (COURSE_ID,STUDENT_ID) values ( 10002,20003);
insert into STUDENT_COURSE (COURSE_ID,STUDENT_ID) values (10001 ,20005);
