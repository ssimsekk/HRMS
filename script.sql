BEGIN;


CREATE TABLE public.candidates_email_verification
(
    id integer NOT NULL,
	token_id integer NOT NULL,
    candidate_id integer NOT NULL,
	is_verified boolean,
	verification_date date,
    PRIMARY KEY (id)
);

CREATE TABLE public.verification_tokens
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    token character varying(100) NOT NULL,
    generation_date date NOT NULL,
    expiration_date date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.employers_email_verification
(
    id integer NOT NULL,
	token_id integer NOT NULL,
    employer_id integer NOT NULL,
	is_verified boolean,
	verification_date date,
    PRIMARY KEY (id)
);

CREATE TABLE public.candidates
(
    candidate_id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    nationality_id character(11) NOT NULL,
    date_of_birth date NOT NULL,
    PRIMARY KEY (candidate_id)
);

CREATE TABLE public.employees
(
    employee_id integer NOT NULL,
    first_name character varying(25) NOT NULL,
    last_name character varying(25) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE public.employers
(
    employer_id integer NOT NULL,
    company_name character varying(50) NOT NULL,
    website character varying(50) NOT NULL,
    phone_number character varying(12) NOT NULL,
    PRIMARY KEY (employer_id)
);

CREATE TABLE public.employers_activation_by_employees
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    employer_id integer NOT NULL,
    employee_id integer NOT NULL,
    is_verified boolean NOT NULL,
    verification_date date,
    PRIMARY KEY (id)
);

CREATE TABLE public.job_positions
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    position_name character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.users
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    email character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.candidates_email_verification
    ADD FOREIGN KEY (candidate_id)
    REFERENCES public.candidates (candidate_id)
    NOT VALID;


ALTER TABLE public.candidates_email_verification
    ADD FOREIGN KEY (token_id)
    REFERENCES public.verification_tokens (id)
    NOT VALID;


ALTER TABLE public.employers_email_verification
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.employers (employer_id)
    NOT VALID;


ALTER TABLE public.employers_email_verification
    ADD FOREIGN KEY (token_id)
    REFERENCES public.verification_tokens (id)
    NOT VALID;


ALTER TABLE public.candidates
    ADD FOREIGN KEY (candidate_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employees
    ADD FOREIGN KEY (employee_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employers
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.users (id)
    NOT VALID;


ALTER TABLE public.employers_activation_by_employees
    ADD FOREIGN KEY (employee_id)
    REFERENCES public.employees (employee_id)
    NOT VALID;


ALTER TABLE public.employers_activation_by_employees
    ADD FOREIGN KEY (employer_id)
    REFERENCES public.employers (employer_id)
    NOT VALID;

END;