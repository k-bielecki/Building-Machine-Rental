--liquibase formatted sql
--changeset kbielecki:3

CREATE TABLE rent_history (
  rent_history_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
   user_id BIGINT NOT NULL,
   machine_id BIGINT NOT NULL,
   rented_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
   returned_date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);