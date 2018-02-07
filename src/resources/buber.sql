SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=1;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `buber` DEFAULT CHARACTER SET utf8;
USE `buber`;

CREATE TABLE IF NOT EXISTS `buber`.`user` (
  `login` VARCHAR(45) NOT NULL COMMENT 'Логин, под которым производится вход на сайт.',
  `password` VARCHAR(45) NOT NULL COMMENT 'Пароль, под которым производится вход на сайт.',
  `role` ENUM('admin', 'user', 'driver') NOT NULL DEFAULT 'user' COMMENT 'Показывает уровень доступа к внутренним данным базы данных. Админ способен «банить», давать премии постоянным клиентам.',
  `is_muted` TINYINT NOT NULL DEFAULT 0 COMMENT 'Показатель того, является ли пользователь заблокированным',
  `is_online` TINYINT NOT NULL DEFAULT 0 COMMENT 'Показатель того, является ли пользователь водителем. При положительном исходе, пользователю веб-приложения предложат выбрать режим входа: Войти как клиент или Войти как водитель.',
   PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `buber`.`user_info` (
  `login` VARCHAR(45) NOT NULL COMMENT 'Логин пользователя',
  `name` VARCHAR(45) NULL COMMENT 'Имя пользователя',
  `surname` VARCHAR(45) NULL COMMENT 'Фамилия пользователя',
  `lastname` VARCHAR(45) NULL COMMENT 'Отчество пользователя',
  `email` VARCHAR(45) NOT NULL COMMENT 'Электронная почта пользователя',
  `avatar` BLOB NULL COMMENT 'Фото пользователя',
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  CONSTRAINT `user_info_to_user`
    FOREIGN KEY (`login`)
    REFERENCES `buber`.`user` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица содержит в себе описание пользователя веб-приложения, общую информацию о нём, его фотографию, а также показатель того, является ли он водителем.';


CREATE TABLE IF NOT EXISTS `buber`.`client_info` (
  `login` VARCHAR(45) NOT NULL COMMENT 'Логин пользователя, являющегося клиентом',
  `trips_number` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Количество поездок клиента',
  `reputation` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Репутация клиента',
  `phone_number` VARCHAR(45) NULL COMMENT 'Мобильный номер клиента',
  `is_wait` TINYINT NOT NULL DEFAULT 0 COMMENT 'Содержит показатель того, находится ли в данный момент клиент на стадии ожидания такси',
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  CONSTRAINT `client_info_to_user_info`
    FOREIGN KEY (`login`)
    REFERENCES `buber`.`user_info` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица содержит описание пользователя веб-приложения как клиента, отображает его данные связи, а также статистическую информацию о нём (кол-во поездок, репутацию) и отображает онлайн ли клиент или вышел из системы.';



CREATE TABLE IF NOT EXISTS `buber`.`driver_info` (
  `login` VARCHAR(45) NOT NULL COMMENT 'Логин пользователя, являющегося водителем',
  `car_number` VARCHAR(45) NOT NULL COMMENT 'Номер машины, которой управляет водитель',
  `reputation` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Репутация водителя',
  `phone_number` VARCHAR(45) NULL COMMENT 'Мобильный номер водителя',
  `trips_number` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT 'Количество поездок водителя',
  `driver_license` BLOB NOT NULL COMMENT 'Фото водительского удостоверения',
  `is_busy` TINYINT NOT NULL DEFAULT 0 COMMENT 'Содержит показатель того, занят ли в данный момент времени водитель или нет (обслуживает ли он клиента)',
  PRIMARY KEY (`login`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `car_number_UNIQUE` (`car_number` ASC),
  CONSTRAINT `driver_info_to_user_info`
    FOREIGN KEY (`login`)
    REFERENCES `buber`.`user_info` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица содержит описание пользователя веб-приложения как водителя, если он таковым является, отображает логин пользователя, который решил стать водителем, номер машины, которой он управляет, репутация водителя, телефонный номер, количество выполненных заказов, паспортные данные и отображает онлайн ли водитель или вышел из системы';


CREATE TABLE IF NOT EXISTS `buber`.`car` (
  `car_number` VARCHAR(45) NOT NULL COMMENT 'Номер машины',
  `avatar` VARCHAR(45) NULL COMMENT 'Фото машины',
  `description` TEXT(1000) NULL COMMENT 'Описание машины',
  PRIMARY KEY (`car_number`),
  UNIQUE INDEX `car_number_UNIQUE` (`car_number` ASC),
  CONSTRAINT `car_to_driver_info`
    FOREIGN KEY (`car_number`)
    REFERENCES `buber`.`driver_info` (`car_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица для описания машины, которой управляет водитель: её номер, её описание и фото';


CREATE TABLE IF NOT EXISTS `buber`.`location` (
  `login` VARCHAR(45) NOT NULL COMMENT 'Идентификатор местоположения пользователя',
  `country` VARCHAR(45) NOT NULL COMMENT 'Содержит страну пользователя',
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL COMMENT 'Содержит улицу пользователя',
  `house_number` VARCHAR(45) NOT NULL COMMENT 'Содержит номер дома пользователя',
  PRIMARY KEY (`login`),
  UNIQUE INDEX `id_UNIQUE` (`login` ASC),
  CONSTRAINT `location_to_user_info`
    FOREIGN KEY (`login`)
    REFERENCES `buber`.`user_info` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица содержит информацию о местоположении пользователя. Используется для правильного подбора клиентов водителям и водителей клиентам.';


CREATE TABLE IF NOT EXISTS `buber`.`card_detail` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `hash_cardnumber` VARCHAR(45) NOT NULL COMMENT 'Содержит захэшированный номер банковской карты',
  `login` VARCHAR(45) NOT NULL COMMENT 'Логин владельца карты',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `card_detail_idx` (`login` ASC),
  CONSTRAINT `card_detail_to_user_info`
    FOREIGN KEY (`login`)
    REFERENCES `buber`.`user_info` (`login`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Таблица содержит информацию об электронных банковских карточках пользователей';



CREATE TABLE IF NOT EXISTS `buber`.`order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Содержит идентификатор заказа',
  `client_login` VARCHAR(45) NOT NULL COMMENT 'Содержит логин заказчика(клиента)',
  `driver_login` VARCHAR(45) NULL COMMENT 'Содержит логин исполнителя (водителя)',
  `money` DECIMAL(10,2) UNSIGNED NOT NULL COMMENT 'Содержит стоимость пути заказа',
  `is_done` ENUM('done', 'undone') NOT NULL DEFAULT 'undone' COMMENT 'Содержит показатель того, выполнен ли заказ',
  `date` DATETIME NOT NULL COMMENT 'Содержит дату заказа',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `driver_login_idx` (`driver_login` ASC),
  INDEX `client_login_idx` (`client_login` ASC))
ENGINE = InnoDB
COMMENT = 'Таблица содержит в себе информацию о заказах, совершенных пользователями';



INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('admin', 'admin', '1', DEFAULT, 0);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('LateRoad', 'qwerty', '2', DEFAULT, 1);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('Osminog', '1234', '2', DEFAULT, 1);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('PartyMaker', '4444', '2', DEFAULT, 1);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('Marina', 'tyuiop', '2', DEFAULT, 0);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('Lexa', 'lexarylit', '2', DEFAULT, 1);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('Abdul', 'alahakbar', '2', DEFAULT, 1);
INSERT INTO `buber`.`user` (`login`, `password`, `role`, `is_muted`, `is_online`) VALUES ('Kent', 'fartymasti', '2', DEFAULT, 1);

INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('LateRoad', 'Roman', 'Pozd', 'Yurievich', 'lateroad@gmail.com', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('Osminog', 'Vlad', 'Taras', 'Victorovich', 'kek@mail.ru', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('PartyMaker', 'Yan', 'Schneider', 'Alex', 'galat@gmail.com', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('Marina', 'Marina', 'Kovaleva', 'Lol', 'marina@gmail.com', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('Lexa', 'Alaxey', 'Kotov', 'Viniaminovich', 'kot@gmail.com', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('Abdul', 'Abdul', 'Alhazred', 'Allahovich', 'necronomicon@gmail.com', NULL);
INSERT INTO `buber`.`user_info` (`login`, `name`, `surname`, `lastname`, `email`, `avatar`) VALUES ('Kent', 'Vasya', 'Litov', 'Ketanovich', 'nasvas@gmail.com', NULL);

INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('LateRoad', 9, 100, '+3889218', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('Osminog', 1, 5, '+8888882', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('Marina', 92, 100, '+7283937', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('Kent', 2, 2, '+82930103', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('Abdul', 6, 66, '+92837182', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('PartyMaker', 21, 31, '+93837182', DEFAULT);
INSERT INTO `buber`.`client_info` (`login`, `trips_number`, `reputation`, `phone_number`, `is_wait`) VALUES ('Lexa', 8, 20, '+92837471', DEFAULT);

INSERT INTO `buber`.`driver_info` (`login`, `car_number`, `reputation`, `phone_number`, `trips_number`, `driver_license`, `is_busy`) VALUES ('PartyMaker', '1234-7', 20, '+56728162', 7, 0x383830393232, 0);
INSERT INTO `buber`.`driver_info` (`login`, `car_number`, `reputation`, `phone_number`, `trips_number`, `driver_license`, `is_busy`) VALUES ('Marina', '2234-8', 90, '+696969699', 69, 0x393939393939, 1);
INSERT INTO `buber`.`driver_info` (`login`, `car_number`, `reputation`, `phone_number`, `trips_number`, `driver_license`, `is_busy`) VALUES ('Abdul', '6666-6', 66, '+666666666', 666, 0x363636363636, 0);
INSERT INTO `buber`.`driver_info` (`login`, `car_number`, `reputation`, `phone_number`, `trips_number`, `driver_license`, `is_busy`) VALUES ('Kent', '7777-7', 100, '+123456789', 1, 0x30313233303132, 0);

INSERT INTO `buber`.`car` (`car_number`, `avatar`, `description`) VALUES ('1234-7', NULL, 'Simple jigulle');
INSERT INTO `buber`.`car` (`car_number`, `avatar`, `description`) VALUES ('2234-8', NULL, 'Red and Light');
INSERT INTO `buber`.`car` (`car_number`, `avatar`, `description`) VALUES ('7777-7', NULL, 'Understate Lada Kalina');
INSERT INTO `buber`.`car` (`car_number`, `avatar`, `description`) VALUES ('6666-6', NULL, 'Heifer');

INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('LateRoad', 'Belarus', 'Minsk', 'Smorgovski', '29');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('Osminog', 'Belarus', 'Minsk', 'Bykovski', '1');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('PartyMaker', 'Belarus', 'Minsk', 'Nezavisimosti', '9');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('Marina', 'Belarus', 'Minsk', 'Kyprevicha', '222');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('Lexa', 'Belarus', 'Minsk', 'Lenina', '90');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('Abdul', 'Belarus', 'Minsk', 'Smorgovski', '2');
INSERT INTO `buber`.`location` (`login`, `country`, `city`, `street`, `house_number`) VALUES ('Kent', 'Belarus', 'Minsk', 'Lenina', '9');

INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (1, '111111', 'LateRoad');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (2, '22222',  'Osminog');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (3, '33333', 'PartyMaker');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (4, '44444',  'Marina');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (5, '55555',  'Lexa');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (6, '66666', 'Abdul');
INSERT INTO `buber`.`card_detail` (`id`, `hash_cardnumber`,`login`) VALUES (7, '77777',  'Kent');

INSERT INTO `buber`.`order` (`id`, `client_login`, `driver_login`, `money`, `is_done`, `date`) VALUES (1, 'LateRoad', 'Marina', 2, '1', now());
INSERT INTO `buber`.`order` (`id`, `client_login`, `driver_login`, `money`, `is_done`, `date`) VALUES (2, 'Marina', NULL, 5, '0', now());
INSERT INTO `buber`.`order` (`id`, `client_login`, `driver_login`, `money`, `is_done`, `date`) VALUES (3, 'Lexa', 'Abdul', 8, '0', now());
INSERT INTO `buber`.`order` (`id`, `client_login`, `driver_login`, `money`, `is_done`, `date`) VALUES (4, 'Osminog', 'Kent', 4, '1', now());
INSERT INTO `buber`.`order` (`id`, `client_login`, `driver_login`, `money`, `is_done`, `date`) VALUES (5, 'LateRoad', 'PartyMaker', 9, '0', now());

/*Выборка всех водителей*/
SELECT  login FROM user_info WHERE is_driver=1;
/*Выборка всех заблокированных пользователей*/
SELECT login FROM user WHERE is_muted=1;


/*Подсчет всех вырученных денег*/
SELECT SUM(`money`) FROM `buber`.`order`;
/*Подсчет всех вырученных денег на данный момент*/
SELECT SUM(`money`) FROM `buber`.`order` WHERE `is_done` = 1;

SELECT CONCAT_WS(' ', `name`, `surname`, `lastname`) FROM `buber`.`user_info`;


/*Поиск водителя с наибольшим кол-вом выполненных заказов*/
SELECT `login` FROM `buber`.`driver_info` WHERE `reputation`> 60 AND`trips_number` = (SELECT  MAX(`trips_number`) origin `driver_info`);
/*Поиск пассажира с наибольшим кол-вом поездок*/
SELECT `login` FROM `buber`.`client_info` WHERE `reputation` > 80 AND `trips_number` = (SELECT  MAX(`trips_number`) origin `client_info`);
/*Поиск незанятых адресов (адресов свободных клиентов)*/
SELECT * FROM `buber`.`location` WHERE `login`= (SELECT `client_login` FROM `buber`.`order` WHERE `driver_login` is NULL);


/*Поиск незанятых водителей в онлайне на одной улице*/
SELECT ui.`login` FROM `buber`.`user_info` AS ui
	INNER JOIN `driver_info` AS di ON di.`is_online` = 1 AND di.`is_busy` = 0 AND ui.`login` = di.`login`
    INNER JOIN `location` AS loc ON loc.`country` = "Belarus" AND loc.`city` = "Minsk";
    
-- SELECT u.`login`, u.`password`, u.`role`, u.`is_muted`, ui.`name`, ui.`surname`, ui.`lastname`, ui.`email`, ci.`trips_number`, ci.`reputation`, ci.`phone_number`, ci.`is_online`, ci.`is_wait`, di.`car_number`, di.`reputation`, di.`phone_number`, di.`trips_number`, di.`driver_license`, di.`is_online`, di.`is_busy`
--   FROM `buber`.`user` AS u
--   JOIN `buber`.`user_info` ui ON u.`login` = ui.`login`
--   JOIN `buber`.`client_info` ci ON u.`login` = ci.`login`
--   JOIN `buber`.`driver_info` di ON u.`login` = di.`login`
--  WHERE u.`login` = 'LateRoad' AND u.`qwerty`;

SELECT * FROM `buber`.`user` AS u 
  JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`)
  JOIN `buber`.`driver_info` AS di ON (u.`login` = di.`login`)
  WHERE u.`login` = 'PartyMaker' AND u.`password` = '4444';
  
  SELECT * FROM `buber`.`user` AS u 
  JOIN `buber`.`user_info` AS ui ON (u.`login` = ui.`login`)
  JOIN `buber`.`client_info` AS ci ON (u.`login` = ci.`login`)
  WHERE u.`login` = 'PartyMaker' AND u.`password` = '4444';
  
  SELECT * FROM `buber`.`driver_info`;
  SELECT * FROM `buber`.`client_info`;
  SELECT * FROM `buber`.`user_info`;
  SELECT * FROM `buber`.`user`;
  
  DELETE FROM `buber`.`user` WHERE  `user`.`login` = 'PartyMaker';
 

SELECT DISTINCT di.`login` FROM `buber`.`driver_info` AS di
	LEFT JOIN `buber`.`location` AS loc ON loc.`country` = "Belarus" AND loc.`city` = "Minsk";
    
/*Вывести всех водителей и клиентов, которые онлайн*/
SELECT `login` FROM  `buber`.`client_info` WHERE `is_online` = 1
	UNION
SELECT `login` FROM  `buber`.`driver_info` WHERE `is_online` = 1;


SELECT AVG(`money`), `login`
  FROM `buber`.`user` AS u INNER JOIN `buber`.`order` AS o ON u.`login` = o.`client_login`
 GROUP BY `client_login`
HAVING AVG(`money`) BETWEEN 5 AND 1000;



/*Поиск пассажира с наибольшим кол-вом поездок*/
SELECT `login`, MAX(`reputation`) FROM `buber`.`client_info`;



-- /*Выборка всех водителей, которые онлайн*/
-- SELECT * FROM `driver_info` JOIN `user_info` ON `user_info`.`id` = 1 AND 'driver_info'.'is_online' = 1;
-- 
