window.addEventListener('load', function(){
    function capturarDatos(){
        //CAPTURAR NODOS
        const nombre = document.querySelector('#NOMBRE');
        const apellido = document.querySelector('#APELLIDO');
        const matricula = document.querySelector('#MATRICULA');
        //OBJETO LITERAL A ENVIAR AL BACK
        let datos = {
            matricula: matricula.value,
            nombre: nombre.value,
            apellido: apellido.value
        }
        return datos;
    }

    //FUNCIÓN SUBMIT
    function init(){
        let form = document.querySelector('form');
        const errorNombre = document.querySelector('#error-nombre');
        const errorApellido = document.querySelector('#error-apellido');
        const errorMatricula = document.querySelector('#error-matricula');
        form.addEventListener('change', function(){
            const datos = capturarDatos();
            const errores = validarDatos(datos);
            if(!errores.nombre) {
                errorNombre.classList.remove('invisible');
            } else {
                errorNombre.classList.add('invisible');
            }

            if(!errores.apellido) {
                errorApellido.classList.remove('invisible');
            } else {
                errorApellido.classList.add('invisible');
            }

            if(!errores.matricula) {
                errorMatricula.classList.remove('invisible');
            } else {
                errorMatricula.classList.add('invisible');
            }
        })
        //VOLVER AL MENU INICIAL CON EL BOTÓN VOLVER
        volver();
        form.addEventListener('submit', function(event){
            event.preventDefault();
            const datos = capturarDatos();
            const errores = validarDatos(datos);
            if(errores.nombre && errores.apellido && errores.matricula){
                const input_submit = document.querySelector('input[type=submit]');
                input_submit.disabled = true;
                input_submit.value = "Registrando ..."
                enviarDatosBackend(datos);
                setTimeout(function(){
                    input_submit.value = "Registrado"
                    window.location.replace('../index.html');
                }, 3000)
            } else {
                alert('No se puede enviar el formulario, hay datos inválidos');
            }
            
        })
    }
    //INICIO DE LA APLICACIÓN
    init();



    //ENVIAR DATOS AL BACKEND
    function enviarDatosBackend(datos) {
        const url = 'http://localhost:8080/odontologo/registrar';
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error de red - ${response.status}`);
                  }
                  return response.json();
            })
            .then(data => {
                alert('Odontólogo registrado exitosamente: ' + data.nombre + " " + data.apellido);
                console.log(data);
            })
            .catch(error => {
                alert('error al registrar odontólogo: ', error.message);
                console.log('error al registrar odontólogo: ', error.message);
            })
    }

    //VALIDACIONES DE DATOS

    function validarDatos(form_submit){
        let errores = {
            matricula: comprobarStringsMatricula(form_submit.matricula),
            nombre: comprobarStrings(form_submit.nombre),
            apellido: comprobarStrings(form_submit.apellido),
        }
        
        return errores;
    }

    function comprobarStrings(palabra){
        let respuesta = true;
        if(palabra.includes(" ")){
            respuesta = false;
        }
        if(palabra.length < 3) {
            respuesta = false;
        }
        if(palabra.length > 50){
            respuesta = false;
        }

        for(let i = 0; i < palabra.length; i++){
            if(!isNaN(palabra[i])){
                respuesta = false;
            }
        }
    
        return respuesta;
    }

    function comprobarStringsMatricula(str){
        // Elimina los espacios en blanco al inicio y al final del string
        const strSinEspacios = str.trim();

        // Verifica que el string tenga entre 3 y 50 caracteres después de quitar espacios en blanco
        const longitudValida = strSinEspacios.length >= 3 && strSinEspacios.length <= 50;

        // La función devuelve verdadero si el string cumple con los criterios, de lo contrario devuelve falso
        return longitudValida;
    }

    function volver(){
        const volver = document.querySelector('#volver');
        volver.addEventListener('click', function(){
            setTimeout(function(){
                window.location.replace('../index.html');
            }, 1000)
        })
    }
})