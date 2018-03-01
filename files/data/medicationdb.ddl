drop database "medication";
create database "medication";
\c medication

CREATE TABLE "medicationsideeffects" (
  "medication" varchar(50) NOT NULL,
  "interaction" varchar(150) NOT NULL,
  "sideeffects" varchar(150) NOT NULL,
  PRIMARY KEY (medication)
);

INSERT INTO "medicationsideeffects" ("medication","interaction","sideeffects") values ('Clorpres oral', 'Amoxapine decreases effects of clonidine. Avoid or use alternate drug.', 'dizziness, nausea, drowsiness, rash');

INSERT INTO "medicationsideeffects" ("medication","interaction","sideeffects") values ('Prinivil oral','Aliskirin increases risk of low potassium, reduced kidney function and excessively low blood pressure','dizziness, headache, tiredness, dry cough');

INSERT INTO "medicationsideeffects" ("medication","interaction","sideeffects") values ('Cozaar oral','Aliskirin increases risk of low potassium, reduced kidney function and excessively low blood pressure','cold or flu symptoms, stomach pain, insomnia');

INSERT INTO "medicationsideeffects" ("medication","interaction","sideeffects") values ('Ramipril orali','dry cough, fainting, weakness, nausea','Valsartin increases risk of kidney function impairment, high blood potassium levels and muscle paralysis');
commit;
