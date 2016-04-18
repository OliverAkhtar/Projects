import java.util.LinkedList;

class MultipleFieldVarList extends FieldVarList
{
	FieldVar fieldVar;
	FieldVarList fieldVarList;
	
	MultipleFieldVarList(FieldVar fv, FieldVarList fvl)
	{
		fieldVar = fv;
		fieldVarList = fvl;
	}

	void getFields(LinkedList<String> l)
	{
		l.add(fieldVar.id.id);
		fieldVarList.getFields(l);
	}
}
