#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER admin;
    CREATE DATABASE cardmanagement;
    ALTER USER admin WITH PASSWORD 'admin';
    GRANT ALL PRIVILEGES ON DATABASE cardmanagement TO admin;
EOSQL
