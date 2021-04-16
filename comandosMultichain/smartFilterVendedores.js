function filterstreamitem(){

    var vendedorIngresado=getfilterstreamitem();

    if (vendedorIngresado.keys.length!==1)
        return "Los vendedores se deben y solo se deben registrar con una cedula";
	
	if (vendedorIngresado.keys[0].length<1 || vendedorIngresado.keys[0] === null)
		return "La cedula ingresada no debe estar vacia";

    if (vendedorIngresado.format!="json")
        return "Solo se pueden ingresar vendedores en formato JSON";

    var vendedorJSON = vendedorIngresado.data.json;
	var vendedorJSONLlaves = Object.keys(vendedorJSON);

    if(vendedorJSONLlaves.length !== 2)
		return "Los vendedores deben tener y solamente tener el campo nombre y telefono";

	if ( (!vendedorJSON.hasOwnProperty("nombre")) || vendedorJSON.nombre === null || (vendedorJSON.nombre.length<1))
		return "Los vendedores requieren del campo nombre";
    
    if ( (!vendedorJSON.hasOwnProperty("telefono")) || vendedorJSON.telefono === null || (vendedorJSON.telefono.length<1))
		return "Los vendedores requieren del campo telefono";
	
}