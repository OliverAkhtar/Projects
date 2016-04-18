import java.util.HashMap;
import java.util.LinkedList;

class MultipleClassDef extends ClassDefList
{
	ClassDef classDef;
	ClassDefList classDefList;
	
	MultipleClassDef(ClassDef cd, ClassDefList cdl)
	{
		classDef = cd;
		classDefList = cdl;
	}

	void symbolTable(HashMap<String, LinkedList<String>> hMap)
	{
		if(hMap.containsKey(classDef.className.id.id))
		{
			System.out.println("Error: class name " + classDef.className.id.id + " already declared");
			int n = 2;
			String a = classDef.className.id.id + "(" + n + ")";
			while(hMap.containsKey(a))
			{
				System.out.println("Error: class name " + classDef.className.id.id + " already declared");
				n++;
				a = classDef.className.id.id + "(" + n + ")";
			}
			LinkedList<String> fieldLL = new LinkedList<String>();
			classDef.classBody.fieldVarList.getFields((fieldLL));
			hMap.put(a, fieldLL);
		}
		else
		{
			LinkedList<String> fieldLL = new LinkedList<String>();
			classDef.classBody.fieldVarList.getFields(fieldLL);
			hMap.put(classDef.className.id.id, fieldLL);
		}
		classDefList.symbolTable(hMap);
	}
	
}
