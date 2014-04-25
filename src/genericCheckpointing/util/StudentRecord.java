package genericCheckpointing.util;

public class StudentRecord extends SerializableObject {

    private int ii;
    private short ss;
    private boolean bb;
    
    // empty constructor is needed for reflection
    public StudentRecord() {
    	// set default values for data members
    	ii = 0;
    	ss = 0;
    	bb = false;
    }
    
    public StudentRecord(int iIn) {
    	// set default values for data members
    	ii = iIn;
    	ss = 0;
    	bb = false;
    }
    
    public StudentRecord(int ii, short ss, boolean bb) {
    	this.ii = ii;
    	this.ss = ss;
    	this.bb = bb;
    }

    public void set_ii(int iiVal) {
    	ii = iiVal;
    }

    public void set_ss(short ssVal) {
    	ss = ssVal;
    }
    
    public void set_bb(boolean bbVal) {
    	bb = bbVal;
    }
    
    public int get_ii() {
    	return ii;
    }

    public short get_ss() {
    	return ss;
    }
    
    public boolean get_bb() {
    	return bb;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bb ? 1231 : 1237);
		result = prime * result + ii;
		result = prime * result + ss;
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
		StudentRecord other = (StudentRecord) obj;
		if (bb != other.bb)
			return false;
		if (ii != other.ii)
			return false;
		if (ss != other.ss)
			return false;
		return true;
	}


}