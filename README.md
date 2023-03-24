# passec

create and store notes with ease. Secure by design.

# live version at https://passec.ramon.cc

## how to install backend

1. clone repo
2. `cd ./passec/backend`
3. make .env file with these variables:
  - MARIADB_DATABASE=passec
  - MARIADB_ROOT_PASSWORD=root123
  - DB_HOST=jdbc:mariadb://passec-db:3306/passec
4. run `sudo docker compose -p passec up -d`
5. enjoy

## how to run frontend

1. clone repo
2. `cd ./passec/frontend`
3. `npm install`
4. `npm run dev`
5. enjoy

