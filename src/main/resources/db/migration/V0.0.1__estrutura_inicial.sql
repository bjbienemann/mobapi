-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.0
-- PostgreSQL version: 9.6
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: mobapi | type: DATABASE --
-- -- DROP DATABASE IF EXISTS mobapi;
-- CREATE DATABASE mobapi
-- ;
-- -- ddl-end --
-- 

-- object: public.onibus_linha | type: TABLE --
-- DROP TABLE IF EXISTS public.onibus_linha CASCADE;
CREATE TABLE public.onibus_linha(
	id bigint NOT NULL,
	codigo character varying NOT NULL,
	nome character varying NOT NULL,
	CONSTRAINT onibus_linha_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.onibus_linha OWNER TO postgres;
-- ddl-end --

-- object: public.onibus_itinerario | type: TABLE --
-- DROP TABLE IF EXISTS public.onibus_itinerario CASCADE;
CREATE TABLE public.onibus_itinerario(
	id bigserial NOT NULL,
	id_onibus_linha bigint NOT NULL,
	ordem integer NOT NULL,
	latitude decimal NOT NULL,
	longitude decimal NOT NULL,
	CONSTRAINT onibus_itineario_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.onibus_itinerario OWNER TO postgres;
-- ddl-end --

-- object: onibus_linha_fk | type: CONSTRAINT --
-- ALTER TABLE public.onibus_itinerario DROP CONSTRAINT IF EXISTS onibus_linha_fk CASCADE;
ALTER TABLE public.onibus_itinerario ADD CONSTRAINT onibus_linha_fk FOREIGN KEY (id_onibus_linha)
REFERENCES public.onibus_linha (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


