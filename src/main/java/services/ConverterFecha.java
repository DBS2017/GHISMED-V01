package services;

import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("C_Fecha")
public class ConverterFecha implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
        if (value != null) {
            return sdf.format(value).toUpperCase();
        } else {
            return "No devuelto";
        }
    }
}
