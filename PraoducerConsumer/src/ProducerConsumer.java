public class ProducerConsumer {

}

class Producer implements Runnable{

    public void run() {

    }
}

class Consumer implements Runnable{
    public void run() {

    }
}

class CircularArray{
    private int[] circularArray;
    private int capacity;
    public CircularArray(int length){
        this.capacity = length;
        this.circularArray = new int[length];

    }

    public void produce(int i){
        int length = this.circularArray.length;
        int index = length-1;
        if(capacity == length){
            index = 0;
        }
        this.circularArray[index] = i;
    }

    public void consume(){

    }
}