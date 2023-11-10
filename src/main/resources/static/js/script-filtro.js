const searchInput = document.getElementById('filtro');
const elementos = document.querySelectorAll('.usuario');

searchInput.addEventListener('input', function() {
    const searchTerm = searchInput.value.toLowerCase();

    elementos.forEach(elemento => {
        const nombre = elemento.querySelector('.nombre').textContent.toLowerCase();
        const rol = elemento.querySelector('.rol').textContent.toLowerCase();
//        const email = elemento.querySelector('.email').textContent.toLowerCase();

        if (searchTerm === '' || nombre.includes(searchTerm) || rol.includes(searchTerm)) { // || email.includes(searchTerm)){
            elemento.style.display = 'table-row';
        } else {
            elemento.style.display = 'none';
        }
    });
});