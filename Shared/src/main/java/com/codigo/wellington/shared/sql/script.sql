
/**
 * Author:  welli
 * Created: 11/08/2017
 */
drop table orderT;

create  table orderT(
 id  integer not null,
 salesmanid integer not null,
 productid integer not null,
 quantity integer not null,
primary key (id)
);

drop table salesman;
create  table salesman(
 id  integer not null,
phone varchar(20),
primary key (id)
);

drop table person;
drop table product;

create  table person(
 id  integer not null,
name varchar(100),
primary key (id)
);

create  table product(
 id  integer not null,
name varchar(100),
primary key (id)
);


