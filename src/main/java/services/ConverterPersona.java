package services;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("c_persona")
public class ConverterPersona implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("b: "+value);
        model.Persona u = new model.Persona(value);
        return u;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("a: "+value);
        model.Persona u = (model.Persona) value;
        return u.toString();
    }

}
