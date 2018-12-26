public class Vector {
	private double x0;
	private double x1;
	private double w0;
	private double w1;
	private double wb;
	private double a;
	private double y;
	private double z; //classification 
	
	public Vector(double x0, double x1, double w0, double w1, double wb, double a, double y, double z) {
		this.x0 = x0;
		this.x1 = x1;
		this.w0 = w0;
		this.w1 = w1;
		this.wb = wb;
		this.a = a;
		this.y = y;
		this.z = z;
	}

	//setters 
	public void setW0(double newW0) {
		this.w0 = newW0;
	}
	public void setW1(double newW1) {
		this.w1 = newW1;
	}
	public void setA(double newA) {
		this.a = newA;
	}
	public void setY(double newY) {
		this.y = newY;
	}
	public void setZ(double newZ) {
		this.z = newZ;
	}

	//Getters
	public double getX0() {
		return this.x0;
	}
	public double getX1() {
		return this.x1;
	}
	public double getA() {
		return this.a;
	}
	public double getW0() {
		return this.w0;
	}
	public double getW1() {
		return this.w1;
	}
	public double getWb() {
		return this.wb;
	}
	public double getY() {
		return this.y;
	}
	public double getZ() {
		return this.z;
	}

}