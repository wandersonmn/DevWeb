// funcao auto executada assim que a pagina eh carregada

// NAO PERMITIR DATAS ANTERIORES AO DIA DE HOJE
today = new Date();
humanMonth = today.getUTCMonth() + 1;

if (humanMonth < 10){
	humanMonth = '0' + humanMonth;
}

minDate = today.getFullYear() + "-" + humanMonth + "-" + today.getUTCDate();
const inputDateElement = document.querySelector("#data");
inputDateElement.setAttribute("min", minDate);
