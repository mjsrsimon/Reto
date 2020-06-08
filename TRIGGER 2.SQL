
-----------------------------------------
--controlar que los socios menores de edad tengan un responsable que será otro socio
--------------------------------------
create or replace trigger menor
before insert  on socio
for each roW
declare

annio number;

e_falta_responsable exception;
e_no_inscribir exception;

begin

select trunc(months_between((sysdate,:neW.fecha_nacimiento))/12) into annio  from  dual;
insert into error values(indiceerror.nextVal, 'trigger', annio, current_timestamp);

-- si es menor 18 y mayor de 4
if annio >=4 and annio <18 then
	insert into error values(indiceerror.nextVal, 'trigger', 'estoy en el if de annio', current_timestamp);
	raise e_falta_responsable;
elsif (annio <4) then
	insert into error values(indiceerror.nextVal, 'trigger', 'estoy en el elsif de annio', current_timestamp);
	raise e_no_inscribir;
end if;

exception
when e_falta_responsable then
raise_application_error(-20100, 'falta introducir un responsable para el nuevo socio');
when e_no_inscribir then
raise_application_error(-20101, 'no se puede inscribir al nuevo socio, es menor de 4 años.');

end menor;