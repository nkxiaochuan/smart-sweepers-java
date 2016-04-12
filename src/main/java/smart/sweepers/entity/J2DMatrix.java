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
		matrix.set_11(1);matrix.set_12(0);matrix.set_13(0);
		matrix.set_21(0);matrix.set_22(1);matrix.set_23(0);
		matrix.set_31(0);matrix.set_32(0);matrix.set_33(1);
	}
	public void translate(double x, double y){
		S2DMatrix mat = new S2DMatrix();
		mat.set_11(1);mat.set_12(0);mat.set_13(0);
		mat.set_21(0);mat.set_22(1);mat.set_23(0);
		mat.set_31(x);mat.set_32(y);mat.set_33(1);
		s2DMatrixMultiply(mat);
	}
	
	public void	scale(double xScale, double yScale){
		S2DMatrix mat = new S2DMatrix();
		mat.set_11(xScale);mat.set_12(0);mat.set_13(0);
		mat.set_21(0);mat.set_22(yScale);mat.set_23(0);
		mat.set_31(0);mat.set_32(0);mat.set_33(1);
		s2DMatrixMultiply(mat);
	}
	
	public void rotate(double rotation){
		S2DMatrix mat = new S2DMatrix();
		double sin = Math.sin(rotation);
		double cos = Math.cos(rotation);
		mat.set_11(sin);mat.set_12(cos);mat.set_13(0);
		mat.set_21(-sin);mat.set_22(cos);mat.set_23(0);
		mat.set_31(0);mat.set_32(0);mat.set_33(1);
		s2DMatrixMultiply(mat);
	}
	
	public void s2DMatrixMultiply(S2DMatrix inMatrix){
		S2DMatrix temp = new S2DMatrix();
		temp.set_11(matrix.get_11() * inMatrix.get_11() + matrix.get_12() * inMatrix.get_21() + matrix.get_13() * inMatrix.get_31());
		temp.set_12(matrix.get_11() * inMatrix.get_12() + matrix.get_12() * inMatrix.get_22() + matrix.get_13() * inMatrix.get_32());
		temp.set_13(matrix.get_11() * inMatrix.get_13() + matrix.get_12() * inMatrix.get_23() + matrix.get_13() * inMatrix.get_33());
		
		temp.set_21(matrix.get_21() * inMatrix.get_11() + matrix.get_22() * inMatrix.get_21() + matrix.get_23() * inMatrix.get_31());
		temp.set_22(matrix.get_21() * inMatrix.get_12() + matrix.get_22() * inMatrix.get_22() + matrix.get_23() * inMatrix.get_32());
		temp.set_23(matrix.get_21() * inMatrix.get_13() + matrix.get_22() * inMatrix.get_23() + matrix.get_23() * inMatrix.get_33());
		
		temp.set_31(matrix.get_31() * inMatrix.get_11() + matrix.get_32() * inMatrix.get_21() + matrix.get_33() * inMatrix.get_31());
		temp.set_32(matrix.get_31() * inMatrix.get_12() + matrix.get_32() * inMatrix.get_22() + matrix.get_33() * inMatrix.get_32());
		temp.set_33(matrix.get_31() * inMatrix.get_13() + matrix.get_32() * inMatrix.get_23() + matrix.get_33() * inMatrix.get_33());
	}
	
	public void transformPoints(List<Point> listPoint){
		for (int i = 0; i < listPoint.size(); i++){
			double tempX = matrix.get_11() * listPoint.get(i).getX() + matrix.get_21() * listPoint.get(i).getY() + matrix.get_31();
			double tempY = matrix.get_12() * listPoint.get(i).getX() + matrix.get_22() * listPoint.get(i).getY() + matrix.get_32();
			listPoint.get(i).setX(tempX);
			listPoint.get(i).setY(tempY);
		}
	}
	
	public S2DMatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(S2DMatrix matrix) {
		this.matrix = matrix;
	}
	
}
