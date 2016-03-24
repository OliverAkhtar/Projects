//Linked-List implementation of stack using C++, main program reads integers from file, then writes them in reverse to an output file.
#include <iostream>
#include <cstddef>
#include <fstream>
using namespace std;

class listNode
{
	friend class linkedStack;
	int data;
	listNode* next;
	    
	listNode(int item){
		data = item;
	}
	
	~listNode(){}
	
};

class linkedStack
{
	listNode* top;
	
	public:
	
		linkedStack(){
			top = NULL;
		}
		
		~linkedStack(){
			listNode* toDelete = top;
			while(toDelete != NULL){
				top = top -> next;
				delete toDelete;
				toDelete = top;
			}
		}
		
		void push(int input){
			listNode* newNode = new listNode(input);
			newNode -> next = top;
			top = newNode;
			cout << newNode -> data << " " << newNode -> next << endl;
		}
		
		int pop(){
			if(isEmpty() == false)
			{
				listNode* toDelete = top;
				int temp = top -> data;
				top = top -> next;
				delete toDelete;
				return temp;
			}
			else return 0;
		}
		
		bool isEmpty(){
			return (top == NULL);
		}
		
};

int main(int argc, char* argv[])
{
    ifstream inFile;
	inFile.open(argv[1]);
	int data = 0;
	
	linkedStack myStack;
	while(inFile >> data){
		myStack.push(data);
	}
	inFile.close();
	cout << endl;
	
	ofstream outFile(argv[2]);
	int finish = 1;
	finish = myStack.pop();
	while(finish != 0){
		cout << finish << endl;
		outFile << finish << endl;
		finish = myStack.pop();
	}
	
	outFile.close();
	
	return 0;
}