
public class LinkAccessController {
	
	private int noOfLinksInUse,k;
	private Link theCommsLinks[];
	private SamplingThread s;
	private boolean g;
	
	LinkAccessController(Receiver r){
		noOfLinksInUse=0;
		g = true;
		theCommsLinks = new Link[GLOBAL_CONSTANTS.NO_OF_LINKS];
		for(int i=0;i<GLOBAL_CONSTANTS.NO_OF_LINKS;i++){
			theCommsLinks[i] = new Link(r,i);
		}
	}
	
	public synchronized Link requestLink(){
		s = (SamplingThread)Thread.currentThread();
		if(!g){
			try{
				while(!g){
					System.out.println("no links free, thread "+ s.iAm()+ " suspends");
					wait();
				}
			}catch(InterruptedException e){
				
			}
		}else{
			noOfLinksInUse++;
			if(noOfLinksInUse==GLOBAL_CONSTANTS.NO_OF_LINKS){
				g = false;
			}
			for(int i=0;i<GLOBAL_CONSTANTS.NO_OF_LINKS;i++){
				if(!theCommsLinks[i].isInUse()){
					k = i;
					theCommsLinks[i].setInUse();
					System.out.println("thread " + s.iAm()+" allowed to use link "+ theCommsLinks[i].getLinkId());
					break;
				}
			}
		}
		return theCommsLinks[k];
	}
	
	public synchronized void releaseLink(Link releasedLink){
		s = (SamplingThread)Thread.currentThread();
		System.out.println("\tthread "+ s.iAm()+ " releases link "+ releasedLink.getLinkId());
		releasedLink.setIdle();
		noOfLinksInUse--;
		g = true;
		notifyAll();
	}
}
