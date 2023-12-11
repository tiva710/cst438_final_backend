create table user_table (
	id int primary key auto_increment,  
	username varchar(25) unique, 
	password varchar(100), 
	role varchar(25) 
);