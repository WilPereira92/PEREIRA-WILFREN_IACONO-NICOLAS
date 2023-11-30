window.addEventListener('load', function(){
    function capturarDatos(){
        //CAPTURAR NODOS
        const nombre = document.querySelector('#NOMBRE');
        const apellido = document.querySelector('#APELLIDO');
        const DNI = document.querySelector('#DNI');
        const calle = document.querySelector('#CALLE');
        const numero = document.querySelector('#NUMERO');
        const localidad = document.querySelector('#LOCALIDAD');
        const provincia = document.querySelector('#PROVINCIA');
        //OBJETO LITERAL PARA ENVIAR AL BACK
        let form_submit = {
            nombre: nombre.value,
            apellido: apellido.value,
            dni: DNI.value,
            fecha_ingreso: obtenerFechaActual(),
            domicilio: {
                calle: calle.value,
                numero: numero.value,
                localidad: localidad.value,
                provincia: provincia.value
            }
        }
        return form_submit;
    }

    function submitForm(){
        //EVENTO SUBMIT
        let form = document.querySelector('form');
        const errorNombre = document.querySelector('#error-nombre');
        const errorApellido = document.querySelector('#error-apellido');
        const errorDni = document.querySelector('#error-dni');
        const errorCalle = document.querySelector('#error-calle');
        const errorNumero = document.querySelector('#error-numero');
        const errorLocalidad = document.querySelector('#error-localidad');
        const errorProvincia = document.querySelector('#error-provincia');
        //Escuchar evento change
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

            if(!errores.dni) {
                errorDni.classList.remove('invisible');
            } else {
                errorDni.classList.add('invisible');
            }

            if(!errores.domicilio.calle) {
                errorCalle.classList.remove('invisible');
            } else {
                errorCalle.classList.add('invisible');
            }

            if(!errores.domicilio.numero) {
                errorNumero.classList.remove('invisible');
            } else {
                errorNumero.classList.add('invisible');
            }

            if(!errores.domicilio.localidad) {
                errorLocalidad.classList.remove('invisible');
            } else {
                errorLocalidad.classList.add('invisible');
            }

            if(!errores.domicilio.provincia) {
                errorProvincia.classList.remove('invisible');
            } else {
                errorProvincia.classList.add('invisible');
            }

        })
        //VOLVER AL MENU INICIAL
        volver();
        form.addEventListener('submit', function(event){
            event.preventDefault();
            const datos = capturarDatos();
            const errores = validarDatos(datos);
            if(errores.nombre && errores.apellido && errores.dni && errores.domicilio.calle && errores.domicilio.numero && errores.domicilio.localidad && errores.domicilio.provincia){
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
    /************************************************************************************************** */
    //LLAMADO A LA FUNCION! INICIO TODO
    submitForm();
    
    //FUNCIÓN ENVIAR AL BACKEND
    function enviarDatosBackend(datos) {
        const url = 'http://localhost:8080/paciente/registrar';
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
                alert('Paciente registrado exitosamente: ' + data.nombre + " " + data.apellido);
                console.log(data);
            })
            .catch(error => {
                alert('error al registrar paciente: ', error.message);
                console.log('error al registrar paciente: ', error.message);
            })
    }
    //VALIDACIONES DE DATOS

    function validarDatos(form_submit){
        let errores = {
            nombre: comprobarStrings(form_submit.nombre),
            apellido: comprobarStrings(form_submit.apellido),
            dni: comprobarDNI(form_submit.dni),
            fecha_ingreso: true,
            domicilio: {
                calle: comprobarStringsDomicilio(form_submit.domicilio.calle),
                numero: comprobarNumero(form_submit.domicilio.numero),
                localidad: comprobarStringsDomicilio(form_submit.domicilio.localidad),
                provincia: comprobarStringsDomicilio(form_submit.domicilio.provincia)
            }
        }
        
        return errores;
    }




    //VALIDACIONES

    
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

    function comprobarStringsDomicilio(str){
        // Elimina los espacios en blanco al inicio y al final del string
        const strSinEspacios = str.trim();

        // Verifica que el string tenga entre 3 y 50 caracteres después de quitar espacios en blanco
        const longitudValida = strSinEspacios.length >= 3 && strSinEspacios.length <= 50;

        // La función devuelve verdadero si el string cumple con los criterios, de lo contrario devuelve falso
        return longitudValida;
    }

    function comprobarDNI(numero) {
        let respuesta = true;
        let cadena = numero + '';
        for(let i = 0; i < cadena.length; i++){
            if(isNaN(cadena)) {
                respuesta = false;
            }
        }
        if(cadena.length > 8){
            respuesta = false;
        }
        if(cadena.length <= 3){
            respuesta = false;
        } 
        if(cadena == '') {
            respuesta = false;
        } 
        return respuesta;
    }
    function comprobarNumero(numero) {
        let respuesta = true;
        let cadena = numero + '';
        for(let i = 0; i < cadena.length; i++){
            if(isNaN(cadena)) {
                respuesta = false;
            }
        }
        if(cadena.length > 8){
            respuesta = false;
        }
        if(cadena == '') respuesta = false;
        return respuesta;
    }
    //fecha actual
    function obtenerFechaActual(){
        const fechaActual = new Date();

        // Formatea la fecha en "yyyy-MM-dd"
        const year = fechaActual.getFullYear();
        const month = String(fechaActual.getMonth() + 1).padStart(2, '0'); 
        const day = String(fechaActual.getDate()).padStart(2, '0');

        const respuesta = `${year}-${month}-${day}`;
        return respuesta;
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
