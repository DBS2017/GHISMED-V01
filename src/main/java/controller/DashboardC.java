package controller;

import dao.DashboardImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import model.TEstablecimiento;
import model.Consulta;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

@Named(value = "dash")
@ViewScoped

public class DashboardC implements Serializable {

    private TEstablecimiento dAsig;
    private List<TEstablecimiento> listAsig;
    private Consulta dAten;
    private List<Consulta> listAten;
    private final DashboardImpl dao;
    
    private int TESP;
    private int TTRA;
    private int TCON;

    /*Graficos*/
    private BarChartModel barAtenciones;

    public DashboardC() {
        dao = new DashboardImpl();
        dAsig = new TEstablecimiento();
        listAsig = new ArrayList();
        dAten = new Consulta();
        listAten = new ArrayList();

    }

    @PostConstruct
    public void inti() {
        try {
//            listAsig = dao.listarAsig();
            createBarAtenciones("2019%");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Consulta getAtenciones() {
        Consulta modelo = new Consulta();
        /*for (Consulta c : listAten) {
            }*/
        return modelo;
    }

    public void createBarAtenciones(String q) throws Exception {
        try {
            System.out.println(6 % 2 + ": es par");
            List<Number> valH = new ArrayList<>();
            List<Number> valM = new ArrayList<>();
            List<String> titulos = new ArrayList<>();
//            listAten = dao.listarAtenciones(q);
            barAtenciones = new BarChartModel();
            ChartData data = new ChartData();

            BarChartDataSet man = new BarChartDataSet();
            man.setLabel("Hombres");
            man.setBackgroundColor("rgba(255, 159, 64, 0.2)");
            man.setBorderColor("rgb(255, 159, 64)");
            man.setBorderWidth(1);

            BarChartDataSet wmn = new BarChartDataSet();
            wmn.setLabel("Mujeres");
            wmn.setBackgroundColor("rgba(255, 99, 132, 0.2)");
            wmn.setBorderColor("rgb(255, 99, 132)");
            wmn.setBorderWidth(1);/*
            for (Consulta modelo : listAten) {
                if (titulos.size() <= 6) {
                    if (modelo.getIDPAC() == 2) {
                        titulos.add(modelo.getESTDCONS());
                        valM.add(modelo.getIDEST());
                        if (valH.size() == valM.size() - 2) {
                            valH.add(0);
                        }
                    } else {
                        valH.add(modelo.getIDEST());
                        if (valH.size() > valM.size()) {
                            valM.add(0);
                        }
                        if (valH.size() > titulos.size()) {
                            titulos.add(modelo.getESTDCONS());
                        }
                    }
                }
            }*/
            man.setData(valH);
            wmn.setData(valM);
            data.addChartDataSet(man);
            data.addChartDataSet(wmn);
            data.setLabels(titulos);
            barAtenciones.setData(data);

            //Options
            BarChartOptions options = new BarChartOptions();
            CartesianScales cScales = new CartesianScales();
            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            CartesianLinearTicks ticks = new CartesianLinearTicks();
            ticks.setBeginAtZero(true);
            linearAxes.setTicks(ticks);
            cScales.addYAxesData(linearAxes);
            options.setScales(cScales);

            Title title = new Title();
            title.setDisplay(true);
            title.setText("Atenciones");
            options.setTitle(title);

            barAtenciones.setOptions(options);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    public TEstablecimiento getdAsig() {
        return dAsig;
    }

    public void setdAsig(TEstablecimiento dAsig) {
        this.dAsig = dAsig;
    }

    public List<TEstablecimiento> getListAsig() {
        return listAsig;
    }

    public void setListAsig(List<TEstablecimiento> listAsig) {
        this.listAsig = listAsig;
    }

    public Consulta getdAten() {
        return dAten;
    }

    public void setdAten(Consulta dAten) {
        this.dAten = dAten;
    }

    public List<Consulta> getListAten() {
        return listAten;
    }

    public void setListAten(List<Consulta> listAten) {
        this.listAten = listAten;
    }

    public BarChartModel getBarAtenciones() {
        return barAtenciones;
    }

    public void setBarAtenciones(BarChartModel model) {
        this.barAtenciones = model;
    }

    public int getTESP() {
        return TESP;
    }

    public void setTESP(int TESP) {
        this.TESP = TESP;
    }

    public int getTTRA() {
        return TTRA;
    }

    public void setTTRA(int TTRA) {
        this.TTRA = TTRA;
    }

    public int getTCON() {
        return TCON;
    }

    public void setTCON(int TCON) {
        this.TCON = TCON;
    }

}
