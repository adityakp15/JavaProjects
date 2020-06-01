DROP TABLE Emp_Payroll;
CREATE TABLE Emp_Payroll(
	eid NUMBER(9) CONSTRAINT emp_paypk PRIMARY KEY,
	ename VARCHAR2(30),
	dob DATE,
	sex CHAR,
	designation VARCHAR2(50),
	basic NUMBER(9,2),
	da NUMBER(9,2),	
	hra NUMBER(9,2),	
	pf NUMBER(9,2),	
	mc NUMBER(9,2),	
	gross NUMBER(9,2),	
	tot_deduc NUMBER(9,2),
	net_pay NUMBER(14,2)
);	

CREATE OR REPLACE PROCEDURE calculations(empid IN emp_payroll.eid%TYPE,empbasic IN emp_payroll.basic%TYPE ) IS

BEGIN
UPDATE Emp_Payroll
SET da= 0.6*empbasic,
	hra= 0.11*empbasic,
	pf=0.04*empbasic,
	mc=0.03*empbasic,
	gross=(1.71*empbasic),
	tot_deduc=(0.07*empbasic),
	net_pay=(1.64*empbasic)
WHERE eid=empid;

END;
/
