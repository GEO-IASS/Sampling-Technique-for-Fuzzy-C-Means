package graph2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter; 
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clustering.ClusterCentroid;
import clustering.ClusterPoint;


@SuppressWarnings("serial")
class DrawGraph extends JPanel {
	

	ArrayList<ClusterCentroid> cc = new ArrayList<ClusterCentroid>();
	ArrayList<ClusterPoint> cp = new ArrayList<ClusterPoint>();
	final int PAD = 20;
	int largest;
	
	public DrawGraph() {
		// TODO Auto-generated constructor stub
	}

	public DrawGraph(String loc,int feature1,int feature2,String centerLocation,int largest) {
		this.getDataPoints(loc, feature1, feature2);
		this.getClusterCenters(centerLocation);
		this.largest = largest;
		this.setBackground(Color.WHITE);
		// TODO Auto-generated constructor stub
	}

	public DrawGraph(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DrawGraph(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	void getClusterCenters(String centerLoc){
		try{
		File fileRead = new File(centerLoc);
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead)));
		String line;
		while((line = input.readLine())!= null){
			String attr[] = line.split(",");
			cc.add(new ClusterCentroid(Double.parseDouble(attr[0]),Double.parseDouble(attr[1])));
		}
		input.close();
		}
		catch(Exception e){
			System.out.print("\n"+e.getMessage());
		}
	}
	
	void getDataPoints(String fileLocation,int feature1,int feature2){
		try{
		File fileRead = new File(fileLocation);
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(fileRead)));
		String line;
		while((line = input.readLine())!= null){
			String attr[] = line.split(",");
			cp.add(new ClusterPoint(Double.parseDouble(attr[feature1]),Double.parseDouble(attr[feature2])));
		}
		input.close();
		}
		catch(Exception e){
			System.out.print("\n"+e.getMessage());
		}
	}
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		
		// Draw ordinate
		g2.draw(new Line2D.Double(PAD, PAD, PAD, height-PAD));
		 g2.draw(new Line2D.Double(PAD, height-PAD, width-PAD, height-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "Y axis";
        float sy = PAD + ((height - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "x axis";
        sy = height - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (width - sw)/2;
        g2.drawString(s, sx, sy);
        // Draw lines.
        //double xInc = (double)(width - 2*PAD)/(cc.size()-1);
        double scale = (double)(height - 2*PAD)/largest;
       
        // Mark data points.
        g2.setPaint(Color.GRAY);
        for(int i = 0; i < cp.size(); i++) {
            double x = width - PAD - scale*cp.get(i).X;
            double y = height - PAD - scale*cp.get(i).Y;
            g2.fill(new Ellipse2D.Double(x-2, y-2, 1, 1));
        	}
        
        //Mark Centers
        g2.setPaint(Color.WHITE);
        for(int i = 0; i < cc.size(); i++) {
            double x = width - PAD - scale*cc.get(i).X;
            double y = height - PAD - scale*cc.get(i).Y;
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        	}
        
        
        }
	
	void runGraph(String loc,int feature1,int feature2,String centerLocation){
		
		 JFrame f = new JFrame();
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.add(new DrawGraph(loc,feature1,feature2,centerLocation,1000));
     f.setSize(400,400);
     f.setLocation(200,200);
     f.setVisible(true);
	}

}
