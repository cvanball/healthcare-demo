
SELECT * FROM PATIENT360.PATIENT

SELECT * FROM PATIENT360.PATIENT OPTION NOCACHE

SELECT * FROM PatientMatCache.Patient 

insert into PatientMatCache.Patient select * from PATIENT360.PATIENT OPTION NOCACHE

select count(*) from PatientMatCache.Patient

exec SYSADMIN.loadMatView(schemaName=>'PATIENT360',viewname=>'PATIENT', invalidate=>'true')


// before load script: execute PatientMatCache.native(''truncate cache'');
// after load script: execute PatientMatCache.native(''swap cache names'');

// load script: insert into PatientMatCache.Patient select * from PATIENT360.PATIENT OPTION NOCACHE;