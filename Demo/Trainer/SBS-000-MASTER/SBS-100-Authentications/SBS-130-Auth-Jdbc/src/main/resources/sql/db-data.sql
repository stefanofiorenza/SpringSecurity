-- User user/pass
INSERT INTO users (username, password, enabled)
  values ('stefano',
    '$2a$10$DtXDXg2OHCLZMLbtTXD18.JAPk.SLualOsIHuykSq7ydwdF/hpV6q',
    1);
 
INSERT INTO authorities (username, authority)
  values ('user', 'ROLE_USER'),  
  values ('user', 'ROLE_MANAGER');