///positionY Scroll
document.addEventListener("DOMContentLoaded", function () {
var storedScrollY = localStorage.getItem("scrollY");

if (storedScrollY) {
window.scrollTo(0, parseInt(storedScrollY) + 125);
}
});
window.addEventListener("beforeunload", function () {
localStorage.setItem("scrollY", window.scrollY + 125);
});



///caracteres

document.addEventListener("DOMContentLoaded", function() {
var textarea = document.getElementById("comentario");
var contador = document.getElementById("charCount");
textarea.addEventListener("input", function() {
 var maxCaracteres = 255;
 var contenido = textarea.value.trim(); // Hacer trim para eliminar espacios en blanco al principio y al final
 var caracteresRestantes = maxCaracteres - contenido.length;

if (caracteresRestantes < 0) {
     // Limitar la longitud 255
textarea.value = contenido.substring(0, maxCaracteres);
    caracteresRestantes = 0;
}
    contador.textContent = caracteresRestantes + " caracteres restantes";});
});
