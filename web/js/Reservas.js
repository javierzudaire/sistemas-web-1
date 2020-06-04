/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        var roundT = document.getElementById('deporte1');
        var roundP = document.getElementById('deporte2');
        var calendar = document.getElementById('fechas');
        var select = document.getElementById('techo');

        calendar.addEventListener('change',function () {
            var fe = this.value;
            console.log(fe);
        })
        function mostrarReservas()
        {
            console.log("wqeqweqw");
            if((!roundT.checked & !roundP.checked)){
                document.getElementById('alerta').style.color= "red";
            }else if(select.value=="no"){
                alert("Tienes que elegir el tipo de pista");
            }else{
                
                document.getElementById('f').submit();
            }
        };
        var selector = document.getElementById('horas');
        var btn = document.getElementById('reser');
        function reservar()
        {
            if(selector.value=="eleccion"){
                alert("Escoge una hora");
            }else{
                document.getElementById('formRes').submit();
            }
        }
   

