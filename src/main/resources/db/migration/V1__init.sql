CREATE TABLE countries (
                           country_id SERIAL PRIMARY KEY,
                           country_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE cities (
                        city_id SERIAL PRIMARY KEY,
                        city_name VARCHAR(100) NOT NULL,
                        country_id INT NOT NULL REFERENCES countries(country_id)
                            ON DELETE CASCADE
);

CREATE TABLE hotels (
                        hotel_id SERIAL PRIMARY KEY,
                        hotel_name VARCHAR(150) NOT NULL,
                        city_id INT NOT NULL REFERENCES cities(city_id)
                            ON DELETE CASCADE,
                        stars INT NOT NULL CHECK (stars BETWEEN 1 AND 5),
                        address TEXT,
                        hotel_description TEXT,
                        phone VARCHAR(20)
);

CREATE TABLE hotel_images (
                              image_id SERIAL PRIMARY KEY,
                              hotel_id INT NOT NULL REFERENCES hotels(hotel_id)
                                  ON DELETE CASCADE,
                              image_url TEXT NOT NULL
);

CREATE TABLE tours (
                       tour_id SERIAL PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       description TEXT,
                       country_id INT NOT NULL REFERENCES countries(country_id),
                       hotel_id INT NOT NULL REFERENCES hotels(hotel_id),
                       price DECIMAL(10,2) NOT NULL CHECK (price > 0),
                       start_date DATE NOT NULL,
                       end_date DATE NOT NULL,
                       max_people INT NOT NULL CHECK (max_people > 0),
                       available_places INT NOT NULL CHECK (available_places >= 0),
                       is_hot BOOLEAN DEFAULT FALSE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CHECK (end_date > start_date)
);

CREATE TABLE tour_images (
                             image_id SERIAL PRIMARY KEY,
                             tour_id INT NOT NULL REFERENCES tours(tour_id)
                                 ON DELETE CASCADE,
                             image_url TEXT NOT NULL
);

CREATE TABLE clients (
                         client_id SERIAL PRIMARY KEY,
                         first_name VARCHAR(100) NOT NULL,
                         last_name VARCHAR(100) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         phone VARCHAR(20) UNIQUE NOT NULL,
                         birth_date DATE,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bookings (
                          booking_id SERIAL PRIMARY KEY,
                          client_id INT NOT NULL REFERENCES clients(client_id)
                              ON DELETE CASCADE,
                          tour_id INT NOT NULL REFERENCES tours(tour_id)
                              ON DELETE CASCADE,
                          booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          people_count INT NOT NULL CHECK (people_count > 0),
                          total_price DECIMAL(10,2) NOT NULL CHECK (total_price > 0),
                          status VARCHAR(30) NOT NULL CHECK (
                              status IN ('PENDING', 'PAID', 'CANCELLED')
                              )
);

CREATE TABLE admins (
                        admin_id SERIAL PRIMARY KEY,
                        full_name VARCHAR(150) NOT NULL,
                        email VARCHAR(150) UNIQUE NOT NULL,
                        password TEXT NOT NULL,
                        role VARCHAR(30) NOT NULL CHECK (
                            role IN ('SUPER_ADMIN', 'ADMIN')
                            )
);