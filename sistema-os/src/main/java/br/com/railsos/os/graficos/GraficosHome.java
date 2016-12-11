package br.com.railsos.os.graficos;

import br.com.railsos.os.dao.GenericDAO;
import br.com.railsos.os.entidade.ClienteNew;
import br.com.railsos.os.entidade.Funcionario;
import br.com.railsos.os.entidade.OrdemServico;
//import br.com.railsos.os.util.FabricaConexao;
//import br.com.railsos.os.util.exception.ErroSistema;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Thamires
 */
@ManagedBean
@ViewScoped
public class GraficosHome implements Serializable {

    private PieChartModel pieModel2;

    private GenericDAO dao = new GenericDAO();
    private Funcionario f = new Funcionario();
    private ClienteNew c = new ClienteNew();
    private OrdemServico o = new OrdemServico();

    private LineChartModel animatedModel1;

    
    
    public GraficosHome(){
    super();
    }
    
    @PostConstruct
    public void init() {
        //    mostrarGraficos();
        createPieModels();
        createAnimatedModels();
    }
    
    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }

    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Relação de questões por dificuldade");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.X);
//        Axis xAxis = animatedModel1.getAxis(AxisType.X);
        List l1 = dao.chart22();
        List l = dao.chart2();
        yAxis.setTickFormat("%1$.0f");
        yAxis.setMin(0);
        yAxis.setMax(l1.get(l1.size() - 1));
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        List l = dao.chart2();
        List l1 = dao.chart22();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        for (int i = 1; i < l.size(); i++) {
            series1.set(l1.get(i - 1), (Number) l.get(i - 1));
        }

//        LineChartSeries series2 = new LineChartSeries();
//        series2.setLabel("Series 2");
// 
//        series2.set(1, 6);
//        series2.set(2, 3);
//        series2.set(3, 2);
//        series2.set(4, 7);
//        series2.set(5, 9);
// 
        model.addSeries(series1);
