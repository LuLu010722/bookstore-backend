drop table if exists order_items;
drop table if exists orders;
drop table if exists books;
drop table if exists user_auths;
drop table if exists users;

create table users
(
    `id`             bigint      not null auto_increment,
    `nickname`       varchar(32) not null,
    `username`       varchar(32) not null,
    `password`       varchar(32) not null,
    `type`           tinyint     not null,
    `email`          varchar(64),
    `phone`          char(11),
    `address`        varchar(256),
    `avatar_img_url` varchar(256),
    primary key (id)
);

create table books
(
    `id`           bigint       not null auto_increment,
    `title`        varchar(256) not null,
    `author`       varchar(256),
    `publish_time` datetime,
    `language`     varchar(32),
    `description`  varchar(1024),
    `price`        numeric(10, 2),
    `img_url`      varchar(256),
    `remaining`    int,
    `isbn`         varchar(20),
    primary key (id)
);

create table orders
(
    `id`         bigint      not null auto_increment,
    `user_id`    bigint,
    `status`     varchar(16) not null default 'FINISHED',
    `time_stamp` datetime    not null default current_timestamp,
    primary key (id),
    foreign key (user_id) references users (id)
);

create table order_items
(
    `id`       bigint         not null auto_increment,
    `order_id` bigint         not null,
    `book_id`  bigint         not null,
    `amount`   int            not null,
    `price`    numeric(10, 2) not null,
    primary key (id),
    foreign key (order_id) references orders (id),
    foreign key (book_id) references books (id)
);

insert into users
values (1, 'admin', 'admin', 'admin', 1, '384668412@qq.com', '', '', null);
insert into users
values (2, 'user', 'user', 'user', 0, '384668412@qq.com', '', '', null);

insert into books
values (1, '平凡的世界', '路遥', current_timestamp, 'zh-ch',
        '《平凡的世界》是中国作家路遥创作的一部全景式地表现中国当代城乡社会生活的百万字长篇小说。全书共三部。1986年12月首次出版。该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心，刻画了当时社会各阶层众多普通人的形象；劳动与爱情、挫折与追求、痛苦与欢乐、日常生活与巨大社会冲突纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。',
        32.10, 'https://s2.loli.net/2022/05/14/17JtIMsgaU3kFb5.jpg', 10, '9787530221396');
insert into books
values (2, '三体（全集）', '刘慈欣', current_timestamp, 'zh-ch',
        '《三体》是刘慈欣创作的系列长篇科幻小说，由《三体》《三体2：黑暗森林》《三体3：死神永生》组成，第一部于2006年5月起在《科幻世界》杂志上连载，第二部于2008年5月首次出版，第三部则于2010年11月出版。作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。其第一部经过刘宇昆翻译后获得了第73届雨果奖最佳长篇小说奖',
        88.30, 'https://s2.loli.net/2022/05/14/sa8LXySf4cFrd35.jpg', 10, '23579654');
insert into books
values (3, '了不起的盖茨比', '菲茨杰拉德', current_timestamp, 'zh-ch',
        '《了不起的盖茨比》是美国作家弗朗西斯·斯科特·基·菲茨杰拉德创作的一部以20世纪20年代的纽约市及长岛为背景的中篇小说，出版于1925年。',
        34.70, 'https://s2.loli.net/2022/05/14/DsY8Ka5XHiOlkEj.jpg', 10, '9787538756371');
insert into books
values (4, '安娜·卡列尼娜', '列夫·托尔斯泰', current_timestamp, 'zh-ch',
        '《安娜·卡列尼娜》是俄国作家列夫·托尔斯泰创作的长篇小说，也是其代表作品。作品讲述了贵族妇女安娜追求爱情幸福，却在卡列宁的虚伪、渥伦斯基的冷漠和自私面前碰得头破血流，最终落得卧轨自杀、陈尸车站的下场。庄园主列文反对土地私有制，抵制资本主义制度，同情贫苦农民，却又无法摆脱贵族习气而陷入无法解脱的矛盾之中。矛盾的时期、矛盾的制度、矛盾的人物、矛盾的心理，使全书在矛盾的漩涡中颠簸。这部小说是新旧交替时期紧张惶恐的俄国社会的写照。',
        103.10, 'https://s2.loli.net/2022/05/15/WtQIkCvnHsayJEw.jpg', 10, '9787544740883');
insert into books
values (5, '人间失格', '太宰治', current_timestamp, 'zh-ch',
        '《人间失格》（又名《丧失为人的资格》）日本小说家太宰治创作的中篇小说，发表于1948年，是一部半自传体的小说。',
        10.10, 'https://s2.loli.net/2022/05/15/TtqfwHzM8ucSv7y.jpg', 10, '9787506380263');
