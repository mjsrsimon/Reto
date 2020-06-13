create or replace package actualizaciones is

procedure actualizaUsuario (
codSocio in socio.id_socio%type,
emailNew in socio.email%type,
telfNew in socio.telf%type);

procedure actualizaAdministrador (
codSocio in socio.id_socio%type,
emailNew in socio.email%type,
telfNew in socio.telf%type,
nombreNew in socio.nombre%type,
apellidosNew in socio.apellidos%type,
fechaNacNew in socio.fecha_Nacimiento%type,
dniNew in socio.dni%type,
fechaAltaNew in socio.fecha_alta_club%type,
fechaBajaNew in socio.fecha_baja_club%type
);

end; 

create or replace package body actualizaciones is

procedure actualizaUsuario (
codSocio in socio.id_socio%type,
emailNew in socio.email%type,
telfNew in socio.telf%type)
 is

begin

update socio set email = emailNew, telf = telfNew where id_socio = codSocio; 

exception
when no_data_found then
raise_application_error(-20100, 'No se ha podido actualizar');


end; 




procedure actualizaAdministrador (
codSocio in socio.id_socio%type,
emailNew in socio.email%type,
telfNew in socio.telf%type,
nombreNew in socio.nombre%type,
apellidosNew in socio.apellidos%type,
fechaNacNew in socio.fecha_Nacimiento%type,
dniNew in socio.dni%type,
fechaAltaNew in socio.fecha_alta_club%type,
fechaBajaNew in socio.fecha_baja_club%type
) is

begin


update socio set 
email = emailNew, 
telf = telfNew,
nombre=	nombreNew,
apellidos=apellidosNew,
fecha_Nacimiento	=fechaNacNew,
dni=dniNew,
fecha_alta_club	=fechaAltaNew,
fecha_baja_club	=fechaBajaNew
where id_socio = codSocio; 

exception
when no_data_found then
raise_application_error(-20101, 'No se ha podido actualizar');

end;

end;