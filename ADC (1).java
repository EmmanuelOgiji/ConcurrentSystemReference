
public class ADC {
	
	private Lock theADCLock;
	private int sampleADCInputChannel;
	private ADCInputChannel chan[];
	
	public ADC(ADCInputChannel ch[]){
		theADCLock = new Lock();
		chan = ch;
	}
	
	public synchronized void requestADC(int c){
			try{
				while(!theADCLock.lock())
					wait();
				sampleADCInputChannel = c;
			}catch (InterruptedException e) {
				
			}
	}
	
	public double sampleADC(){
		double j = chan[sampleADCInputChannel].currentSample();
		return j;
	}
	
	public synchronized void releaseADC(int c){
		theADCLock.unlock();	//unlock lock
		notifyAll();			//wake up waiting threads
	}
	
}

