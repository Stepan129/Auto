INSERT INTO autos (id, type, brand, model, year, country, condition, mileage, price, info)
VALUES  (1, 'легковий', 'NISSAN', 'universal', '2015','Японія','хороший', 12000, 2500, 'сімейний автомобіль'),
        (2, 'легковий', 'MERCEDES', 'sedan', '2019','Німеччина','хороший', 130000, 7500, 'автомобіль для справжніх мужчин'),
        (3, 'легковий', 'BMW', 'universal', '2012','Німеччина','хороший', 137000, 5500, 'дуже хороший стан'),
        (4, 'легковий', 'PEUGEOT', 'hatchback', '2013','Франція','хороший', 223000, 2000, 'бюджетний автомобіль'),
        (5, 'легковий', 'KIA', 'crossover', '2021','Корея','хороший', 110000, 3500, 'чудовий автомобіль для поїздок')
)

INSERT INTO users (username, password_hash, is_admin, first_name, middle_name, last_name, phone, email, info)
VALUES ('andriy100', '$2a$10$FIpjQDlCogT7evqJX7z.KOGVwnQTD1v4YK7G255OhlOdgMvjHjKYy', true, 'Andriy', 'Ivanovych', 'Burban', '+380673842378', 'Andriy.Burban@test', 'Fanny gay:)');
SELECT setval('users_id_seq', 1);