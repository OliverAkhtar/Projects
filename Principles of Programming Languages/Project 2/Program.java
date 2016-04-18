import java.util.HashMap;
import java.util.LinkedList;

class Program
{
	ClassDefList classDefList;
	
	Program(ClassDefList cdl)
	{
		classDefList = cdl;
	}

	void symbolTable(HashMap<String, LinkedList<String>> hMap)
	{
		classDefList.symbolTable(hMap);
	}
}