//        model.addSeries(series2);

        return model;
    }

    private void createPieModels() {
        createPieModel2();
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        List lista;
        List listaOrdem = dao.listar(o);
        List listaFuncionario = dao.listar(f);
        lista = dao.chart1(listaOrdem.size());
        ArrayList lista1;
        lista1 = dao.chart11(listaFuncionario.size());
        for (int i = 0; i < listaFuncionario.size(); i++) {
            pieModel2.set((String) lista1.get(i), (Number) lista.get(i));
        }
        pieModel2.setTitle("Ordem de Serviço por Funcionarios");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

}
//    private PieChartModel graficoOrdensAbertas;
//
//    public PieChartModel getGraficoOrdensAbertas() {
//        return graficoOrdensAbertas;
//    }
//
//    public int getTotalOrdensAbertas() {
//        return totalOrdensAbertas;
//    }
//
//    public void setTotalOrdensAbertas(int totalOrdensAbertas) {
//        this.totalOrdensAbertas = totalOrdensAbertas;
//    }
//
//    public int getNumeroOrdensAbertas() {
//        return numeroOrdensAbertas;
//    }
//
//    public void setNumeroOrdensAbertas(int numeroOrdensAbertas) {
//        this.numeroOrdensAbertas = numeroOrdensAbertas;
//    }
//
//    public String getMensagemAbertas() {
//        return mensagemAbertas;
//    }
//
//    public void setMensagemAbertas(String mensagemAbertas) {
//        this.mensagemAbertas = mensagemAbertas;
//    }
//
//    public void setGraficoOrdensAbertas(PieChartModel graficoOrdensAbertas) {
//        this.graficoOrdensAbertas = graficoOrdensAbertas;
//    }
//    private PieChartModel graficoOrdensEncerradas;
//    private int totalOrdensEncerradas;
//    private int numeroOrdensEncerradas;
//    private String mensagemEncerradas;
//    private int totalOrdensAbertas;
//    private int numeroOrdensAbertas;
//    private String mensagemAbertas;
//
//    public String getMensagemEncerradas() {
//        return mensagemEncerradas;
//    }
//
//    public void setMensagemEncerradas(String mensagemEncerradas) {
//        this.mensagemEncerradas = mensagemEncerradas;
//    }
//
//    public int getTotalOrdensEncerradas() {
//        return totalOrdensEncerradas;
//    }
//
//    public void setTotalOrdensEncerradas(int totalOrdensEncerradas) {
//        this.totalOrdensEncerradas = totalOrdensEncerradas;
//    }
//
//    public int getNumeroOrdensEncerradas() {
//        return numeroOrdensEncerradas;
//    }
//
//    public void setNumeroOrdensEncerradas(int numeroOrdensEncerradas) {
//        this.numeroOrdensEncerradas = numeroOrdensEncerradas;
//    }
//
//    public PieChartModel getGraficoOrdensEncerradas() {
//        return graficoOrdensEncerradas;
//    }
//
//    private void mostrarGraficos() {
//
//        graficoOrdensEncerradas();
//        graficoOrdensAbertas();
//
//    }
//
//    private void graficoOrdensEncerradas() {
//
//        try {
//            Connection conexao = FabricaConexao.getConexao();
//            String sql = "SELECT COUNT( ordem_servico.atendente) as total, funcionarios.nome";
//            sql = sql + " from ordem_servico INNER JOIN";
//            sql = sql + " funcionarios on ordem_servico.atendente = funcionarios.nome";
//            sql = sql + " where status = 'Encerrado' ";
//            sql = sql + " GROUP by ordem_servico.atendente, funcionarios.nome";
//            PreparedStatement ps = null;
//            try {
//                ps = conexao.prepareStatement(sql);
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ResultSet resultSet = null;
//            try {
//                resultSet = ps.executeQuery();
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//
//                graficoOrdensEncerradas = new PieChartModel();
//                graficoOrdensEncerradas.setTitle("Gráfico de Ordens de Serviço Encerradas por Funcionário");
//                graficoOrdensEncerradas.setLegendPosition("e");
//                graficoOrdensEncerradas.setFill(false);
//                graficoOrdensEncerradas.setShowDataLabels(true);
//                graficoOrdensEncerradas.setDiameter(230);
//
//                while (resultSet.next()) {
//                    numeroOrdensEncerradas = resultSet.getInt("total");
//                    graficoOrdensEncerradas.set("Funcionário: " + resultSet.getString("nome") + " - Total de Ordens Realizadas: " + numeroOrdensEncerradas, (resultSet.getInt("total")));
//                    totalOrdensEncerradas = totalOrdensEncerradas + resultSet.getInt("total");
//                }
//
//                mensagemEncerradas = "Total de Ordens Encerradas: " + totalOrdensEncerradas;
//
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                FabricaConexao.fecharConexao();
//            } catch (ErroSistema ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (ErroSistema ex) {
//            Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private void graficoOrdensAbertas() {
//
//        try {
//            Connection conexao = FabricaConexao.getConexao();
//            String sql = "SELECT COUNT( ordem_servico.atendente) as total, funcionarios.nome";
//            sql = sql + " from ordem_servico INNER JOIN";
//            sql = sql + " funcionarios on ordem_servico.atendente = funcionarios.nome";
//            sql = sql + " where status = 'Aberta' ";
//            sql = sql + " GROUP by ordem_servico.atendente, funcionarios.nome";
//            PreparedStatement ps = null;
//            try {
//                ps = conexao.prepareStatement(sql);
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            ResultSet resultSet = null;
//            try {
//                resultSet = ps.executeQuery();
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//
//                graficoOrdensAbertas = new PieChartModel();
//                graficoOrdensAbertas.setTitle("Gráfico de Ordens de Serviço Abertas por Funcionário");
//                graficoOrdensAbertas.setLegendPosition("e");
//                graficoOrdensAbertas.setFill(false);
//                graficoOrdensAbertas.setShowDataLabels(true);
//                graficoOrdensAbertas.setDiameter(230);
//
//                while (resultSet.next()) {
//                    numeroOrdensAbertas = resultSet.getInt("total");
//                    graficoOrdensAbertas.set("Funcionário: " + resultSet.getString("nome") + " - Total de Ordens Abertas: " + numeroOrdensAbertas, (resultSet.getInt("total")));
//                    totalOrdensAbertas = totalOrdensAbertas + resultSet.getInt("total");
//                }
//
//                mensagemAbertas = "Total de Ordens Abertas: " + totalOrdensAbertas;
//
//            } catch (SQLException ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                FabricaConexao.fecharConexao();
//            } catch (ErroSistema ex) {
//                Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } catch (ErroSistema ex) {
//            Logger.getLogger(GraficosHome.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//}
