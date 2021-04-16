function filterstreamitem(){

    var gananciaIngresada=getfilterstreamitem();

    if (gananciaIngresada.keys.length!==1)
        return "Las ganancias se deben y solo se deben registrar con una cedula";
	
	if (gananciaIngresada.keys[0].length<1 || gananciaIngresada.keys[0] === null)
		return "La cedula ingresada no debe estar vacia";

    if (gananciaIngresada.format!="json")
        return "Solo se pueden ingresar ganancias en formato JSON";

    var gananciaJSON = gananciaIngresada.data.json;
	var gananciaJSONLlaves = Object.keys(gananciaJSON);

    if(gananciaJSONLlaves.length !== 1)
		return "Las ganancias deben tener y solamente tener el campo valor";

	if ( (!gananciaJSON.hasOwnProperty("valor")) || gananciaJSON.valor === null || (gananciaJSON.valor.length<1))
		return "Las ganancias requieren del campo valor";
	
	if(isNaN(gananciaJSON.valor))
		return "El valor debe ser numerico";
}