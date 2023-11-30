window.addEventListener('load', function(){
    function init(){
        volver();
    }

    init();


    //BOTON VOLVER
    function volver(){
        const volver = document.querySelector('#volver');
        volver.addEventListener('click', function(){
            setTimeout(function(){
                window.location.replace('../index.html');
            }, 1000)
        })
    }
})