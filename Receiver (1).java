public class Receiver {
// Objects of this class receive a thread id and a block of samples from a // link. Samples are stored in array dataBlocks in the row corresponding to 10
// the thread id. printBlocks prints out the contents of dataBlocks.
  private double dataBlocks[][];
  Receiver() {
   dataBlocks = new double
[GLOBAL_CONSTANTS.MAX_NO_THREADS][GLOBAL_CONSTANTS.DATA_BLOCK_SIZE]; }
public void receiveDataBlock(int id, double data[]){
for (int i = 0; i < GLOBAL_CONSTANTS.DATA_BLOCK_SIZE; i++)
     dataBlocks[id][i] = data[i];
  }
  public void printBlocks() {
   System.out.println("Data:");
   //print out the data as a TABLE, e.g.
   // 0.0 0.0 0.0
   // 1.0 1.0 1.0
   // etc
} }
