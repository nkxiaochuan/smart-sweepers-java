/**
 * 
 */
package smart.sweepers.entity;

import java.util.List;

/**
 * @author Andy
 *
 */
public class J2DMatrix {

	private S2DMatrix matrix;

	public J2DMatrix(){
		identity();
	}
	
	public void identity(){
		
	}
	public void translate(double x, double y){
		
	}
	
	public void	scale(double xScale, double yScale){
		
	}
	
	public void rotate(double rotation){
		
	}
	
	public void transformPoint(List<Point> listPoints){
		
	}
	
	
	
	public S2DMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(S2DMatrix matrix) {
		this.matrix = matrix;
	}
	
}
