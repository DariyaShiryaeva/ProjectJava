-- 1. Таблица Отделов
CREATE TABLE department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

-- 2. Таблица Сотрудников (Связана с Отделом)
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    position VARCHAR(50),
    department_id INT REFERENCES department(id) ON DELETE SET NULL
);

-- 3. Таблица Задач (Связана с Сотрудником)
CREATE TABLE task (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    deadline DATE,
    employee_id INT REFERENCES employee(id) ON DELETE CASCADE
);

-- Добавим данные
-- Заполняем Отделы (Department)
INSERT INTO department (name, location) VALUES 
('IT Отдел', '3 этаж, серверная'),
('Бухгалтерия', '2 этаж, каб. 210'),
('Отдел Продаж', '1 этаж, оперзал');

-- Заполняем Сотрудников (Employee)
-- department_id ссылается на ID созданных выше отделов (1, 2 или 3)

-- Сотрудники IT отдела (id=1)
INSERT INTO employee (full_name, email, position, department_id) VALUES 
('Иван Иванов', 'ivan.dev@company.com', 'Senior Java Developer', 1),
('Петр Петров', 'petr.admin@company.com', 'DevOps Engineer', 1);

-- Сотрудники Бухгалтерии (id=2)
INSERT INTO employee (full_name, email, position, department_id) VALUES 
('Анна Сидорова', 'anna.acc@company.com', 'Главный бухгалтер', 2);

-- Сотрудники Отдела Продаж (id=3)
INSERT INTO employee (full_name, email, position, department_id) VALUES 
('Мария Кузнецова', 'maria.sales@company.com', 'Менеджер по продажам', 3),
('Дмитрий Соколов', 'dima.sales@company.com', 'Стажер', 3);

-- 4. Заполняем Задачи (Task)
-- Важно: employee_id ссылается на ID сотрудников (от 1 до 5)

-- Задачи для Ивана (Java Dev)
INSERT INTO task (title, description, deadline, employee_id) VALUES 
('Разработать REST API', 'Создать контроллеры для сущности User', '2024-12-01', 1),
('Фикс бага #102', 'Исправить ошибку NullPointer при логине', '2024-11-20', 1);

-- Задачи для Петра (DevOps)
INSERT INTO task (title, description, deadline, employee_id) VALUES 
('Настройка БД', 'Поднять PostgreSQL в Docker контейнере', '2024-11-25', 2);

-- Задачи для Анны (Бухгалтер)
INSERT INTO task (title, description, deadline, employee_id) VALUES 
('Годовой отчет', 'Свести дебет с кредитом за 2024 год', '2024-12-25', 3),
('Выплата зарплаты', 'Подготовить ведомости за ноябрь', '2024-11-30', 3);

-- Задачи для Марии (Продажи)
INSERT INTO task (title, description, deadline, employee_id) VALUES 
('Звонок клиенту', 'Обсудить продление контракта с ООО "Ромашка"', '2024-11-15', 4);

-- Задачи для Дмитрия (Стажер)
INSERT INTO task (title, description, deadline, employee_id) VALUES 
('Изучение продукта', 'Прочитать документацию по новым тарифам', '2024-11-18', 5);


SELECT * FROM department;
SELECT * FROM task;
SELECT * FROM employee;

DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS task CASCADE;
DROP TABLE IF EXISTS employee CASCADE;