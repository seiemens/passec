# passec

create and store notes with ease. Secure by design.

## how to install backend

1. clone repo
2. `cd ./passec/backend`
3. make .env file with these variables:
  - MARIADB_DATABASE=passec
  - MARIADB_ROOT_PASSWORD=root123
  - DB_HOST=jdbc:mariadb://passec-db:3306/passec
4. run `sudo docker compose -p passec up -d`
5. enjoy
