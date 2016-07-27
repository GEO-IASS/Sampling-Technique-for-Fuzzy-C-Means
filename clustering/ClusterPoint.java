package clustering;

public class ClusterPoint {
	public double X;
	public double Y;
	public double clusterIndex;
	/*Basic Constructor
	 * Initializes (x,y) and clusterindex as -1
	 * */
	public ClusterPoint(double X,double Y){
		this.X = X;
		this.Y = Y;
		this.clusterIndex = -1;
	}
	
}
