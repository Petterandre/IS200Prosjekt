/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function editModule() {
     document.getElementById("content").innerHTML='<object type="text/html" data="editmodule.html" ></object>';
}

function deleteModule() {
     document.getElementById("content").innerHTML='<object type="text/html" data="deletemodule.html" ></object>';
}

$(document).ready( function() {
    $("#addModule").on("click", function() {
        $("#content").load("addmodule.html");
    });
});