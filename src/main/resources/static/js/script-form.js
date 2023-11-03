const nombre = document.getElementById("nombre")
const apellido = document.getElementById("apellido")
const email = document.getElementById("mail")
const pass = document.getElementById("contraseña")
const pass2 = document.getElementById("contraseña2")
const form = document.getElementById("a-form")
const parrafo = document.getElementById("warnings")

form.addEventListener("submit", e=> {
    e.preventDefault()
    let warnings = ""
    let entrar = false
    let mail = email.value
    
    if(nombre.value === null || nombre.value.length <= 2){
         warnings += "nombre vacio <br>"
    
     }
     if(apellido.value === null || apellido.value.length === 0){
         warnings += "apellido vacio <br>"
     }
   
    if (!validateEmail(mail)) {
        warnings += "email invalido <br>"
        entrar = true;
       
    }

function validateEmail(mail) {
    const regex = /^[^\s@]+@[^\s@]+.[^\s@]{2,7}$/
    return regex.test(mail)
}
   
    
    if(pass.value.length < 6 ){
        warnings += "Contraseña poco segura <br>"
        entrar = true
    }
    
    if(pass2.value != pass.value){
        warnings += "contraseña2 incorrecta<br>"
        entrar = true
    }
    
    if(entrar){
        parrafo.innerHTML =  warnings
    }
})