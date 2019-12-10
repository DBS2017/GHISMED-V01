package services;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("c_tipo")
public class ConverterTipo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String valor = (String) value;
        
        String retorno = "";
        
        switch(valor){
            case "E":
                retorno = "Servicio";
                break;
            case "A":
                retorno = "Administrativo";
                break;
                default:
                    retorno = "";
        }
        return retorno;
    }
   
}
