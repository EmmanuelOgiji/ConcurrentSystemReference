import java.util.concurrent.ThreadLocalRandom;

public class SamplingThread extends Thread{
	
	private ADC myADC;
	private int id, myChan;
	private double sampleBlock[];
	private LinkAccessController myLAC;
	private Link theLink;
	
	SamplingThread(int i, ADC a, int c, LinkAccessController l){
		myADC = a;
		id = i;
		myChan = c;
		sampleBlock = new double [GLOBAL_CONSTANTS.DATA_BLOCK_SIZE];
		myLAC = l;
	}
	
	public int iAm(){
		return id;
	}
	
	public void run(){
		
		for(int i=0;i<GLOBAL_CONSTANTS.DATA_BLOCK_SIZE;i++){
			myADC.requestADC(myChan);	
			try{
				Thread.sleep(ThreadLocalRandom.current().nextLong(100, 1000));	//sleep for random period between 0.1s and 1s
			}catch(InterruptedException e){
				
			}	
			sampleBlock[i] =  myADC.sampleADC();			//acquire sample
			myADC.releaseADC(id);						//release lock
		}
		theLink = myLAC.requestLink();
		try{
			Thread.sleep(ThreadLocalRandom.current().nextLong(100, 1000));	//sleep for random period between 0.1s and 1s
		}catch(InterruptedException e){
			
		}
		theLink.writeDataToLink(id, sampleBlock);
		myLAC.releaseLink(theLink);
	}
		
}
