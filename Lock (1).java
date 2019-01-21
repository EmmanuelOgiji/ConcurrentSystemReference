
public class Lock{
	
	private boolean open;
	SamplingThread s;
	
	public Lock(){
		open = true;
	}
	
	public boolean lock(){
		s = (SamplingThread)Thread.currentThread();
		if(open==false){
			System.out.println("ADC unavailable, thread " + s.iAm() + " about to suspend");
			return open;
		}else{
			try{
				System.out.println("ADC locked by thread " + s.iAm());
				return open;
			}finally{
				open = false;
			}
		}
	}
	
	public void unlock(){
		
		open = true;
		s = (SamplingThread)Thread.currentThread();
		System.out.println("\tADC unlocked by thread "+ s.iAm());
		
	}
}
