------En este disparador debemos controlar que aquellos socios que no --------
------paguen la cuota antes del 31/12 del año correspondiente, se dará de baja. ---------
----------------------------------------------------------------------------------------

create or replace trigger bajas
before insert on cuota
for each row
declare

v_socio cuotapagada.id_socio%type;
v_fIni cuota.anyo%type:=  :new.anyo-730;
v_fFin cuota.anyo%type:= :new.anyo-365;


cursor cuotasImpagadas is 
select id_socio from cuotapagada, cuota
 where pagado is null and 
 cuota.id_cuota = cuotapagada.id_cuota and
 cuota.anyo >= v_fIni and cuota.anyo <= v_fFin; 

begin

open cuotasImpagadas; 
	loop 	
		fetch cuotasImpagadas into v_socio; 
		exit when cuotasImpagadas%notfound;
		update socio set fecha_baja_club = sysdate where v_socio = id_socio; 
		update cuotapagada set pagado = 'No' where v_socio = id_socio;
	end loop;
close cuotasImpagadas; 

end;