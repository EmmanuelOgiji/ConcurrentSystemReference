
public class Link {
	
	private boolean inUse;
	private Receiver myReceiver;
	private int LinkId;
	
	Link(Receiver r, int id){
		inUse = false;
		myReceiver = r;
		LinkId = id;
	}
	
	public boolean isInUse(){
		return inUse;
	}
	
	public void setInUse(){
		inUse = true;
	}
	
	public void setIdle(){
		inUse = false;
	}
	
	public void writeDataToLink(int id,double data[]){
		myReceiver.receiveDataBlock(id,data);
	}
	
	public int getLinkId(){
		return LinkId;
	}
}
