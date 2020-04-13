CREATE TABLE "Weather" (
    "WeatherID" bigserial,
    "Lat" double precision   NOT NULL,
    "Lon" double precision   NOT NULL,
    "WindSpeed" double precision   NOT NULL,
    "WindDirection" bigint   NOT NULL,
    "Temperature" double precision   NOT NULL,
    "WeatherType" varchar(20)   NOT NULL,
    "SnowVolume" double precision,
    "RainVolume" double precision,
    "Date" timestamp   NOT NULL,
    CONSTRAINT "pk_Weather" PRIMARY KEY (
        "WeatherID"
     )
);

CREATE TABLE "SpecialMode" (
    "SpecialModeID" bigserial,
    "Name" varchar(20)   NOT NULL,
    "DateEnable" timestamp,
    "DateDisable" timestamp,
    CONSTRAINT "pk_SpecialMode" PRIMARY KEY (
        "SpecialModeID"
     )
);

CREATE TABLE "Tower" (
    "TowerID" bigserial,
    "Name" varchar(100)   NOT NULL,
    "Lon" double precision   NOT NULL,
    "Lat" double precision   NOT NULL,
    CONSTRAINT "pk_Tower" PRIMARY KEY (
        "TowerID"
     )
);

CREATE TABLE "PowerLine" (
    "PowerLineID" bigserial,
    "Name" varchar(100)   NOT NULL,
    CONSTRAINT "pk_PowerLine" PRIMARY KEY (
        "PowerLineID"
     )
);

CREATE TABLE "PowerLineTower" (
    "PowerLineTowerID" bigserial,
    "PowerLineID" bigint   NOT NULL,
    "TowerID" bigint   NOT NULL,
    CONSTRAINT "pk_PowerLineTower" PRIMARY KEY (
        "PowerLineTowerID"
     )
);

CREATE TABLE "Shutdown" (
    "ShutdownID" bigserial,
    "WeatherID" bigint   NOT NULL,
    "PowerLineID" bigint   NOT NULL,
    CONSTRAINT "pk_Shutdown" PRIMARY KEY (
        "ShutdownID"
     )
);

ALTER TABLE "PowerLineTower" ADD CONSTRAINT "fk_PowerLineTower_PowerLineID" FOREIGN KEY("PowerLineID")
REFERENCES "PowerLine" ("PowerLineID");

ALTER TABLE "PowerLineTower" ADD CONSTRAINT "fk_PowerLineTower_TowerID" FOREIGN KEY("TowerID")
REFERENCES "Tower" ("TowerID");

ALTER TABLE "Shutdown" ADD CONSTRAINT "fk_Shutdown_WeatherID" FOREIGN KEY("WeatherID")
REFERENCES "Weather" ("WeatherID");

ALTER TABLE "Shutdown" ADD CONSTRAINT "fk_Shutdown_PowerLineID" FOREIGN KEY("PowerLineID")
REFERENCES "PowerLine" ("PowerLineID");