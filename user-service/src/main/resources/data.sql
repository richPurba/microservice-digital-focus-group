DELETE FROM user;
INSERT INTO user VALUES (0, unix_timestamp(now()), unix_timestamp(now()), 'john.doe@example.com', 'John', 'Doe', 'user');
INSERT INTO user VALUES (1, unix_timestamp(now()), unix_timestamp(now()), 'Jue.wang@dawnware', 'Jue', 'Wang', 'pw');