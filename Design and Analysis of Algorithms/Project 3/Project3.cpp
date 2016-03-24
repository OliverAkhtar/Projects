//Linked-List implementation of insertion sort, program reads integers from input file, outputs sorted list to output file.
#include <iostream>
#include <fstream>
using namespace std;

class listNode
{
	friend class linkedList;
	int data;
	listNode* next;
	
	listNode(int item){
		data = item;
		next = NULL;
	}
	
	~listNode(){
	}
};

class linkedList
{
	listNode* head;
	
	public:
	
		linkedList(){
			listNode* junk = new listNode(-9999);
			head = junk;
		}
		
		bool isEmpty(){
			return (head -> next == NULL);
		}
		
		void listInsert(int data, ofstream& outFile)
		{
			listNode* spot = findSpot(data);
			if(spot == NULL)
			{
				outFile << "Insert data " << data << ": " << data << " is already in the list." << endl;
				return;
			}
			else
			{
				listNode* newNode = new listNode(data);
				newNode -> next = spot -> next;
				spot -> next = newNode;
				outFile << "Insert data " << data << ": listHead";
				printList(outFile);
			}
		}
		
		listNode* findSpot(int data){
			listNode* spot = head;
			while(spot -> next != NULL && spot -> next -> data < data)
				spot = spot -> next;
			if(spot -> next != NULL && spot -> next -> data == data)
				return NULL;
			return spot;
		}
		
		void printList(ofstream& outFile)
		{
			if(!isEmpty())
			{
				listNode* toPrint = head;
				while(toPrint -> next != NULL)
				{
					outFile << " --> (" << toPrint -> data << ", " << toPrint -> next -> data << ")";
					toPrint = toPrint -> next;
				}
				outFile << " --> (" << toPrint -> data << ", " << "-1)";
				outFile << endl;
			}
			else cout << "List Empty!";
		}
};

int main(int argc, char* argv[])
{
	ifstream inFile(argv[1]);
	ofstream outFile(argv[2]);
	int data = 0;
	linkedList myList;
	
	while(inFile >> data)
		myList.listInsert(data, outFile);
	
	inFile.close();
	outFile.close();
	
	return 0;
}