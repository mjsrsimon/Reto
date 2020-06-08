--- me muestra los asistentes (he puesto solo el id_socio) ----- 

SELECT
 actividad.fecha as "FECHA ACTIVIDAD",
 tipoactividad.descripcion as "TIPO DE ACTIVIDAD",    
 ACTIVIDAD.DESCRIPCION AS "DESCRIPCION ACTIVIDAD",
 ACTIVIDAD.PRECIO,
 socioparticipa.id_participante

  FROM
 ACTIVIDAD, TIPOACTIVIDAD ,  SOCIO, socioparticipa
  WHERE
 ACTIVIDAD.TIPOACTIVIDAD=TIPOACTIVIDAD.ID_TIPO_ACTIVIDAD AND
 ACTIVIDAD.PRECIO>0 
 and actividad.id_actividad=socioparticipa.id_actividad and 
 socio.id_socio=socioparticipa.id_participante ;
 
 
---- me muestra el organizador ----  

SELECT
 actividad.fecha as "FECHA ACTIVIDAD",
 tipoactividad.descripcion as "TIPO DE ACTIVIDAD",    
 ACTIVIDAD.DESCRIPCION AS "DESCRIPCION ACTIVIDAD",
 ACTIVIDAD.PRECIO,
 SOCIO.NOMBRE AS "NOMBRE ORGANIZADOR",
 SOCIO.APELLIDOS AS "APELLIDOS ORGANIZADOR"
  FROM
 ACTIVIDAD, TIPOACTIVIDAD ,  SOCIO
  WHERE
 ACTIVIDAD.TIPOACTIVIDAD=TIPOACTIVIDAD.ID_TIPO_ACTIVIDAD AND
 ACTIVIDAD.PRECIO>0 AND
 ACTIVIDAD.ORGANIZADOR=SOCIO.ID_SOCIO;
