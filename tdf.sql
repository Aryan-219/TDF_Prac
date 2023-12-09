create DATABASE mytdf;

use mytdf


########################## - status start #######################
create table status
(
    status_id int auto_increment primary key,
    name char(15) not null 
);

insert into status (name) 
values 
('Active'),
('Inactive'),
('Open'),
( 'Closed'),
('Blocked');

########################## - status end ###########################



########################## Countries - start ######################

create table countries 
(
    country_id int auto_increment primary key,
    name char(35) not null unique,
    isd_code int not null unique
);

insert into countries 
(name, isd_code) values 
('bharat',91), ('USA',1) , ('China',86) , ('Russia',7);

####################### countries -end #############################



###################### occupations - start #########################
create table occupations 
(
    occupation_id int auto_increment primary key,
    name char(35) not null unique
);

insert into occupations 
(name) values 
('Student'), ('Working Professional'), ('Startup Owner'), ('Freelancer');

###################### occupations end #############################



####################### badges start ###############################

create table badges 
(
    badge_id int auto_increment primary key,
    name char(15) not null unique 
);

insert into badges 
(name) values 
('Gold') , ('Silver') , ('Platinum') ;

##################### badges end ###################################




########################## topics start ###########################
create table topics 
(
    topic_id int auto_increment primary key,
    name char(25) not null,
    all_posts int not null default 0,
    open_posts int not null default 0,
    active_users int not null default 0
);

insert into topics 
(name) values 
('Java Script'), 
('Java SE'), 
('Java EE'), 
('Spring'), 
('SpringBoot'), 
('Micro Services'), 
('Python'), 
('Django'), 
('Go Lang'), 
('Ruby');

alter table topics add column last_post datetime after open_posts; 
########################## topics end ###########################

############### users start ##############
create table users 
(
    user_id int auto_increment primary key,
    name char(45) not null,
    email char(255) not null unique,
    password char(255) not null ,
    pic char(255),
    phone char(10) not null unique,
    interest char(255),
    employer char(60),
    experience int,
    job_profile char(25),
    occupation_id int,
    technologies_used char(100),
    questions_posted int not null default 0,
    responses int not null default 0,
    country_id int not null,
    messages_blocked int not null default 0,
    otp char(6),
    star_rank int not null default 0,
    badge_id int,
    status_id int not null default 2,
    constraint fk_users_occupations foreign key (occupation_id) references occupations (occupation_id),
    constraint fk_users_country foreign key (country_id) references countries (country_id),
    constraint fk_users_badges foreign key (badge_id) references badges (badge_id),
    constraint fk_users_status foreign key (status_id) references status (status_id)
);

################## users end ##################




################## moderators start ##################
create table moderators 
(
    moderator_id int auto_increment primary key,
    topic_id int not null,
    user_id int not null,
    joined_on datetime not null,
    status_id int not null,
    constraint fk_moderators_topics foreign key (topic_id) references topics (topic_id),
    constraint fk_moderators_users foreign key (user_id) references users (user_id),
    constraint fk_moderators_status foreign key (status_id) references status (status_id)
);

################### moderators end ###################




################### posts start ###################
create table posts 
(
    post_id int auto_increment primary key,
    user_id int not null,
    posted_on datetime not null,
    post varchar(2000) not null,
    likes int not null default 0,
    dislikes int not null default 0,
    spams int not null default 0,
    shares int not null default 0,
    post_type bool not null,
    status_id int not null,
    constraint fk_posts_users foreign key (user_id) references users (user_id),
    constraint fk_posts_status foreign key (status_id) references status (status_id)
);

################## posts end ####################



################# questions start ##############

create table questions 
(
    question_id int auto_increment primary key,
    topic_id int not null,
    title char(100) not null,
    post_id int not null,
    replies int not null default 0, 
    constraint fk_questions_topics foreign key (topic_id) references topics (topic_id),
    constraint fk_questions_posts foreign key (post_id) references posts (post_id)
);

################## questions end ####################




############### replies start ####################
create table replies 
(
    reply_id int auto_increment primary key,
    question_id int not null,
    post_id int not null,
    constraint fk_replies_questions foreign key (question_id) references questions (question_id),
    constraint fk_replies_posts foreign key (post_id) references posts (post_id)
);

################## replies end #################




################### wishlist start ###################
create table wishlist 
(
    wishlist_id int auto_increment primary  key,
    post_id int not null,
    user_id int not null, 
    status_id int not null default 1,
    constraint fk_wishlist_posts foreign key (post_id) references posts (post_id),
    constraint fk_wishlist_users foreign key (user_id) references users (user_id),
    constraint fk_wishlist_status foreign key (status_id) references status (status_id)
);

################### wishlist end ###################




################### messages  start ###################

create table messages 
(
    message_id int auto_increment primary key,
    from_user_id int not null,
    to_user_id int not null,
    message varchar(1000) not null,
    posted_on datetime not null,
    status_id int not null,
    constraint fk_messages_users1 foreign key (from_user_id) references users (user_id),
    constraint fk_messages_users2 foreign key (to_user_id) references users (user_id),
    constraint fk_messages_status foreign key (status_id) references status (status_id)
);

################### messages end ###################