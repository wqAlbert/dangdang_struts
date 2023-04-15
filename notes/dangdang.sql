/*
说明:d_user没有数据,d_order没有数据;d_product的上架时间(add_time)相同
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.1.46-community : Database - dangdang
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`dangdang` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `dangdang`;

/*Table structure for table `d_book` */

DROP TABLE IF EXISTS `d_book`;

CREATE TABLE `d_book` (
  `id` int(12) NOT NULL,
  `author` varchar(200) NOT NULL,
  `publishing` varchar(200) NOT NULL,
  `publish_time` bigint(20) NOT NULL,
  `word_number` varchar(15) DEFAULT NULL,
  `which_edtion` varchar(15) DEFAULT NULL,
  `total_page` varchar(15) DEFAULT NULL,
  `print_time` int(20) DEFAULT NULL,
  `print_number` varchar(15) DEFAULT NULL,
  `isbn` varchar(25) DEFAULT NULL,
  `author_summary` text NOT NULL,
  `catalogue` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_book` */

insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (1,'斯台芬·茨威格','上海译文出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (2,'丹尼尔·笛福','人民文学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','鲁滨逊（鲁滨孙、鲁宾逊）（Robinson Crusoe）是一个充满劳动热情的人。他坚毅地在这荒无人烟的孤岛上生活了28年，直至26年时，\"星期五\"出现了。并于28年后回到英国。面对人生困境，鲁滨逊的所作所为显示了一个作为男人的坚毅性格、英雄本色以及乐观向上的精神，体现了资产阶级上升时期的创造精神，开拓精神和敢于同恶劣的环境勇于斗争的精神。','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (3,'蔡志忠','现代出版社',1237873000234,'1万','1','191',1,NULL,'12345678','蔡志忠,1948年2月2日,出生于台湾彰化,1963年开始画连环漫画','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (4,'丹·布朗','上诲人民出版社',1237873000234,'1万','1','100',1,NULL,'12345678','丹.布朗(DanBrown,1964-) 美国著名畅销作家,毕业于阿莫斯特大学,曾是一名英语教师,一九九六年开始写作,先后推出了<<数字城堡>>,<<骗局>>,<<天使与魔鬼>>,<<达芬奇密码>>和<<失落的秘符>>五部小说','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (5,'杨红樱','接力出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (6,'E·B·怀特','上海译文出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (7,'赵学良 译 ','清华大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (8,' 庐隐','上诲人民出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (9,'布赖恩·伯勒 ','哈珀·柯林集团出版 ',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (10,'高尔基','清华大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (11,'匿名','北京大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (12,'露易莎·梅·奥尔科特','上海译文出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (13,'（美）沃森','化学工业出版社',1237873000234,'1万','1','100',1,NULL,'12345678','詹姆斯·沃森（James D.Watson）1928年出生于芝加哥。在芝加哥大学修动物学，后来前往欧洲，在哥本哈根和剑桥从事研究工作，在那里他和弗朗西斯?克里克合作发现了DNA的结构，并因此分享了1962年的诺贝尔奖。1990年代，沃森成为第一个主持人类基因组研究的首席科学家。','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (14,'夏洛蒂·勃朗特','上海生活书店出版',1237873000234,'1万','1','100',1,NULL,'12345678','夏洛蒂·勃朗特（Charlotte Bronte，1816-1855年）','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (15,'张孝祥','清华大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','张孝祥，1995年毕业于北京理工大学，获学士学位。毕业后在计算机专业的专家指导下，熟练掌握了VC，VB，JAVA等编程语言及工具，潜心研究计算机软件开发，曾先后在国家863计划CIMS项目组、泰克威尔公司、摩托罗拉公司、清华万博的中国教育热线网站从事软、硬件开发','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (16,'小仲马','四川文艺出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (17,'雪莱','中央编译出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (18,'高尔基','漓江出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (19,'玛格丽特·米切尔','漓江出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (20,'兰伯特','经济管理出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (21,'王晓春','漓江出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (22,'简·奥斯汀','清华大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (23,'高尔基','清华大学出版社',1237873000234,'1万','1','100',1,NULL,'12345678','作者是好人','这真是一本好书啊');
insert  into `d_book`(`id`,`author`,`publishing`,`publish_time`,`word_number`,`which_edtion`,`total_page`,`print_time`,`print_number`,`isbn`,`author_summary`,`catalogue`) values (24,'黑柳彻子','南海出版社',1237873000234,'17.5万字','2','272',1,NULL,'9787544250580','黑柳彻子 日本著名作家，著名电视节目主持人，联合国儿童基金会亲善大使。代表作《窗边的小豆豆》1981年出版后，不仅在日本，而且在全球都引起了极大的反响，截至2001年，日文版累计销量达938万册，成为日本历史上销量最大的一本书。该书已经被译成33种文','这真是一本好书啊');

/*Table structure for table `d_category` */

DROP TABLE IF EXISTS `d_category`;

CREATE TABLE `d_category` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `turn` int(10) NOT NULL,
  `en_name` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

/*Data for the table `d_category` */

insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (1,1,'Book','图书',NULL,0);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (2,1,'Novel','小说',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (3,2,'Youth','青春',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (4,3,'Humanity And Social Science','人文社科',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (5,4,'Management','管理',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (6,5,'Children','少儿',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (7,6,'Foreign Language','外语',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (8,7,'Computer','计算机',NULL,1);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (9,1,'Chinese Contemporary Novel','当代小说',NULL,2);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (10,2,'Chinese Modern Novel','近现代小说',NULL,2);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (11,3,'Chinese Classical  Novel','古典小说',NULL,2);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (12,4,'Four Classic Novels','四大名著',NULL,2);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (13,5,'Translated Works','世界名著',NULL,2);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (14,1,'School','校园',NULL,3);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (15,2,'Love','爱情/情感',NULL,3);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (16,3,'Grow Up','叛逆/成长',NULL,3);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (17,4,'Fantasy','玄幻',NULL,3);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (18,5,'Original','原创',NULL,3);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (19,1,'Political','政治',NULL,4);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (20,2,'Economic','经济',NULL,4);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (21,3,'Law','法律',NULL,4);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (22,4,'Philosophy','哲学',NULL,4);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (23,5,'History','历史',NULL,4);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (24,1,'Commen Management','管理学',NULL,5);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (25,2,'Strategic Management','战略管理',NULL,5);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (26,3,'Marketing','市场营销',NULL,5);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (27,4,'Business History','商业史传',NULL,5);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (28,5,'E-Business','电子商务',NULL,5);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (29,1,'0-2','0-2岁',NULL,6);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (30,2,'3-6','3-6岁',NULL,6);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (31,3,'7-10','7-10岁',NULL,6);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (32,4,'11-14','11-14岁',NULL,6);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (33,5,'Child Literature','儿童文学',NULL,6);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (34,1,'English','英语',NULL,7);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (35,2,'Japanese','日语',NULL,7);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (36,3,'Korean','韩语',NULL,7);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (37,4,'Russian','俄语',NULL,7);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (38,5,'German','德语',NULL,7);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (39,1,'Computer Theory','计算机理论',NULL,8);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (40,2,'Database','数据库',NULL,8);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (41,3,'Programming','程序设计',NULL,8);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (42,4,'Artificial Intelligence','人工智能',NULL,8);
insert  into `d_category`(`id`,`turn`,`en_name`,`name`,`description`,`parent_id`) values (43,5,'Computer Examination','计算机考试',NULL,8);

/*Table structure for table `d_category_product` */

DROP TABLE IF EXISTS `d_category_product`;

CREATE TABLE `d_category_product` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `product_id` int(10) NOT NULL,
  `cat_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

/*Data for the table `d_category_product` */

insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (1,1,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (2,2,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (3,3,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (4,4,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (5,5,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (6,6,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (7,7,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (8,8,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (9,9,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (10,10,9);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (11,11,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (12,12,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (13,13,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (14,14,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (15,15,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (16,16,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (17,17,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (18,18,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (19,19,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (20,20,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (21,21,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (22,22,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (23,23,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (24,24,10);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (25,1,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (26,2,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (27,3,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (28,4,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (29,5,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (30,6,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (31,7,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (32,8,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (33,9,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (34,10,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (35,11,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (36,12,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (37,13,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (38,14,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (39,15,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (40,16,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (41,17,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (42,18,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (43,19,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (44,20,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (45,21,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (46,22,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (47,23,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (48,24,2);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (49,1,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (50,2,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (51,3,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (52,4,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (53,5,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (54,6,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (55,7,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (56,8,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (57,9,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (58,10,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (59,11,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (60,12,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (61,13,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (62,14,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (63,15,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (64,16,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (65,17,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (66,18,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (67,19,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (68,20,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (69,21,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (70,22,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (71,23,1);
insert  into `d_category_product`(`id`,`product_id`,`cat_id`) values (72,24,1);

/*Table structure for table `d_item` */

DROP TABLE IF EXISTS `d_item`;

CREATE TABLE `d_item` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `dang_price` double NOT NULL,
  `product_num` int(10) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_item` */

/*Table structure for table `d_order` */

DROP TABLE IF EXISTS `d_order`;

CREATE TABLE `d_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `status` int(10) NOT NULL,
  `order_time` bigint(20) NOT NULL,
  `order_desc` varchar(100) DEFAULT NULL,
  `total_price` double NOT NULL,
  `receive_name` varchar(100) DEFAULT NULL,
  `full_address` varchar(200) DEFAULT NULL,
  `postal_code` varchar(8) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `d_order` */

/*Table structure for table `d_product` */

DROP TABLE IF EXISTS `d_product`;

CREATE TABLE `d_product` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `add_time` bigint(20) DEFAULT NULL,
  `fixed_price` double NOT NULL,
  `dang_price` double NOT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `has_deleted` int(1) NOT NULL DEFAULT '0',
  `product_pic` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `d_product` */

insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (1,'一个陌生女人的来信','短篇小说《一个陌生女人的来信》为茨威格代表作之一。',1237873000234,200,180,'key',0,'16.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (2,'鲁滨逊漂流记','鲁滨逊漂流记',1237873000234,200,180,'key',0,'17.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (3,'老子说','本套图书囊括了蔡志忠漫画中国思想,漫画中国传统经典著作的经典,采用中英文对照的全新编辑方式,英文紧随中文之后,在每一页上立即实现对照,并用大开本印刷,非常方便中英文学习',1237873000234,200,180,'key',0,'18.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (4,'达·芬奇密码','是作者丹.布朗的一部著名小说,2003年3月18号由兰登书屋出版,并以750万本的成绩打破美国小说记录,是有史以来最卖座的小说,小说集合了侦探,惊悚和阴谋论等多种风格',1237873000234,200,180,'key',0,'19.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (5,'淘气包马小跳','淘气包马小跳',1237873000234,200,180,'key',0,'20.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (6,'夏洛的网','夏洛的网',1237873000234,200,180,'key',0,'21.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (7,'Servlet与JSP核心编程','Servlet与JSP核心编程',1237873000234,200,180,'key',0,'22.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (8,'庐隐作品集','庐隐作品集',1237873000234,200,180,'key',0,'23.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (9,'门口的野蛮人','门口的野蛮人',1237873000234,200,180,'key',0,'24.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (10,'童年·在人间·我的大学','童年·在人间·我的大学',1237873000234,200,180,'key',0,'2.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (11,'海水 观赏鱼','海水 观赏鱼',1237873000234,200,180,'key',0,'3.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (12,'小妇人','小妇人',1237873000234,200,180,'key',0,'4.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (13,'双螺旋','本书告诉你这个伟大发现是如何发生的。本书是最经典的生命科学科普图书之一，是诺贝尔生理学或医学奖获得者沃森的大作。',1237873000234,200,180,'key',0,'5.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (14,'简·爱','简·爱',1237873000234,200,180,'key',0,'6.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (15,'Java就业培训教程','Java就业培训教程',1237873000234,200,180,'key',0,'7.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (16,'茶花女','茶花女',1237873000234,200,180,'key',0,'8.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (17,'雪莱诗选','雪莱诗选',1237873000234,200,180,'key',0,'9.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (18,'在人间','在人间',1237873000234,200,180,'key',0,'10.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (19,'飘','飘',1237873000234,200,180,'key',0,'11.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (20,'关键管理问题','关键管理问题',1237873000234,200,180,'key',0,'12.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (21,'孩子,我们一路同行','孩子,我们一路同行',1237873000234,200,180,'key',0,'13.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (22,'傲慢与偏见','傲慢与偏见',1237873000234,200,180,'key',0,'14.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (23,'童年','童年',1237873000234,200,180,'key',0,'15.jpg');
insert  into `d_product`(`id`,`product_name`,`description`,`add_time`,`fixed_price`,`dang_price`,`keywords`,`has_deleted`,`product_pic`) values (24,'窗边的小豆豆','本书讲述了作者上小学时的一段真实的故事。这本书不仅带给世界千万读者无数的笑声和感动，而且为现代教育的发展注入了新的活力，成为20世纪全球最有影响的作品之一。',1237873000234,25,13.8,'童年.欢乐.成长.感人',0,'1.jpg');

/*Table structure for table `d_receive_address` */

DROP TABLE IF EXISTS `d_receive_address`;

CREATE TABLE `d_receive_address` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receive_name` varchar(20) NOT NULL,
  `full_address` varchar(200) NOT NULL,
  `postal_code` varchar(8) NOT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

/*Data for the table `d_receive_address` */

insert  into `d_receive_address`(`id`,`user_id`,`receive_name`,`full_address`,`postal_code`,`mobile`,`phone`) values (1,6,'Java','sun.cn','10000800','12345','67890');
insert  into `d_receive_address`(`id`,`user_id`,`receive_name`,`full_address`,`postal_code`,`mobile`,`phone`) values (2,6,'JavaJavaJava','ibm.cn','10000600','12345','67890');

/*Table structure for table `d_user` */

DROP TABLE IF EXISTS `d_user`;

CREATE TABLE `d_user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `user_integral` int(12) NOT NULL DEFAULT '0',
  `is_email_verify` char(3) DEFAULT NULL,
  `email_verify_code` varchar(52) DEFAULT NULL,
  `last_login_time` bigint(20) DEFAULT NULL,
  `last_login_ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `d_user` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;

/*Data for the table `project` */

insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (1,'pm001','2008-01-02','2008-01-15');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (2,'pm002','2008-02-01','2008-02-20');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (3,'pm003','2008-03-01','2008-03-08');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (4,'pm004','2008-03-08','2008-03-10');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (5,'pm005','2008-04-08','2008-04-20');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (6,'pm006','2008-02-01','2008-02-20');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (7,'pm007','2008-03-01','2008-03-08');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (8,'pm008','2008-03-08','2008-03-10');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (9,'pm009','2008-01-02','2008-01-15');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (10,'pm010','2008-02-01','2008-02-20');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (11,'pm011','2008-03-01','2008-03-08');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (12,'pm012','2008-03-08','2008-03-10');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (13,'pm013','2008-01-02','2008-01-15');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (14,'pm014','2008-02-01','2008-02-20');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (15,'pm015','2008-03-01','2008-03-08');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (16,'pm016','2008-03-08','2008-03-10');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (17,'pm017','2014-01-01','2014-02-26');
insert  into `project`(`id`,`name`,`start_date`,`end_date`) values (18,'xxx','2015-01-10','2015-05-10');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
