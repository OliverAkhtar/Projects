//Heap sort implementation to sort integers from input file, and output to a file. 
#include <iostream>
#include <fstream>
using namespace std;

class HeapSort
{
	int* heapAry;
	int capacity;
	
	public:
		HeapSort(int size){
			heapAry = new int[size + 1];
			heapAry[0] = 0;
			capacity = size;
		}
		
		~HeapSort(){
			delete [] heapAry;
			cout << "Destructor called!";
		}
		
		void buildHeap(ifstream& input, ofstream& output){
			int data;
			while(input >> data){
				insertOneDataItem(data);
				printHeap(output);
			}
			input.close();
		}
		
		void deleteHeap(ofstream& output2, ofstream& output1){
			while(!isHeapEmpty()){
				deleteRoot(output2);
				printHeap(output1);
			}
			output1.close();
			output2.close();
		}
		
		void insertOneDataItem(int item){
			if(isHeapFull()){
				cout << "Heap full!" << endl;
				return;
			}
			else{
				int nextIndex = ++heapAry[0];
				heapAry[nextIndex] = item;
				bubbleUp();
			}
		}
		
		void deleteRoot(ofstream& outFile){
			if(isHeapEmpty()){
				cout << "Heap Empty!" << endl;
				return;
			}
			else{
				outFile << heapAry[1] << endl;
				int lastIndex = heapAry[0]--;
				heapAry[1] = heapAry[lastIndex];
				heapAry[lastIndex] = 0;
				bubbleDown();
			}
		}
		
		void bubbleUp(){
			int bubbleIndex = heapAry[0];
			while(bubbleIndex != 1){
				int parentIndex = bubbleIndex/2;
				if(heapAry[bubbleIndex] < heapAry[parentIndex]){
					int temp = heapAry[parentIndex];
					heapAry[parentIndex] = heapAry[bubbleIndex];
					heapAry[bubbleIndex] = temp;
				}
				bubbleIndex /= 2;
			}
		}
		
		void bubbleDown(){
			int bubbleIndex = 1;
			while(bubbleIndex * 2 <= heapAry[0]){ //no need to bubbleDown from leaf nodes
				int leftChildIndex = bubbleIndex * 2, rightChildIndex = (bubbleIndex * 2) + 1;
				int smallerChildIndex;
				if(leftChildIndex > heapAry[0]) smallerChildIndex = rightChildIndex; //only right child exists
				else if(rightChildIndex > heapAry[0]) smallerChildIndex = leftChildIndex; //only left child exists
				else{
					if(heapAry[leftChildIndex] < heapAry[rightChildIndex])
						smallerChildIndex = leftChildIndex;
					else
						smallerChildIndex = rightChildIndex;
				}
				if(heapAry[smallerChildIndex] < heapAry[bubbleIndex]){
					int temp = heapAry[smallerChildIndex];
					heapAry[smallerChildIndex] = heapAry[bubbleIndex];
					heapAry[bubbleIndex] = temp;
				}
				bubbleIndex = smallerChildIndex;
			}
		}
		
		bool isHeapEmpty(){
			return (heapAry[0] == 0);
		}
		
		bool isHeapFull(){
			return (heapAry[0] == capacity);
		}
		
		void printHeap(ofstream& outFile){
			if(heapAry[0] < 10){
				for(int i = 0; i <= heapAry[0]; i++)
					outFile << heapAry[i] << " ";
			}
			else{
				for(int i = 0; i <= 10; i++)
					outFile << heapAry[i] << " ";
			}
			outFile << endl;
		}
};

int main(int argc, char* argv[])
{
	ifstream inFile(argv[1]);
	int data, count = 0;
	while(inFile >> data)
		count++;
	inFile.close();
	HeapSort myHeap(count);
	
	inFile.open(argv[1]);
	ofstream out1(argv[2]);
	myHeap.buildHeap(inFile, out1);
	
	ofstream out2(argv[3]);
	myHeap.deleteHeap(out2, out1);
	
	return 0;
}