insert into player (id, first_name, last_name, age, email, deleted)
values
    (NEXT VALUE FOR player_seq, 'Luca', 'Rossi', 21, 'luca.rossi@gmail.com', false),
    (NEXT VALUE FOR player_seq, 'Marco', 'Bianchi', 27, 'marco.bianchi@gmail.com', false),
    (NEXT VALUE FOR player_seq, 'Anna', 'Verdi', 19, 'anna.verdi@gmail.com', false),
    (NEXT VALUE FOR player_seq, 'Sofia', 'Ferrari', 24, 'sofia.ferrari@gmail.com', false),
    (NEXT VALUE FOR player_seq, 'Thomas', 'Meyer', 30, 'thomas.meyer@gmail.com', false),
    (NEXT VALUE FOR player_seq, 'Elena', 'Russo', 22, 'elena.russo@gmail.com', false);