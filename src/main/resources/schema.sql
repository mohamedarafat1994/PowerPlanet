DROP TABLE IF EXISTS TBL_EMPLOYEES;
 
DROP TABLE IF EXISTS POWERPLANET;  
CREATE TABLE POWERPLANET (  
SEQGEN19 INT AUTO_INCREMENT  PRIMARY KEY,  
YEAR VARCHAR(50) NOT NULL,  
PSTATABB VARCHAR(50) NOT NULL,
PNAME VARCHAR(50) NOT NULL,
GENID VARCHAR(50) NOT NULL,
GENSTAT VARCHAR(50) NOT NULL,
GENNTAN DECIMAL(15,3) DEFAULT 0
);

