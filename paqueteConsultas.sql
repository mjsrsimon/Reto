create or replace package consultas is

procedure consultaRepresentante (
codRepresentante in socio.representante%type,
nombreRep out socio.nombre%type,
apellidosRep out socio.apellidos%type);

end; 

create or replace package body consultas is

procedure consultaRepresentante (
codRepresentante in socio.representante%type,
nombreRep out socio.nombre%type,
apellidosRep out socio.apellidos%type) is

begin

select nombre, apellidos into nombreRep, apellidosRep 
from socio
where id_socio=codRepresentante; 

exception
when no_data_found then
nombreRep:= '';
apellidosRep:='';

end; 

end;