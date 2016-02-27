//Array implementation of stack using C++, main program reads integers from file, then writes them in reverse to an output file.
#include <iostream>
#include <fstream>
using namespace std;

class Stack
{	
	public:
		int top;
		int* aryStack;
	
		Stack(int size){
			top = -1;
			aryStack = new int[size];
		}
		
		~Stack(){
			delete [] aryStack;
		}
		
		void push(int x){
			aryStack[++top] = x;
		}
		
		int pop(){
			if(!isEmpty()) 
				return aryStack[top--];
		}
		
		bool isEmpty(){
			return (top == -1);
		}
};

int main(int argc, char* argv[])
{
	ifstream infile;
	infile.open(argv[1]);
	int data, count = 0;
	while(infile >> data){
		count++;
		cout << data << endl;
	}
	cout << endl;
	infile.close();
	
	Stack myStack(count);
	infile.open(argv[1]);
	while(infile >> data)
		myStack.push(data);
	infile.close();
	
	ofstream outfile(argv[2]);
	while(!myStack.isEmpty()){
		data = myStack.pop();
		cout << data << endl;
		outfile << data << endl;
	}
	outfile.close();
	
	return 0;
}