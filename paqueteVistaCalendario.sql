create or replace package vistaCalendario is
 
 type cursorReferencia is ref cursor;
 
  procedure vista (
 
 fechaActividad out actividad.fecha%type,
 tipoActividadHecha out tipoactividad.descripcion%type,
 descActividad out ACTIVIDAD.DESCRIPCION%type,
 coste out actividad.precio%type,
 organizadorNombre out  SOCIO.NOMBRE%type,
 organizadorApellido out  SOCIO.APELLIDOS%type,
 participantes out cursorReferencia
 
 )
end;


 create or replace package body vistaCalendario is(
 
 
  procedure vista (
 
 fechaActividad out actividad.fecha%type,
 tipoActividadHecha out tipoactividad.descripcion%type,
 descActividad out ACTIVIDAD.DESCRIPCION%type,
 coste out actividad.precio%type,
 organizadorNombre out  SOCIO.NOMBRE%type,
 organizadorApellido out  SOCIO.APELLIDOS%type,
 participantes out cursorReferencia
 
 )
 is
 begin
 open participantes for select  socioparticipa.id_participante from socioparticipa;
 
 
 
 end;
 
 
end;
