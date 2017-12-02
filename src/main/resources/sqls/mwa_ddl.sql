alter table payment_details drop foreign key FKegvoehy2k1f5wqnbgh7a2m8k0
alter table payment_details drop foreign key FKegvoehy2k1f5wqnbgh7a2m8k0 [42102-195]
drop table if exists apartment_reg
drop table if exists commercial_reg
drop table if exists fund_details
drop table if exists hibernate_sequences
drop table if exists member_reg
drop table if exists payment_details
create table APARTMENT_REG (id bigint not null, is_active bit, email_id varchar(255), mobile_no varchar(255), no_of_families integer, owner_first_name varchar(255), owner_last_name varchar(255), house_no varchar(255), aprtment_name varchar(255), presedent_first_name varchar(255), presedent_last_name varchar(255), presedent_mobile_no varchar(255), secretery_first_name varchar(255), secretery_last_name varchar(255), secretery_mobile_no varchar(255), primary key (id))
create table COMMERCIAL_REG (id bigint not null, is_active bit, email_id varchar(255), mobile_no varchar(255), no_of_families integer, owner_first_name varchar(255), owner_last_name varchar(255), house_no varchar(255), business_name varchar(255), type_of_business varchar(255), primary key (id))
create table FUND_DETAILS (id bigint not null auto_increment, is_active bit, amount double precision, bank_details varchar(255), description varchar(255), expire_date datetime, is_expired bit, fund_name varchar(255), fund_type varchar(255), start_date datetime, terms_and_conditaions varchar(255), primary key (id))
create table HIBERNATE_SEQUENCES (sequence_name varchar(255) not null, sequence_next_hi_value bigint, primary key (sequence_name))
create table MEMBER_REG (id bigint not null, is_active bit, email_id varchar(255), mobile_no varchar(255), no_of_families integer, owner_first_name varchar(255), owner_last_name varchar(255), house_no varchar(255), primary key (id))
create table PAYMENT_DETAILS (id bigint not null auto_increment, collected_by varchar(255), note varchar(255), paid_amount double precision not null, paid_by varchar(255), paid_date datetime, fundvo_id bigint, member_id bigint, primary key (id))
alter table PAYMENT_DETAILS add constraint FK_FUND_ID foreign key (fundvo_id) references fund_details (id)
alter table PAYMENT_DETAILS add constraint FK_MEMBER_ID foreign key (member_id) references MEMBER_REG (id)