package genericCheckpointing.util;

public class EmployeeRecord extends SerializableObject {
    private int ii;
    private float ff;
    private double dd;
    private long ll;

    /**
     *  empty constructor is needed for reflection
     */
    public EmployeeRecord() {
    	// set default values for data members
    	ii = 0;
    	ff = 0;
    	dd = 0;
    	ll = 0;
    }

    /**
     * constructor setting ii, others as default
     * @param iIn
     */
    public EmployeeRecord(int iIn) {
    	ii = iIn;
    	// set default values for data members
    	ff = 0;
    	dd = 0;
    	ll = 0;
    }
    
    /**
     * constructor
     * @param iiVal
     * @param ffVal
     * @param ddVal
     * @param llVal
     */
    public EmployeeRecord(int iiVal, float ffVal, double ddVal, long llVal) {
    	ii = iiVal;
    	// set default values for data members
    	ff = ffVal;
    	dd = ddVal;
    	ll = llVal;
    }
    
    /**
     * ii setter
     * @param iiVal
     */
    public void set_ii(int iiVal) {
    	ii = iiVal;
    }

    /**
     * ff setter
     * @param ffVal
     */
    public void set_ff(float ffVal) {
    	ff = ffVal;
    }
    
    /**
     * dd setter
     * @param ddVal
     */
    public void set_dd(double ddVal) {
    	dd = ddVal;
    }
    
    /**
     * ll setter
     * @param llVal
     */
    public void set_ll(long llVal) {
    	ll = llVal;
    }
    
    /**
     * ii getter
     * @return
     */
    public int get_ii() {
    	return ii;
    }

    /**
     * ff getter
     * @return
     */
    public float get_ff() {
    	return ff;
    }
    
    /**
     * dd getter
     * @return
     */
    public double get_dd() {
    	return dd;
    }
    
    /**
     * ll getter
     * @return
     */
    public long get_ll() {
    	return ll;
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Float.floatToIntBits(ff);
		result = prime * result + ii;
		result = prime * result + (int) (ll ^ (ll >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRecord other = (EmployeeRecord) obj;
		if (Double.doubleToLongBits(dd) != Double.doubleToLongBits(other.dd))
			return false;
		if (Float.floatToIntBits(ff) != Float.floatToIntBits(other.ff))
			return false;
		if (ii != other.ii)
			return false;
		if (ll != other.ll)
			return false;
		return true;
	}
}