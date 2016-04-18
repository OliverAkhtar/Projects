import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public abstract class Parser extends LexAnalyzer
{
	public static Program program()
	{
		ClassDefList classDefList = classDefList();
		return new Program(classDefList);
	}
	
	public static ClassDefList classDefList()
	
	{
		if(state == State.Keyword_class)
		{
			getToken();
			ClassDef classDef = classDef();
			ClassDefList classDefList = classDefList();
			return new MultipleClassDef(classDef, classDefList);
		}
		else
		{
			return new EmptyClassDefList();
		}
	}
	
	public static ClassDef classDef()
	{
		ClassName className = className();
		ClassBody classBody = classBody();
		return new ClassDef(className, classBody);
	}
	
	public static ClassName className()
	{
		if(state == State.Id)
		{
			Id id = new Id(t);
			getToken();
			return new ClassName(id);
		}
		else
		{
			//expected id
			return null;
		}
	}
	
	public static ClassBody classBody()
	{
		if(state == State.LBrace)
		{
			getToken();
			FieldVarList fieldVarList = fieldVarList();
			if(state == State.RBrace)
			{
				getToken();
				return new ClassBody(fieldVarList);
			}
			else
			{
				return null;
				//expected }
			}
		}
		else
		{
			return null;
			//expected {
		}
	}
	
	public static FieldVarList fieldVarList()
	{
		if(state == State.Id)
		{
			FieldVar fieldVar = fieldVar();
			FieldVarList fieldVarList = fieldVarList();
			return new MultipleFieldVarList(fieldVar, fieldVarList);
		}
		else
		{
			return new EmptyFieldVarList();
		}
	}
	
	public static FieldVar fieldVar()
	{
		Id id = new Id(t);
		getToken();
		return new FieldVar(id);
	}
	
	public static void printTable(HashMap<String, LinkedList<String>> hMap)
	{
		Set<String> set = hMap.keySet();
		Iterator<String> i = set.iterator();
		while(i.hasNext())
		{
			String key = i.next();
			System.out.print(key + "=");
			LinkedList<String> v = hMap.get(key);
			System.out.println(v.toString());
		}
	}
	
	public static void main(String argv[])
	{
		setIO(argv[0], argv[1]);
		setLex();
		
		getToken();
		
		Program program = program();
		HashMap<String, LinkedList<String>> h = new HashMap<String, LinkedList<String>>();
		program.symbolTable(h);
		printTable(h);
		
	}
}
