create or replace package inserciones is

procedure nuevaCuota (
codCuota out cuota.id_cuota%type,
precio_adulto_new in cuota.precio_adulto%type,
precio_menor_new in cuota.precio_menor%type,
anyo_new in cuota.anyo%type
);



end; 

create or replace package body inserciones is
procedure nuevaCuota (
codCuota out cuota.id_cuota%type,
precio_adulto_new in cuota.precio_adulto%type,
precio_menor_new in cuota.precio_menor%type,
anyo_new in cuota.anyo%type
)

 is

begin


INSERT INTO CUOTA VALUES (INCREMENTO_CUOTAS.nextVal, precio_adulto_new, precio_menor_new, anyo_new); 

select max(id_cuota) into codCuota from cuota; 


end; 
end; 
