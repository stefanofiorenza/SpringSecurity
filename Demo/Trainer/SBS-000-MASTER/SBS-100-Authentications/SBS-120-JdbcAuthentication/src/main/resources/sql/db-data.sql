-- User user/pass (stefano, stefan0) see \test\java\com\knits\sbs\course\test\TestPasswordEncoder.java
INSERT INTO users (username, password, enabled)
  values ('stefano',
    '$2a$10$DtXDXg2OHCLZMLbtTXD18.JAPk.SLualOsIHuykSq7ydwdF/hpV6q',
    1);
 
INSERT INTO roles (username, authority)
  values ('raul', 'ROLE_USER');

INSERT INTO roles (username, authority)
  values ('stefano', 'ROLE_MANAGER');