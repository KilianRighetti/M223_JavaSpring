insert into game_profile (id, nickname, level, favorite_game, deleted)
values
    (NEXT VALUE FOR profile_seq, 'ShadowX', 12, 'Minecraft', false),
    (NEXT VALUE FOR profile_seq, 'DragonSlayer', 25, 'Elden Ring', false),
    (NEXT VALUE FOR profile_seq, 'PixelMaster', 7, 'Terraria', false),
    (NEXT VALUE FOR profile_seq, 'NightHunter', 31, 'Call of Duty Black Hops', false),
    (NEXT VALUE FOR profile_seq, 'SpeedRunner', 18, 'Mario Kart 8 Deluxe', false),
    (NEXT VALUE FOR profile_seq, 'RetroKing', 40, 'The Legend of Zelda Tears of the Kingdom', false);