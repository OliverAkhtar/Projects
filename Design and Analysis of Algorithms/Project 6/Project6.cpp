//Huffman Coding implementation, reads text file of character probability pairs, then constructs and outputs huffman binary tree and character codes.
#include <iostream>
#include <fstream>
using namespace std;

class listBinTreeNode
{
	friend class HuffmanLinkedList;
	friend class HuffmanBinaryTree;
	
	string chStr;
	int prob;
	listBinTreeNode* next;
	listBinTreeNode* left;
	listBinTreeNode* right;
	
	public:
	
		listBinTreeNode(string ChStr, int Prob)
		{
			chStr = ChStr;
			prob = Prob;
			next = NULL;
			left = NULL;
			right = NULL;
		}
		
		~listBinTreeNode()
		{
			delete next;
			delete left;
			delete right;
		}
		
		void printNode(ofstream& outFile)
		{
			outFile << "String: " << chStr << " " << "Prob: " << prob << " " << "Next String: " << next -> chStr << " " << "Left String: " << left -> chStr << " " << "Right String: " << right -> chStr << endl;
		}
};

class HuffmanLinkedList
{
	friend class HuffmanBinaryTree;
	
	listBinTreeNode* listHead;
	listBinTreeNode* oldListHead;
	static int a;
	
	public:
	
		HuffmanLinkedList()
		{
			listBinTreeNode* dummy = new listBinTreeNode("dummy", 0);
			listHead = dummy;
			listBinTreeNode * tail = new listBinTreeNode("tailDummy", 101);
			tail->next = NULL;
			listHead -> next = tail;
		}
		
		void listInsert(listBinTreeNode* newNode)
		{
			listBinTreeNode* spot = findSpot(newNode -> prob);
			newNode -> next = spot -> next;
			spot -> next = newNode;
		}
		
		listBinTreeNode* findSpot(int data)
		{
			listBinTreeNode* spot = listHead;
			while(spot -> next != NULL && spot -> next -> prob < data)
				spot = spot -> next;
			return spot;
		}
		
		bool isEmpty()
		{
			return (listHead -> next == NULL);
		}
		
		void printList(ofstream& outFile)
		{
			if(!isEmpty())
			{
				listBinTreeNode* toPrint = listHead;
				outFile << "listHead -->";
				while(toPrint -> next != NULL)
				{
					outFile << "(" << toPrint -> chStr << "," << toPrint -> prob << "," << toPrint -> next -> chStr << ") -->";
					toPrint = toPrint -> next;
				}
				outFile << "(" << toPrint -> chStr << "," << toPrint -> prob << "," << "NULL)--> NULL" << endl;
			}
			else
				cout << "List Empty!";
		}
};

class HuffmanBinaryTree
{	
	public:
		listBinTreeNode* root;
		
		HuffmanBinaryTree(HuffmanLinkedList& list, ofstream& outFile)
		{
			listBinTreeNode* dummy = new listBinTreeNode("dummy", 0);
			list.oldListHead = dummy;
			list.oldListHead -> next = list.listHead -> next;
			while(!list.isEmpty() && list.listHead -> next -> next -> next != NULL)
			{
				int newProb = (list.listHead -> next -> prob) + (list.listHead -> next -> next -> prob);
				string newStr = (list.listHead -> next -> chStr) + (list.listHead -> next -> next -> chStr);
				listBinTreeNode* newNode = new listBinTreeNode(newStr, newProb);
				newNode -> left = list.listHead -> next;
				newNode -> right = list.listHead -> next -> next;
				list.listHead -> next = list.listHead -> next -> next -> next;
				
				list.listInsert(newNode);
				newNode -> printNode(outFile);
				list.printList(outFile);
			}
			root = list.listHead -> next;
		}
		
		~HuffmanBinaryTree()
		{
			
		}
		
		void preOrderTraversal(listBinTreeNode* T, ofstream& outFile)
		{
			if(T == NULL)
				return;
			else if(T -> left == NULL && T -> right == NULL)
				return;
			else
			{
				T -> printNode(outFile);
				preOrderTraversal(T -> left, outFile);
				preOrderTraversal(T -> right, outFile);
			}
		}
		
		void constructCharCode(listBinTreeNode* T, string code, ofstream& outFile)
		{
			if(T == NULL)
				return;
			else if(T -> left == NULL && T -> right == NULL)
				outFile << T-> chStr << " " << code << endl;
			else
			{
				constructCharCode(T -> left, code + "0", outFile);
				constructCharCode(T -> right, code + "1", outFile);
			}
		}
		
};

int main(int argc, char* argv[])
{
	ifstream inFile(argv[1]);
	ofstream outFile1(argv[2]);
	ofstream outFile2(argv[3]);
	ofstream outFile3(argv[4]);
	
	HuffmanLinkedList myHuffList;
	
	string chrStr;
	int prob;
	while(inFile >> chrStr && inFile >> prob)
	{
		listBinTreeNode* newNode = new listBinTreeNode(chrStr, prob);
		myHuffList.listInsert(newNode);
		myHuffList.printList(outFile3);
	}
	inFile.close();
	
	HuffmanBinaryTree myHuffTree(myHuffList, outFile3);
	myHuffTree.constructCharCode(myHuffTree.root, " ", outFile1);
	myHuffTree.preOrderTraversal(myHuffTree.root, outFile2);
}
