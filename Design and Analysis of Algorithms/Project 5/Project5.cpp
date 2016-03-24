//Bucket sort implementation, program reads integers from input file, and outputs them in sorted order to a file.
#include <iostream>
#include <fstream>
using namespace std;

class bucketSort
{
	int minData, maxData, bucketSize, offSet;
	int* bucketAry;
	
	public:
		bucketSort(ifstream& inputFile){
			findMinMax(inputFile);
			bucketSize = (maxData - minData) + 1;
			bucketAry = new int[bucketSize];
			offSet = minData;
			for(int i = 0; i < bucketSize; i++)
				bucketAry[i] = 0;
		}
		
		~bucketSort(){
			delete [] bucketAry;
		}
		
		void findMinMax(ifstream& input){
			int data;
			input >> data;
			minData = maxData = data;
			while(input >> data){
				if(data > maxData) maxData = data;
				if(data < minData) minData = data;
			}
		}
		
		void getIndex(ifstream& inputFile, ofstream& out2){
			int data, index;
			while(inputFile >> data){
				index = data - offSet;
				bucketAry[index]++;
				out2 << "Data: " << data << " Index: " << index << " " << endl;
			}
		}
		
		void printBucketAry(ofstream& out2){
			for(int i = 0; i < 19; i++){
				out2 << i << " ";
			}
			out2 << endl;
			for(int i = 0; i < 19; i++){
				if(i >= 10)
					out2 << bucketAry[i] << "  ";
				else
					out2 << bucketAry[i] << " ";
			}
		}
		
		void printSortedData(ofstream& out1){
			for(int i = 0; i < bucketSize; i++){
				while(bucketAry[i] != 0){
					out1 << (i + offSet) << endl;
					bucketAry[i]--;
				}
			}
		}
		
};

int main(int argc, char* argv[])
{
	ifstream inFile(argv[1]);
	bucketSort mySort(inFile);
	inFile.close();
	
	inFile.open(argv[1]);
	ofstream outFile2(argv[3]);
	mySort.getIndex(inFile, outFile2);
	mySort.printBucketAry(outFile2);
	
	ofstream outFile1(argv[2]);
	mySort.printSortedData(outFile1);
	
	inFile.close();
	outFile1.close();
	outFile2.close();
	return 0;
}