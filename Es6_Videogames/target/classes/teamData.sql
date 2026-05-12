insert into team (id, name, country, founded_at, deleted)
values
    (NEXT VALUE FOR videogame_seq, 'Red Dragons', 'Switzerland', '2015-03-12', false),
    (NEXT VALUE FOR videogame_seq, 'Blue Phoenix', 'Italy', '2018-07-01', false),
    (NEXT VALUE FOR videogame_seq, 'Night Wolves', 'Germany', '2020-11-21', false),
    (NEXT VALUE FOR videogame_seq, 'Shadow Legends', 'France', '2016-05-17', false),
    (NEXT VALUE FOR videogame_seq, 'Cyber Titans', 'USA', '2019-09-09', false);