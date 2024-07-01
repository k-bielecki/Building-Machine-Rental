--liquibase formatted sql
--changeset kbielecki:5

INSERT INTO details (machine_hour, weight, horse_power, category) VALUES
(1000, 1500.50, 250, 'EXCAVATOR'),
(2000, 2200.75, 300, 'BULLDOZER'),
(1500, 1800.00, 275, 'WHEELED_LOADER'),
(3000, 3500.10, 400, 'BULLDOZER'),
(2500, 3100.80, 350, 'CRANE'),
(1200, 1450.25, 240, 'ROAD_ROLLER'),
(1700, 2000.45, 280, 'BACKHOE'),
(900, 1350.35, 230, 'CRANE'),
(800, 1250.60, 220, 'BACKHOE'),
(1300, 1600.90, 260, 'FORK_LIFT');

INSERT INTO machine (name, price_per_day, is_rented, condition, details_id) VALUES
('CAT 320 Excavator', 500.00, false, 'GOOD', 1),
('Komatsu D155 Bulldozer', 750.00, false, 'USED', 2),
('JCB 426 Wheeled Loader', 600.00, false, 'VERY_GOOD', 3),
('Liebherr PR736 Bulldozer', 850.00, false, 'PERFECT', 4),
('Grove GMK4100 Crane', 1000.00, false, 'GOOD', 5),
('Bomag BW213 Road Roller', 450.00, false, 'USED', 6),
('CAT 428F Backhoe Loader', 480.00, false, 'VERY_GOOD', 7),
('Potain MDT 178 Crane', 950.00, false, 'USED', 8),
('JCB 3CX Backhoe Loader', 520.00, false, 'GOOD', 9),
('Toyota 8FGCU25 Fork Lift', 350.00, false, 'PERFECT', 10);

INSERT INTO users (first_name, last_name, email, nickname, password) VALUES
('John', 'Doe', 'john.doe@example.com', 'johndoe123', 'password1'),
('Jane', 'Smith', 'jane.smith@example.com', 'janesmith456', 'password2'),
('Robert', 'Johnson', 'robert.johnson@example.com', 'robertj789', 'password3'),
('Emily', 'Davis', 'emily.davis@example.com', 'emilydavis101', 'password4'),
('Michael', 'Brown', 'michael.brown@example.com', 'mikebrown112', 'password5'),
('Sarah', 'Miller', 'sarah.miller@example.com', 'sarahmiller98', 'password6'),
('David', 'Wilson', 'david.wilson@example.com', 'davidwilson77', 'password7'),
('Jessica', 'Moore', 'jessica.moore@example.com', 'jessicam45', 'password8'),
('Daniel', 'Taylor', 'daniel.taylor@example.com', 'danieltaylor33', 'password9'),
('Laura', 'Anderson', 'laura.anderson@example.com', 'laura_anderson', 'password10');

INSERT INTO rent_history (user_id, machine_id, rented_date, returned_date) VALUES
(1, 1, '2023-01-01 08:00:00', '2023-01-10 16:00:00'),
(2, 2, '2023-01-05 09:00:00', '2023-01-15 17:00:00'),
(3, 3, '2023-02-01 08:00:00', '2023-02-10 16:00:00'),
(4, 4, '2023-03-01 08:00:00', '2023-03-12 16:00:00'),
(5, 5, '2023-04-01 08:00:00', '2023-04-07 16:00:00'),
(6, 6, '2023-05-01 08:00:00', '2023-05-05 16:00:00'),
(7, 7, '2023-06-01 08:00:00', '2023-06-10 16:00:00'),
(8, 8, '2023-07-01 08:00:00', '2023-07-15 16:00:00'),
(9, 9, '2023-08-01 08:00:00', '2023-08-10 16:00:00'),
(10, 10, '2023-09-01 08:00:00', '2023-09-05 16:00:00');