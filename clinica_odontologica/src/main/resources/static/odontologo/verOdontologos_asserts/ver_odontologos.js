window.addEventListener('load', function(){
    
    function init(){
        //VOLVER AL MENÚ PRINCIPAL
        volver();
        listarOdontologos();
    }
    
    //RUN APPLICATION
    init();
    
    
    //LISTAR TODOS LOS ODONTÓLOGOS
    function listarOdontologos(){
        const url = 'http://localhost:8080/odontologo/listar';
        fetch(url)
        .then(response => {
            if(!response.ok){
                throw new Error(`Error de red - ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            showOdontologos(data);
        })
        .catch(error => {
            //alert('error al listar odontólogos: ', error.message);
            console.log('error al listar odontólogos: ', error.message);
        })
    }
    
    function showOdontologos(data){
        const container_odontologos = document.querySelector('#inyectar-odontologos');
        container_odontologos.classList.remove('inyectar-odontologos');
        const no_register = document.querySelector('#no-register');
        const totalOdontologos = document.querySelector('#total-odontologos');
        if(data.length != 0){
            container_odontologos.classList.add('inyectar-odontologos');
            totalOdontologos.innerHTML = `Total de odontólogos registrados = ${data.length}`;
            no_register.innerHTML = 'Listado de odontólogos';
            for(let i = 0; i < data.length; i++){
                container_odontologos.innerHTML += 
                '<ul>' + 
                    `<li>id = ${data[i].id}</li>` +
                    `<li>nombre = ${data[i].nombre}</li>` +
                    `<li>apellido = ${data[i].apellido}</li>` +
                '</ul>'
                ;
            }
        }
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