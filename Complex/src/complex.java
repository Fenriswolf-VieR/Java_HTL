
public class complex {
	
	private double re;
	private double im;
	
	public complex() {
		re = 0;
		im = 0;
	}
	
	public complex(double r, double i) {
		super();
		this.re = r;
		im = i;
	}
	
	public complex (complex a) {
		re = a.re;
		im = a.im;
	}
	
	public complex add(complex a) {
		complex c = new complex();
		c.re =re + a.re;
		c.im = im + a.im;
		return c;
	}
	
	public static complex add(complex a, complex b) {
		complex c = new complex();
		c.re =a.re + b.re;
		c.im = a.im + b.im;
		return c;
	}
	
	public void add2(complex a) {
		re =re + a.re;
		im = im + a.im;
	}
	
	public void sub2(complex a) {
		re =re - a.re;
		im = im - a.im;
	}
	
	public void mul2(complex a) {
		re =re * a.re * a.im;
		im = im * a.im * im;
	}
	
	public void div2(complex a) {
		re =re/ (re*im);
		im = im/ (a.re * a.im);
	}
	
	public String toString() {
		return "("+re+" + "+im+"*j)";
		
	}

	public double getRe() {
		return re;
	}

	public void setRe(double re) {
		this.re = re;
	}

	public double getIm() {
		return im;
	}

	public void setIm(double im) {
		this.im = im;
	}
	
	public void set(double re,double im) {
		this.re = re;
		this.im = im;
	}
	
	public void setVal (double val, double arg) {
		 re=val*Math.cos(Math.PI*arg/100);
	     im=val*Math.sin(Math.PI*arg/100);
	}
	public double getVal() {
		return Math.sqrt(re*re + im*im);
	}
	
	public double getArg() {
		return 180 * Math.atan2(im, re) / Math.PI;
	}

}
