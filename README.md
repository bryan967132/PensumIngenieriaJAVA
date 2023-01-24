# Pensum Ingenieria
Pensum Dinámico: Se visualizan los cursos aprobados, sin aprobar y los que aún no están disponibles por falta de prerrequisito. Cada tipo se diferencia del otro por el color.

Los cursos se cargan mediante un archivo con el siguiente formato:

* código,créditos,créditos_requeridos,prerrequisitos,obligatorio,nombre,semestre

Los prerrequisitos tienen el siguiente formato:

* prerrequisito1;prerrequisito2;....;prerrequisitoN

Ejemplo:

* 962,5,0,960;770;795,true,Matematica Computo 2,4