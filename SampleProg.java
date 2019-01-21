
public class SampleProg {
	
	public static void main(String[] args)throws InterruptedException {
	
																						//declare objects used throughout the program
		ADC theADC;
		LinkAccessController lac;
		Receiver theReceiver;
		SamplingThread sT[];
		ADCInputChannel inputADCInputChannels[];
		
																						//instantiate objects used through out the program
		inputADCInputChannels = new ADCInputChannel[GLOBAL_CONSTANTS.MAX_NO_CHAN];
		theReceiver = new Receiver();
		lac = new LinkAccessController(theReceiver);
		theADC = new ADC(inputADCInputChannels);
		
		for(int i=0;i<GLOBAL_CONSTANTS.MAX_NO_THREADS;i++)
			inputADCInputChannels[i] = new ADCInputChannel((double)i);
		
																						//create, initialize and start an array of 6 SamplingThreads.
		sT = new SamplingThread[GLOBAL_CONSTANTS.MAX_NO_THREADS];
		for(int i=0;i<GLOBAL_CONSTANTS.MAX_NO_THREADS;i++){
			sT[i] = new SamplingThread(i,theADC,i,lac);
			sT[i].start();
		}
		
																					//prevent main from continuing until all 6 s SamplingThreads have completed.
		for(int i=0;i<GLOBAL_CONSTANTS.MAX_NO_THREADS;i++){
			sT[i].join();
		}																					//waits for threads to finish
			
		theReceiver.printBlocks();
	}
}
