
insert into LEADER (id, name, position, email)
values(1, 'John Johnson', 'Head of IT at Janisons', 'John.Johnson@Janisons.com');

insert into LEADER (id, name, position, email)
values(2, 'Bart Bartson', 'Head of security at Janisons', 'Bart.Bartson@Janisons.com');

insert into LEADER (id, name, position, email)
values(3, 'Jimmie Jimmieson', 'CEO of Janisons', 'Jimmie.Jimmieson@Janisons.com');

insert into PROJECT (ID, PROJECT_NAME, CITY, INFO, INTERNAL, LENGTH_IN_DAYS, leader_id)
values(1, 'Modernising the building', 'Mechelen', 'We modernise our building to bring us up to date with the standards of the future', true, 20, 1);

insert into PROJECT (ID, PROJECT_NAME, CITY, INFO, INTERNAL, LENGTH_IN_DAYS, leader_id)
values(2, 'Improving work efficiency', 'Heist-op-den-berg', 'Improving the work efficiency for a client in Heist', false, 60, 2);

insert into PROJECT (ID, PROJECT_NAME, CITY, INFO, INTERNAL, LENGTH_IN_DAYS, leader_id)
values(3, 'Removing excess data', 'Mechelen', 'We remove excess data from our servers to make our support faster and more responsive', true, 10, 2);

insert into PROJECT (ID, PROJECT_NAME, CITY, INFO, INTERNAL, LENGTH_IN_DAYS, LEADER_ID)
values(4, 'Making an app', 'Mechelen', 'Making an app to reach out to our clients and make it easier to contact us for ideas', true, 90,3);


insert into MEETING (ID, NAME, BIO, DATE)
values(1,'Pre build', 'Laying the groundwork for some internal projects', '2021-05-20');

insert into MEETING (ID, NAME, BIO, DATE)
values(2, 'Data management', 'Decide what to do with our data, what is important and what we can clean up', '2021-05-25');

insert into MEETING (ID, NAME, BIO, DATE)
values(3, 'Future projects', 'What are our future projects going to be and what should be add', '2021-06-05');


insert into PROJECT_MEETINGS (PROJECTS_ID, MEETINGS_ID) values(1, 1);
insert into PROJECT_MEETINGS (PROJECTS_ID, MEETINGS_ID) values(3, 1);
insert into PROJECT_MEETINGS (PROJECTS_ID, MEETINGS_ID) values(4, 1);
insert into PROJECT_MEETINGS (PROJECTS_ID, MEETINGS_ID) values(3, 2);
