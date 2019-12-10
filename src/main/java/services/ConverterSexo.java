package services;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("C_Sexo")
public class ConverterSexo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String valor = (String) value;
        
        String sexo = "";
        
        switch(valor){
            case "F":
                sexo = "F";
                break;
            case "M":
                sexo = "M";
                break;
                default:
                    sexo = "---";
        }
        return sexo;
    }
   
}
