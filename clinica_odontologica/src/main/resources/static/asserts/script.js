window.addEventListener('load', function(){
    function init(){
        const registroPaciente = document.querySelector('#registro-paciente');
        const registroOdontologo = document.querySelector('#registro-odontologo');
        const verEspecialistas = document.querySelector('#especialistas');
        const agendarTurno = document.querySelector('#agendar-turno');
        registroPaciente.addEventListener('click', function(event){
            setTimeout(function(){
                window.location.replace('./paciente/paciente.html');
            }, 1000)
        })
        registroOdontologo.addEventListener('click', function(event){
            setTimeout(function(){
                window.location.replace('./odontologo/odontologo.html');
            }, 1000)
        })
        verEspecialistas.addEventListener('click', function(){
            setTimeout(function(){
                window.location.replace('./odontologo/verOdontologos.html');
            })
        })
        agendarTurno.addEventListener('click', function(){
            window.location.replace('./turno/turno.html');
        })

    }
    //RUN APPLICATION
    init();
})