<!DOCTYPE html>
<html>
<head>
<title>${titulo}</title>
</head>
<body>
    <h1>Ejemplo de formulario</h1>
    <form action="/procesarFormulario/" method="post"  enctype="application/x-www-form-urlencoded">
         Matricula: <input name="matricula" type="number"/><br/>
         Nombre: <input name="nombre" type="text"/><br/>
         Carrera: <input name="carrera" type="text"/><br/>
        <button name="Enviar" type="submit">Enviar</button>
        <!-- Es un comentario en HTML -->
<#--        Es lo mismo que arriba.-->
<#--        <input type="submit" value="Enviar"/>-->
    </form>
</body>
</html>