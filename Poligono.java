package trabalho_de_cg;

import javax.swing.*;
import java.awt.*;

public class Poligono extends JFrame {
    private int numLados = 10; 
    private int centerX; 
    private int centerY; 
    private int radiusX = 100; 
    private int radiusY = 50; 
    private int offsetX = 0; 
    private int offsetY = 0; 
    private double anguloAtual = 0; 
    private double escala = 1.0; 
    private double cisalhamentoX = 0.0; 
    private double cisalhamentoY = 0.0; 
    private final double ANGULO_PADRAO = Math.toRadians(15); 
    private final double ESCALA_PADRAO = 0.1; 
    private final double CISALHAMENTO_PADRAO = 0.1; 
 
    
    public Poligono() {
		
        setTitle("Polígono");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        JLabel xLabel = new JLabel("Deslocamento X:");
        JLabel yLabel = new JLabel("Deslocamento Y:");    
	JTextField xField = new JTextField("0", 5);
        JTextField yField = new JTextField("0", 5);
        JButton translateButton = new JButton("Transladar");
        translateButton.addActionListener(e -> {
            offsetX = Integer.parseInt(xField.getText());
            offsetY = Integer.parseInt(yField.getText());
            repaint();
        });
	
        JLabel label = new JLabel("Numero de Lados:");
        SpinnerNumberModel model = new SpinnerNumberModel
        (numLados, 10, 100, 1);
        JSpinner spinner = new JSpinner(model);
        spinner.addChangeListener(e -> {
            numLados = (int) spinner.getValue();
            repaint();
        });
        
        JButton HorarioButton = new JButton("Girar horário");
        HorarioButton.addActionListener(e -> rotacionarHorario());       
        JButton AntiHorarioButton = new JButton("Girar anti-horário");
        AntiHorarioButton.addActionListener(e -> rotacionarAntiHorario());
        
        JButton AumentarButton = new JButton("Aumentar Escala");
        AumentarButton.addActionListener(e -> aumentaEscala());       
        JButton DiminuirButton = new JButton("Diminuir Escala");
        DiminuirButton.addActionListener(e -> diminuiEscala());
        
        JLabel cisalhamentoXLabel  = new JLabel ("Cisalhamento X:");
        JLabel cisalhamentoYLabel  = new JLabel ("Cisalhamento Y:");
        JTextField cisalhamentoXField = new JTextField("0", 5);
        JTextField cisalhamentoYField = new JTextField("0", 5);
        JButton cisalhamentoButton = new JButton ("Aplicar Cisalhamento");
        cisalhamentoButton.addActionListener(e ->{
            cisalhamentoX = Double.parseDouble(cisalhamentoXField.getText());
            cisalhamentoY = Double.parseDouble(cisalhamentoYField.getText());
            repaint();
        });
        
        JPanel panel = new JPanel();
        panel.add(xLabel);
        panel.add(xField);
        panel.add(yLabel);
        panel.add(yField);
        panel.add(translateButton);
        panel.add(label);
        panel.add(spinner);       
        panel.add(HorarioButton);        
        panel.add(AntiHorarioButton);       
        panel.add(AumentarButton);       
        panel.add(DiminuirButton);       
        panel.add(cisalhamentoXLabel);
        panel.add(cisalhamentoXField);
        panel.add(cisalhamentoYLabel);
        panel.add(cisalhamentoYField);
        panel.add(cisalhamentoButton);
        add(panel, BorderLayout.NORTH);
        setVisible(true);       
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
      
        int centerX = getWidth() / 2 + offsetX;
        int centerY = getHeight() / 2 - offsetY;
        
        g2d.rotate(anguloAtual, centerX, centerY);
        g2d.setColor(Color.BLACK);
        
        for (int i = 0; i <= numLados; i++) {
            double angle = 2 * Math.PI * i / numLados + anguloAtual;
            double scaledRadiusX = radiusX * escala;
            double scaledRadiusY = radiusY * escala;
            int x1 = (int) (centerX + scaledRadiusX * Math.cos(angle)
                    + cisalhamentoX * scaledRadiusY * Math.sin(angle));
            int y1 = (int) (centerY + scaledRadiusY * Math.sin(angle)
                    - cisalhamentoY * scaledRadiusX * Math.cos(angle));
            int x2 = (int) (centerX + scaledRadiusX * Math.cos(angle + Math.PI / numLados)
                + cisalhamentoX * scaledRadiusY * Math.sin(angle + Math.PI / numLados));
            int y2 = (int) (centerY + scaledRadiusY * Math.sin(angle + Math.PI / numLados)
                - cisalhamentoY * scaledRadiusX * Math.cos(angle + Math.PI / numLados));
            g2d.drawLine(x1, y1, x2, y2);
        }
    }
    public void rotacionar(double angulo) {
        anguloAtual = angulo;
        repaint();
    }
    public void rotacionarHorario() {
        anguloAtual += ANGULO_PADRAO;
        rotacionar(anguloAtual);
    }
    public void rotacionarAntiHorario() {
        anguloAtual -= ANGULO_PADRAO;
        rotacionar(anguloAtual);
    }
    public void escalar(double Escala){
        escala = Escala;
        repaint();
    };
    public void aumentaEscala(){
        escala += ESCALA_PADRAO;
        repaint();
    }
    public void diminuiEscala(){
        escala -= ESCALA_PADRAO;
        if (escala < 0){
            escala =0;
        }
        repaint();
    }
    public static void main(String[] args) {
        new Poligono();
    }
}
