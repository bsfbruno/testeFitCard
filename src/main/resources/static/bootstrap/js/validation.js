let inputSupermercado = document.getElementById('categoria');
let textSup = inputSupermercado.options[inputSupermercado.selectedIndex].text;
let inputTelefone = document.getElementById('telefone');
let buttonSubmit = document.getElementById('saveForm')

$(document).ready(checkCategoria);
console.log(textSup);
function checkCategoria() {
	if (textSup == 'Supermercado' && inputTelefone.value.length == 0) {
		inputTelefone.required = true;  
	} else {
		inputTelefone.required = true;
	}
}
