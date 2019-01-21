
public class ADCInputChannel {
	
	//the value of the instance variable sample represents the value
	//read from the channel by the ADC
	//for simplicity this is a value set when the channel is constructed
	
	private double sample;
	
	ADCInputChannel(double a){
		sample = a;
	}
	
	public double currentSample(){
		return sample;
	}
}
