package services;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Singleton;
import model.Ubigeo;

@FacesConverter("C_Ubigeo")
@Singleton
public class ConverterUbigeo implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Ubigeo u = new Ubigeo(value);
        return u;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ubigeo u = (Ubigeo) value;
        return u.toString();
    }

}
