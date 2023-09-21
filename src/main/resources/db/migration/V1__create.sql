create table employee (id int8 generated by default as identity, full_name varchar(255), primary key (id));
create table equipment (id int8 generated by default as identity, name varchar(255), employee_id int8, primary key (id));
create table payment (id int8 generated by default as identity, comment varchar(255), date date, sum int8, employee_id int8, primary key (id));
alter table if exists equipment add constraint FKf2ffo63vgd3hwnef2u9h06ii2 foreign key (employee_id) references employee;
alter table if exists payment add constraint FK80uujv6vcwhagikotxoynv7ta foreign key (employee_id) references employee;